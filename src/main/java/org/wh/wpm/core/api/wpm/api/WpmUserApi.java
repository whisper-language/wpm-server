package org.wh.wpm.core.api.wpm.api;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.wh.wpm.core.admin.wpm.entity.Wpm;
import org.wh.wpm.core.admin.wpm.service.WpmService;
import org.wh.wpm.core.api.wpm.form.WpmSearchForm;
import org.wh.wpm.core.common.dto.ResDto;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController()
@Slf4j
public class WpmUserApi {

    @Autowired
    WpmService wpmService;

    @RequestMapping(method = RequestMethod.GET, value = "/search")
    public ResDto<?> search(WpmSearchForm form) {
        return new ResDto(0, "", wpmService.search(form));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/download/{authorName}/{packageName}/{versionName}")
    public void down(@PathVariable("authorName") String authorName,
                     @PathVariable("packageName") String packageName,
                          @PathVariable("versionName") String versionName,
                          HttpServletResponse response) throws IOException {
        wpmService.download(response,authorName,packageName,versionName);
    }
}
