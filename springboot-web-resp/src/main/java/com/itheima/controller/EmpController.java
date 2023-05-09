package com.itheima.controller;

import com.itheima.pojo.Result;
import com.itheima.service.EmpService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 地图打点的信息
 */
@RestController
public class EmpController {
    private EmpService empService;
    @Autowired
    public void setEmpService (EmpService empService) {
        this.empService = empService;
    }
    @RequestMapping("/listEmp")
    public Result list () {
        return Result.success(empService.listEmp());
    }
}
