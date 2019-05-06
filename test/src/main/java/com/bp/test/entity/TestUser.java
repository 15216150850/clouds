package com.bp.test.entity;

import com.bp.common.base.BaseEntity;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @auther: 钟欣凯
 * @date: 2019/3/14 15:03
 */
@Entity
@Data

public class TestUser extends BaseEntity {


    @Id
    @GeneratedValue
    private Long id;

    private String username;


    private String password;

    private String teelPhone;


    private Boolean sex;

    @OneToMany(targetEntity = TestRole.class,fetch = FetchType.LAZY)
    @JoinColumn(name="userId",foreignKey = @ForeignKey(name="none",value= ConstraintMode.NO_CONSTRAINT))
    private List<TestRole> testRoles;

}
