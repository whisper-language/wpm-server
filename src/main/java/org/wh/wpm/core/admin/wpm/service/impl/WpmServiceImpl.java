package org.wh.wpm.core.admin.wpm.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;
import org.wh.wpm.boot.utils.DateUtils;
import org.wh.wpm.boot.utils.MdUtils;
import org.wh.wpm.core.admin.wpm.entity.PublishType;
import org.wh.wpm.core.admin.wpm.entity.Wpm;
import org.wh.wpm.core.admin.wpm.mapper.WpmMapper;
import org.wh.wpm.core.admin.wpm.service.WpmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wh.wpm.core.api.wpm.form.PublishForm;
import org.wh.wpm.core.api.wpm.form.SearchForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
@Slf4j
public class WpmServiceImpl implements WpmService {

    @Autowired
    WpmMapper wpmMapper;

    @Override
    public Wpm create(Wpm form) {
        return null;
    }

    @Override
    public Boolean delete(Wpm form) {
        return null;
    }

    @Override
    public Wpm save(Wpm form) {
        return null;
    }

    @Override
    public List<Wpm> query(Wpm form) {
        return wpmMapper.query(form);
    }

    @Override
    public List<Wpm> list(Wpm form) {
        return null;
    }

    @Override
    public List<Wpm> search(SearchForm form) {
        return new ArrayList<>();
    }

    @Override
    public void download(HttpServletRequest request, HttpServletResponse response, String authorName, String packageName, String versionName) throws Exception {

        var fName = authorName + "_" + packageName + "_" + versionName + ".zip";
        var f = new File("./repo/" + fName);
        var output = response.getOutputStream();
        if (f.exists()) {
            log.info("读取本地缓存文件");
            FileInputStream fin = new FileInputStream(f);
            response.setContentType("application/octet-stream");
            output.write(fin.readAllBytes());
            fin.close();
        } else {
            log.info("读取后端文件缓存到本地");

            if (checkVersionExit(authorName, packageName, versionName)) {
                readFromUpstream(request, response, fName);
                return;
            }
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; filename=" + fName);
            var local = new FileOutputStream(f);

            log.info("创建zip文件");
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            //创建压缩留
            ZipOutputStream zipOutputStream = new ZipOutputStream(byteArrayOutputStream);
            var list = new ArrayList<String>();
            list.add("test1.txt");
            list.add("test2.txt");
            list.forEach(t -> {
                try {
                    log.info("写入文件");
                    var z = new ZipEntry(t);
                    zipOutputStream.putNextEntry(z);
                    zipOutputStream.write(("test file" + t).getBytes(StandardCharsets.UTF_8));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            zipOutputStream.close();
            var s = byteArrayOutputStream.toByteArray();
            output.write(s);
            local.write(s);
            local.close();
        }
        output.close();
    }

    boolean checkVersionExit(String authorName, String packageName, String versionName) {
        return get(authorName, packageName, versionName) == null;
    }

    Wpm get(String authorName, String packageName, String versionName) {
        return wpmMapper.queryByAuthorNameAndPackageNameAndVersionName(authorName, packageName, versionName);
    }

    @Override
    public Wpm upload(HttpServletResponse response, String authorName, String packageName, String versionName, MultipartFile file, PublishForm form) throws Exception {
        log.info("上传" + form.getPublish());
        var isExit = wpmMapper.queryByAuthorNameAndPackageNameAndVersionNameAndPublish(authorName, packageName, versionName, form.getPublish());
        if (isExit != null) {
            if (form.getPublish().equals(PublishType.PRODUCT)) {
                throw new Exception("对应的版本已经存在请更新版本后再发布 " + authorName + " " + packageName + " " + versionName);
            } else {
                log.info("处理压缩文件");
                var fName = authorName + "_" + packageName + "_" + versionName + ".zip";
                var f = new File("./repo/" + fName);
                FileOutputStream fileOutputStream = new FileOutputStream(f);
                fileOutputStream.write(file.getBytes());
                fileOutputStream.close();
                isExit.setMd5(MdUtils.md5(file.getBytes()));
                isExit.setSha256(MdUtils.sha256(file.getBytes()));
                isExit.setUpdateAt(DateUtils.getNow());
                isExit.setSize(file.getSize());
                wpmMapper.updateByPrimaryKey(isExit);
            }
        } else {

            log.info("处理压缩文件");
            var fName = authorName + "_" + packageName + "_" + versionName + ".zip";
            var f = new File("./repo/" + fName);
            FileOutputStream fileOutputStream = new FileOutputStream(f);
            fileOutputStream.write(file.getBytes());
            fileOutputStream.close();

            log.info("创建包记录" + form.getPublish());
            var s = Wpm.builder()
                    .author(authorName)
                    .name(packageName)
                    .version(versionName)
                    .access(form.getAccess().ordinal())
                    .publish(form.getPublish().ordinal())
                    .md5(MdUtils.md5(file.getBytes()))
                    .sha256(MdUtils.sha256(file.getBytes()))
                    .createAt(DateUtils.getNow())
                    .updateAt(DateUtils.getNow())
                    .size(file.getSize())
                    .build();
            wpmMapper.insertSelective(s);
        }

        return null;
    }

    @Value("${app.upstream.url}")
    String upstream;
    @Value("${app.upstream.token}")
    String token;

    public void readFromUpstream(HttpServletRequest request, HttpServletResponse response, String fName) throws Exception {
        log.info("从后端读取包信息" + request.getRequestURL());
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest newRequest = HttpRequest.newBuilder()
                .uri(new URI(upstream))
                .setHeader("token", token)
                .build();
        var s = client.sendAsync(newRequest, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(HttpResponse::body)
                .thenAccept(s1 -> {
                    response.setContentType("application/octet-stream");
                    response.setHeader("Content-Disposition", "attachment; filename=" + fName);
                    byte[] source;
                    try {
                        source = s1.readAllBytes();
                        log.info("结果" + new String(source));
                        //如果结果正确 cache到本地
                        var out = response.getOutputStream();
                        out.write(source);
//                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }
}
