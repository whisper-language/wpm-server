package org.wh.wpm.core.common.controller;

import lombok.extern.slf4j.Slf4j;
import org.wh.wpm.core.common.dto.ResDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController()
@Slf4j
public class IndexController {
    @RequestMapping(method = RequestMethod.GET, value = "/home")
    public ResDto<?> index() {
        return new ResDto(0, "", "");
    }
}
