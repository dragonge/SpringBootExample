package com.example.springboot.common.domain.entity;

import lombok.Data;

import javax.persistence.Id;

/**
 * 描述：用户附件关联表
 * @author  Ay
 * @date    2017/12/4.
 */
@Data
public class AyUserAttachmentRel {
    @Id
    private String id;
    private String userId;
    private String fileName;
}
