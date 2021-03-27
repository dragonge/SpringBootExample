package com.example.springboot.common.app.service.impl;

import com.example.springboot.common.app.service.AyMoodService;
import com.example.springboot.common.domain.entity.AyMood;
import com.example.springboot.common.domain.repository.AyMoodRepository;
import com.example.springboot.common.mq.AyMoodProducer;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;

/**
 * 描述：微信说说服务层
 * @author Ay
 * @date   2017/12/2
 */
@Service
public class AyMoodServiceImpl implements AyMoodService {

    @Resource
    private AyMoodRepository ayMoodRepository;
    @Override
    public AyMood save(AyMood ayMood) {
        return ayMoodRepository.save(ayMood);
    }

    private static Destination destination = new ActiveMQQueue("ay.queue.asyn.save");

    @Resource
    private AyMoodProducer ayMoodProducer;
    @Override
    public String asynSave(AyMood ayMood){
        //往队列ay.queue.asyn.save推送消息，消息内容为说说实体
        ayMoodProducer.sendMessage(destination, ayMood);
        return "success";
    }
}
