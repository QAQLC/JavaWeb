package com.itheima.service.Impl;

 import com.itheima.dao.EmpDao;
import com.itheima.dao.impl.EmpDaoA;
import com.itheima.pojo.Emp;
import com.itheima.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
 import org.springframework.stereotype.Service;

 import java.util.List;

@Service
public class EmpServiceA implements EmpService {
    // 面向接口编程

    private  EmpDao empDao;
    @Autowired
    public void setEmpDao(EmpDao empDao) {
        this.empDao = empDao;
    }
    @Override
    public List<Emp> listEmp() {
        List<Emp> empList = empDao.listEmp();
        empList.stream().filter(emp -> {
            if ("1".equals(emp.getGender())) {
                emp.setGender("男");
            } else {
                emp.setGender("女");
            }
            if ("1".equals(emp.getJob())) {
                emp.setJob("讲师");
            }
            if ("2".equals(emp.getJob())) {
                emp.setJob("班主任");
            }
            if ("3".equals(emp.getJob())) {
                emp.setJob("就业老师");
            }
            return false;
        });
        return empList;
    }
}
