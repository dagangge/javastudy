package com.shinva.javastudy.controller;

import cn.hutool.core.util.StrUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lrg
 * @date 2020/9/7
 **/
@RestController
public class helloController {
    @GetMapping("hello")
    public String hello(@RequestParam(required = false,name = "who") String who) {
        if (StrUtil.isBlank(who)) {
            who = "world";
        }
        return "hello" + who;
    }}
