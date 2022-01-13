package org.wh.wpm.core.admin.wpm.entity;

import lombok.Getter;

import java.io.Serializable;

@Getter
public enum AccessType implements Serializable {
    PUBLIC,
    PRIVATE
}
