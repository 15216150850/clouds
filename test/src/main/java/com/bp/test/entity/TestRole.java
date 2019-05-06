package com.bp.test.entity;


import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

/**
 * @auther: 钟欣凯
 * @date: 2019/3/14 15:26
 */
@Entity
@Data
public class TestRole {

    @Id
    @GeneratedValue
    private Integer id;

    private String roleName;

    private String roleCode;


    private Long userId;

}
