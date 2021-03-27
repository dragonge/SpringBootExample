package com.example.springboot.common.app.service.impl;

import com.example.springboot.common.app.service.AyRoleService;
import com.example.springboot.common.domain.entity.AyRole;
import com.example.springboot.common.domain.repository.AyRoleRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 描述：用户角色Service
 * @author Ay
 * @date   2017/12/2
 */
@Service
public class AyRoleServiceImpl implements AyRoleService {

    @Resource
    private AyRoleRepository ayRoleRepository;

    @Override
    public AyRole find(String id){
        return ayRoleRepository.findById(id).get();
    }
}
