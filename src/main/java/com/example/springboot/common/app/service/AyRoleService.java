package com.example.springboot.common.app.service;

import com.example.springboot.common.domain.entity.AyRole;

/**
 * 描述：用户角色Service
 * @author 阿毅
 * @date   2017/10/14
 */
public interface AyRoleService {

    AyRole find(String id);
}
