package com.pangpang.jei.controller;

import com.pangpang.jei.util.SnowflakeIdWorker;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: Twitter下SnowFlake
 * @author: leewake
 * @create: 2018-06-07 11:30
 **/

@RestController
public class SnowflakeController {

    @ApiOperation("生成分布式Id")
    @GetMapping("/distributionId/generate")
    public void gen() {
        System.out.println("开始：" + System.currentTimeMillis());
        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
        for (int i = 0; i < 50; i++) {
            long id = idWorker.nextId();
            System.out.println(id);
        }
        System.out.println("结束：" + System.currentTimeMillis());
    }


}
