package com.lichking.datasource.switcher.controller;

import com.lichking.datasource.switcher.dao.base.BaseMapper;
import com.lichking.datasource.switcher.dao.misc.MiscMapper;
import com.lichking.datasource.switcher.po.TestPO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("test")
public class TestController {

    @Resource
    private MiscMapper miscMapper;

    @Resource
    private BaseMapper baseMapper;

    @RequestMapping("misc")
    public String misc(){
        miscMapper.insert(build());
        return "success";
    }

    @RequestMapping("base")
    public String base(){
        baseMapper.insert(build());
        return "success";
    }


    private TestPO build() {
        TestPO po = new TestPO();
        Date date = new Date();
        po.setName(date.toString());
        return po;
    }

}
