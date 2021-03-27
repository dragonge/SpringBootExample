package com.example.springboot.common.domain.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 描述：用户表
 * @Author 阿毅
 * @date   2017/10/8.
 */
@Entity
@Table(name = "ay_user")
@Data
public class AyUser implements Serializable{

    //主键
    @Id
    private String id;
    //用户名
    private String name;
    //密码
    private String password;
    //邮箱
    private String mail;
}
