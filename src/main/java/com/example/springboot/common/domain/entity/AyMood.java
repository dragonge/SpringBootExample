package com.example.springboot.common.domain.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 描述：微信说说实体
 * @author Ay
 * @date   2017/11/28.
 */
@Entity
@Table(name = "ay_mood")
@Data
public class AyMood implements Serializable{
    /**
     *     主键
     */
    @Id
    private String id;
    /**
     *     说说内容
     */
    private String content;
    /**
     *     用户id
     */
    private String userId;
    /**
     *     点赞数量
     */
    private Integer praiseNum;
    /**
     *  发表时间
     */
    private Date publishTime;
}
