package com.example.springboot.common.app.service.impl;

import com.example.springboot.common.app.service.AyUserRoleRelService;
import com.example.springboot.common.domain.entity.AyUserRoleRel;
import com.example.springboot.common.domain.repository.AyUserRoleRelRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 描述：用户角色关联Service
 * @author   Ay
 * @date     2017/12/10.
 */
@Service
public class AyUserRoleServiceImpl implements AyUserRoleRelService {

    @Resource
    private AyUserRoleRelRepository ayUserRoleRelRepository;

    @Override
    public List<AyUserRoleRel> findByUserId(String userId) {
        return ayUserRoleRelRepository.findByUserId(userId);
    }
}
