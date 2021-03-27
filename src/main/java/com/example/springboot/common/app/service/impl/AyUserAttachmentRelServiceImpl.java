package com.example.springboot.common.app.service.impl;

import com.example.springboot.common.app.service.AyUserAttachmentRelService;
import com.example.springboot.common.domain.entity.AyUserAttachmentRel;
import com.example.springboot.common.domain.repository.AyUserAttachmentRelRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 描述：用户附件实现层
 * @author  Ay
 * @date    2017/12/4.
 */
@Service
public class AyUserAttachmentRelServiceImpl implements AyUserAttachmentRelService {

    @Resource
    private AyUserAttachmentRelRepository ayUserAttachmentRelRepository;

    @Override
    public AyUserAttachmentRel save(AyUserAttachmentRel ayUserAttachmentRel){
        return ayUserAttachmentRelRepository.save(ayUserAttachmentRel);
    }
}
