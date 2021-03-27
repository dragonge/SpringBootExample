package com.example.springboot.common.app.service.impl;

import com.example.springboot.common.api.dto.AyUserDao;
import com.example.springboot.common.app.service.AyUserService;
import com.example.springboot.common.domain.entity.AyUser;
import com.example.springboot.common.domain.repository.AyUserRepository;
import com.example.springboot.common.error.BusinessException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.annotation.Reference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Future;

/**
 * 描述：用户服务层实现类
 * @author 阿毅
 * @date   2017/10/14
 */
//@Transactional
@Service
public class AyUserServiceImpl implements AyUserService {

    @Resource(name = "ayUserRepository")
    private AyUserRepository ayUserRepository;

    @Resource
    private RedisTemplate redisTemplate;

    private static final String ALL_USER = "ALL_USER_LIST";

    public AyUserServiceImpl(){

    }

    @Resource
    private AyUserDao ayUserDao;

    @Override
    public Long findUserTotalNum() {
        return ayUserRepository.count();
    }

    @Override
    public AyUser findByNameAndPassword(String name, String password) {
        return ayUserDao.findByNameAndPassword(name, password);
    }

    @Override
    public AyUser findByNameAndPasswordRetry(String name, String password) {
        System.out.println("[findByNameAndPasswordRetry] 方法失败重试了！");
        throw new BusinessException();
    }

    @Override
    public AyUser findById(String id){
        //step.1  查询Redis缓存中的所有数据
        List<AyUser> ayUserList = redisTemplate.opsForList().range(ALL_USER, 0, -1);
        if(ayUserList != null && ayUserList.size() > 0){
            for(AyUser user : ayUserList){
                if (user.getId().equals(id)){
                    return user;
                }
            }
        }
        //step.2  查询数据库中的数据
        AyUser ayUser = ayUserRepository.findById(id).get();
        if(ayUser != null){
            //step.3 将数据插入到Redis缓存中
            redisTemplate.opsForList().leftPush(ALL_USER, ayUser);
        }
        return ayUser;
    }

    @Override
    public List<AyUser> findAll() {
        try{
            System.out.println("开始做任务");
            long start = System.currentTimeMillis();
            List<AyUser> ayUserList = ayUserRepository.findAll();
            long end = System.currentTimeMillis();
            System.out.println("完成任务，耗时：" + (end - start) + "毫秒");
            return ayUserList;
        }catch (Exception e){
            logger.error("method [findAll] error",e);
            return Collections.EMPTY_LIST;
        }
    }

    @Override
    @Async
    public Future<List<AyUser>> findAsynAll() {
        try{
            System.out.println("开始做任务");
            long start = System.currentTimeMillis();
            List<AyUser> ayUserList = ayUserRepository.findAll();
            long end = System.currentTimeMillis();
            System.out.println("完成任务，耗时：" + (end - start) + "毫秒");
            return new AsyncResult<List<AyUser>>(ayUserList) ;
        }catch (Exception e){
            logger.error("method [findAll] error",e);
            return new AsyncResult<List<AyUser>>(null);
        }
    }

    //@Transactional
    @Override
    public AyUser save(AyUser ayUser) {
        AyUser saveUser =  ayUserRepository.save(ayUser);
        String error = null;
        error.split("/");
        return saveUser;
    }

    Logger logger = LogManager.getLogger(this.getClass());

    @Override
    public void delete(String id) {
        ayUserRepository.deleteById(id);
        logger.info("userId:" + id + "用户被删除");
    }

    @Override
    public Page<AyUser> findAll(Pageable pageable) {
        return ayUserRepository.findAll(pageable);
    }

    @Override
    public List<AyUser> findByName(String name){
        return ayUserRepository.findByName(name);
    }

    @Override
    public AyUser findByUserName(String name) {
        return ayUserDao.findByUserName(name);
    }

    @Override
    public List<AyUser> findByNameLike(String name){
        return ayUserRepository.findByNameLike(name);
    }

    @Override
    public List<AyUser> findByIdIn(Collection<String> ids){
        return ayUserRepository.findByIdIn(ids);
    }


    @Override
    public boolean update(AyUser ayUser) {
        AyUser user = ayUserRepository.save(ayUser);
        if(user == null){
            //更新失败
            throw new BusinessException();
        }
        return Boolean.TRUE;
    }
}
