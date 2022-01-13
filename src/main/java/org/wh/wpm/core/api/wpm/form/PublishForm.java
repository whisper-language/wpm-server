package org.wh.wpm.core.api.wpm.form;

import lombok.Data;
import org.wh.wpm.core.admin.wpm.entity.AccessType;
import org.wh.wpm.core.admin.wpm.entity.PublishType;

@Data
public class PublishForm {
    //访问类型
    AccessType access=AccessType.PUBLIC;
    //发布类型
    PublishType publish=PublishType.PRODUCT;
}
