package com.example.springboot.common.api.controller;

import com.example.springboot.common.app.service.AyUserService;
import com.example.springboot.common.domain.entity.AyUser;
import com.example.springboot.common.error.BusinessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * 描述：用户控制层
 * @author Ay
 * @date   2017/10/28.
 */
@Controller
@RequestMapping("/ayUser")
public class AyUserController {

    @Resource
    private AyUserService ayUserService;

    @RequestMapping("/test")
    public String test(Model model) {
        List<AyUser> ayUser = ayUserService.findAll();
        model.addAttribute("users",ayUser);
        return "ayUser";
    }

    @RequestMapping("/findAll")
    public String findAll(Model model) {
        List<AyUser> ayUser = ayUserService.findAll();
        model.addAttribute("users",ayUser);
        throw new BusinessException("业务异常");
    }

    @RequestMapping("/update")
    public String update(Model model) {
        AyUser ayUser = new AyUser();
        ayUser.setId("1");
        ayUser.setName("阿毅");
        boolean isSuccess = ayUserService.update(ayUser);
        return "success";
    }

}
