package org.wh.wpm.core.admin.wpm.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Table(name = "wpm")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Wpm implements Serializable {

    @Id
    @Column(insertable = false)
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @NotNull private String author;
    /**
     * name
     */
    @NotNull private String name;
    /**
     * version
     */
    @NotNull private String version;
    /**
     * 访问级别
     */
    @NotNull private Integer access;

    /**
     * 访问级别
     */
    @NotNull private Integer publish;

    /**
     * repo
     */
    private String repo;
    /**
     * 压缩文件md5值
     */
    @NotNull
    private String md5;
    /**
     * 压缩文件 sha256值
     */
    @NotNull
    private String sha256;


    private  Long size;

    Date createAt;
    Date updateAt;

}
