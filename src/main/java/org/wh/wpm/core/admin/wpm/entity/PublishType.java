package org.wh.wpm.core.admin.wpm.entity;

import lombok.Getter;

import java.io.Serializable;

@Getter
public enum PublishType implements Serializable {
    /**
     * 可重复提交 重复的话就覆盖掉
     */
    DEV,
    /**
     * 生产版本 只能递增 , 不能重复
     */
    PRODUCT,
}
