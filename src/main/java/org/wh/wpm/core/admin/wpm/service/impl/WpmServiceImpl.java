package org.wh.wpm.core.admin.wpm.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.wh.wpm.core.admin.wpm.entity.Wpm;
import org.wh.wpm.core.admin.wpm.mapper.WpmMapper;
import org.wh.wpm.core.admin.wpm.service.WpmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wh.wpm.core.api.wpm.form.WpmSearchForm;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
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
    public List<Wpm> search(WpmSearchForm form) {
        return new ArrayList<>();
    }

    @Override
    public void download(HttpServletResponse response, String authorName, String packageName, String versionName) throws Exception {

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
                readFromUpstream(response);
                return;
            }
            response.setContentType("application/octet-stream");
            var local = new FileOutputStream(f);
            response.setHeader("Content-Disposition", "attachment; filename=" + fName);


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
        var packageMeta = wpmMapper.queryByAuthorNameAndPackageNameAndVersionName(authorName, packageName, versionName);
        return packageMeta == null;
    }

    @Override
    public Wpm upload(HttpServletResponse response, String authorName, String packageName, String versionName, MultipartFile file) throws Exception {
        log.info("上传");
        if (!checkVersionExit(authorName, packageName, versionName)) {
            throw new Exception("对应的版本已经存在请更新版本后再发布 " + authorName + " " + packageName + " " + versionName);
        }

        wpmMapper.insert(Wpm.builder().author(authorName).name(packageName).version(versionName).build());

        var fName = authorName + "_" + packageName + "_" + versionName + ".zip";
        var f = new File("./repo/" + fName);
        FileOutputStream fileOutputStream = new FileOutputStream(f);
        fileOutputStream.write(file.getBytes());
        fileOutputStream.close();
        return null;
    }

    public void readFromUpstream(HttpServletResponse response) throws Exception {
        log.info("从后端读取包信息");

        throw new Exception("包文件不存在");
    }
}
