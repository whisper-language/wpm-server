package org.wh.wpm.core.admin.wpm.mapper;

import org.wh.wpm.core.admin.wpm.entity.Wpm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface WpmMapper extends tk.mybatis.mapper.common.Mapper<Wpm> {
    @Select(" select * from wpm where id=#{form.id}")
    List<Wpm> query(@Param("form") Wpm form);

    @Select(" select * from wpm where author=#{authorName} and name=#{packageName} and version=#{versionName} limit 1")
    Wpm queryByAuthorNameAndPackageNameAndVersionName(@Param("authorName") String authorName, @Param("packageName") String packageName, @Param("versionName") String versionName);
}
