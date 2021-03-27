package com.example.springboot.common.domain.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 描述：用户角色实体
 * @author Ay
 * @date   2017/12/10.
 */
@Entity
@Table(name = "ay_role")
@Data
public class AyRole {

    @Id
    private String id;
    private String name;
}
