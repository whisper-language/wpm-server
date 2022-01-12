package org.wh.wpm.core.admin.wpm.service;

import org.wh.wpm.core.admin.wpm.entity.Wpm;
import org.wh.wpm.core.api.wpm.form.WpmSearchForm;

import java.util.List;

public interface WpmService extends CurdService<Wpm> {
    List<Wpm> search(WpmSearchForm form);
}
