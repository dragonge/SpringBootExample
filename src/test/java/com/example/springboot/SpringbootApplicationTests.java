package com.example.springboot;

import com.example.springboot.common.app.service.AyUserService;
import com.example.springboot.common.domain.entity.AyUser;
import com.example.springboot.common.app.service.AyUserAttachmentRelService;
import com.example.springboot.common.domain.entity.AyUserAttachmentRel;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Future;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
class SpringbootApplicationTests {
    @Test
    public void contextLoads() {
    }

    @Resource
    private JdbcTemplate jdbcTemplate;

    /**
     * Mysql集成Spring Boot简单测试
     */
    @Test
    public void mySqlTest() {
        String sql = "select id,name,password from ay_user";
        List<AyUser> userList =
                jdbcTemplate.query(sql, (rs, rowNum) -> {
                    AyUser user = new AyUser();
                    user.setId(rs.getString("id"));
                    user.setName(rs.getString("name"));
                    user.setPassword(rs.getString("password"));
                    return user;
                });
        System.out.println("查询成功：");
        for (AyUser user : userList) {
            System.out.println("【id】: " + user.getId() + "；【name】: " + user.getName());
        }
    }

    @Resource
    private AyUserService ayUserService;

    @Test
    public void testRepository() {
        //查询所有数据
        List<AyUser> userList = ayUserService.findAll();
        System.out.println("findAll() :" + userList.size());
        //通过name查询数据
        List<AyUser> userList2 = ayUserService.findByName("test");
        System.out.println("findByName() :" + userList2.size());
        Assert.isTrue(userList2.get(0).getName().equals("test"), "data error!");
        //通过name模糊查询数据
        List<AyUser> userList3 = ayUserService.findByNameLike("%es%");
        System.out.println("findByNameLike() :" + userList3.size());
        Assert.isTrue(userList3.get(0).getName().equals("test"), "data error!");
        //通过id列表查询数据
        List<String> ids = new ArrayList<String>();
        ids.add("1");
        ids.add("2");
        List<AyUser> userList4 = ayUserService.findByIdIn(ids);
        System.out.println("findByIdIn() :" + userList4.size());
        //分页查询数据
        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "id"));
        Page<AyUser> userList5 = ayUserService.findAll(pageRequest);
        System.out.println("page findAll():" + userList5.getTotalPages() + "/" + userList5.getSize());
        //新增数据
        AyUser ayUser = new AyUser();
        ayUser.setId("3");
        ayUser.setName("test");
        ayUser.setPassword("123");
        ayUserService.save(ayUser);
        //删除数据
        ayUserService.delete("3");

    }

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void testRedis() {
        //增 key：name，value：ay
        redisTemplate.opsForValue().set("name", "ay");
        String name = (String) redisTemplate.opsForValue().get("name");
        System.out.println(name);
        //删除
        redisTemplate.delete("name");
        //更新
        redisTemplate.opsForValue().set("name", "test");
        //查询
        name = stringRedisTemplate.opsForValue().get("name");
        System.out.println(name);
    }

    @Resource
    private AyUserAttachmentRelService ayUserAttachmentRelService;

    @Test
    public void testMongoDB(){
        AyUserAttachmentRel ayUserAttachmentRel = new AyUserAttachmentRel();
        ayUserAttachmentRel.setId("1");
        ayUserAttachmentRel.setUserId("1");
        ayUserAttachmentRel.setFileName("个人简历.doc");
        ayUserAttachmentRelService.save(ayUserAttachmentRel);
        System.out.println("保存成功");
    }

    @Test
    public void testMybatis() {
        AyUser ayUser = ayUserService.findByNameAndPassword("test", "123");
        log.info(ayUser.getId() + ayUser.getName());

    }

    @Test
    public void testAsync(){
        long startTime = System.currentTimeMillis();
        System.out.println("第一次查询所有用户！");
        List<AyUser> ayUserList = ayUserService.findAll();
        System.out.println("第二次查询所有用户！");
        List<AyUser> ayUserList2 = ayUserService.findAll();
        System.out.println("第三次查询所有用户！");
        List<AyUser> ayUserList3 = ayUserService.findAll();
        long endTime = System.currentTimeMillis();
        System.out.println("总共消耗：" + (endTime - startTime) + "毫秒");

    }

    @Test
    public void testAsync2()throws Exception{
        long startTime = System.currentTimeMillis();
        System.out.println("第一次查询所有用户！");
        Future<List<AyUser>> ayUserList = ayUserService.findAsynAll();
        System.out.println("第二次查询所有用户！");
        Future<List<AyUser>> ayUserList2 = ayUserService.findAsynAll();
        System.out.println("第三次查询所有用户！");
        Future<List<AyUser>> ayUserList3 = ayUserService.findAsynAll();
        while (true){
            if(ayUserList.isDone() && ayUserList2.isDone() && ayUserList3.isDone()){
                break;
            }else {
                Thread.sleep(5);
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("总共消耗：" + (endTime - startTime) + "毫秒");
    }

    //    @Test
//    public void testRetry() throws Exception{
//        boolean result = false;
//        try{
//            result = load();
//            if(!result){
//                load();//一次重试
//            }
//        }catch (Exception e){
//            load();//一次重试
//        }
//    }


//    @Test
//    public void testRetry2() throws Exception{
//        boolean result = false;
//        try{
//            result = load();
//            if(!result){
//                //延迟3s，重试3次
//                reLoad(3000L,3);//延迟多次重试
//            }
//        }catch (Exception e){
//            //延迟3s，重试3次
//            reLoad(3000L,3);//延迟多次重试
//        }
//    }

}
