package org.wh.wpm.core.admin.wpm.mapper;

import org.wh.wpm.core.admin.wpm.entity.Wpm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface WpmMapper extends tk.mybatis.mapper.common.Mapper<Wpm> {
    @Select(" select * from student where id=#{form.id}")
    List<Wpm> query(@Param("form") Wpm form);
}
