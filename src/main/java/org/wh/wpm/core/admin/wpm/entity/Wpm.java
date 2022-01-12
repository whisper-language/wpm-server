package org.wh.wpm.core.admin.wpm.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

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

    /**
     * name
     */
    private String name;
    /**
     * version
     */
    private String version;

    /**
     * repo
     */
    private String repo;

}
