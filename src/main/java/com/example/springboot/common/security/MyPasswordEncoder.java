//package com.example.springboot.common.security;
//
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//public class MyPasswordEncoder implements PasswordEncoder {
//
//    /**
//     * 使用springboot，权限管理使用spring security，使用内存用户验证，但无响应报错：
//     * java.lang.IllegalArgumentException: There is no PasswordEncoder mapped for the id "null"
//     * 解决方法：
//     * 这是因为Spring boot 2.0.3引用的security 依赖是 spring security 5.X版本，此版本需要提供一个PasswordEncorder的实例，否则后台汇报错误：
//     * ————————————————
//     * 版权声明：本文为CSDN博主「灰太狼_cxh」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
//     * 原文链接：https://blog.csdn.net/weixin_39220472/article/details/80865411
//     * @param charSequence
//     * @return
//     */
//    @Override
//    public String encode(CharSequence charSequence) {
//        return charSequence.toString();
//    }
//
//    @Override
//    public boolean matches(CharSequence charSequence, String s) {
//        return s.equals(charSequence.toString());
//    }
//}
