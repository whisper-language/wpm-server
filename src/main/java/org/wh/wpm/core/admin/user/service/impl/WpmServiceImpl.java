package org.wh.wpm.core.admin.user.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.wh.wpm.core.admin.user.entity.Wpm;
import org.wh.wpm.core.admin.user.mapper.WpmMapper;
import org.wh.wpm.core.admin.user.service.WpmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        log.info(form.getId()+"");
        wpmMapper.insert(new Wpm());
        return wpmMapper.query(form);
    }

    @Override
    public List<Wpm> list(Wpm form) {
        return null;
    }
}
