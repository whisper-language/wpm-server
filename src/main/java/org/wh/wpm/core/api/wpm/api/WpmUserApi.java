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

@RestController()
@Slf4j
@RequestMapping("/api/v0.0.1/")
public class WpmUserApi {

    @Autowired
    WpmService wpmService;

    @RequestMapping(method = RequestMethod.GET, value = "/search")
    public ResDto<?> search(WpmSearchForm form) {
        return new ResDto(0, "", wpmService.search(form));
    }
}
