package org.wh.wpm.core.admin.user.api;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.wh.wpm.core.admin.user.entity.Wpm;
import org.wh.wpm.core.admin.user.service.WpmService;
import org.wh.wpm.core.common.dto.ResDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController()
@Slf4j
@RequestMapping("/wpm")
public class WpmApi {

    @Autowired
    WpmService wpmService;

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public ResDto<?> create(@RequestBody Wpm wpm) {
        return new ResDto(0, "", wpmService.create(wpm));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/delete")
    public ResDto<?> delete(@RequestBody Wpm wpm) {
        return new ResDto(0, "", wpmService.delete(wpm));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/save")
    public ResDto<?> save(@RequestBody Wpm wpm) {
        return new ResDto(0, "", wpmService.save(wpm));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/query")
    public ResDto<?> query(Wpm wpm,
                           @RequestParam(name = "pn",defaultValue ="1",required = false ) Integer pn,
                           @RequestParam(name = "ps",defaultValue ="10",required = false )Integer ps) {
        PageHelper.startPage(pn, ps);
        var data=wpmService.query(wpm);
        PageInfo<Wpm> pageInfo = new PageInfo<>(data);
        return new ResDto(0, "", pageInfo);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public ResDto<?> list(Wpm wpm) {
        return new ResDto(0, "", wpmService.list(wpm));
    }

}
