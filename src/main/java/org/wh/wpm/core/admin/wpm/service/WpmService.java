package org.wh.wpm.core.admin.wpm.service;

import org.springframework.web.multipart.MultipartFile;
import org.wh.wpm.core.admin.wpm.entity.Wpm;
import org.wh.wpm.core.api.wpm.form.WpmSearchForm;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface WpmService extends CurdService<Wpm> {
    List<Wpm> search(WpmSearchForm form);

    void download(HttpServletResponse response, String authorName, String packageName, String versionName) throws Exception;

    Wpm upload(HttpServletResponse response, String authorName, String packageName, String versionName, MultipartFile file) throws Exception;
}
