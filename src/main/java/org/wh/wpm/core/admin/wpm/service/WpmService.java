package org.wh.wpm.core.admin.wpm.service;

import org.springframework.web.multipart.MultipartFile;
import org.wh.wpm.core.admin.wpm.entity.Wpm;
import org.wh.wpm.core.api.wpm.form.PublishForm;
import org.wh.wpm.core.api.wpm.form.SearchForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface WpmService extends CurdService<Wpm> {
    List<Wpm> search(SearchForm form);

    void download(HttpServletRequest request, HttpServletResponse response, String authorName, String packageName, String versionName) throws Exception;

    Wpm upload(HttpServletResponse response, String authorName, String packageName, String versionName, MultipartFile file, PublishForm form) throws Exception;
}
