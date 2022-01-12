package org.wh.wpm.core.admin.wpm.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.wh.wpm.core.admin.wpm.entity.Wpm;
import org.wh.wpm.core.admin.wpm.mapper.WpmMapper;
import org.wh.wpm.core.admin.wpm.service.WpmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wh.wpm.core.api.wpm.form.WpmSearchForm;

import java.util.ArrayList;
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
}
