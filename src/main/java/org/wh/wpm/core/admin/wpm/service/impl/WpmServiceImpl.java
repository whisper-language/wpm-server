package org.wh.wpm.core.admin.wpm.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.wh.wpm.core.admin.wpm.entity.Wpm;
import org.wh.wpm.core.admin.wpm.mapper.WpmMapper;
import org.wh.wpm.core.admin.wpm.service.WpmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wh.wpm.core.api.wpm.form.WpmSearchForm;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    public void download(HttpServletResponse response, String authorName, String packageName, String versionName) throws IOException {
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename="+packageName+"_"+versionName+".zip");
        var output=response.getOutputStream();

        ZipOutputStream zipOutputStream = new ZipOutputStream(response.getOutputStream());
        var list=new ArrayList<String>();
        list.add("test1.txt");
        list.add("test2.txt");
        list.forEach(t -> {
            try {
                log.info("写入文件");
                var z = new ZipEntry(t);
                zipOutputStream.putNextEntry(z);
                zipOutputStream.write("test file".getBytes(StandardCharsets.UTF_8));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        zipOutputStream.close();
        output.write("111".getBytes(StandardCharsets.UTF_8));
    }
}
