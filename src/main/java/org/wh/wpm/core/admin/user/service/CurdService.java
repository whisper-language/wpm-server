package org.wh.wpm.core.admin.user.service;

import java.util.List;

public interface CurdService<T> {
   T create(T form);
   Boolean delete(T form);
   T save(T form);
   List<T> query(T form);
   List<T> list(T form);
}
