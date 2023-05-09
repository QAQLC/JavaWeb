package com.itheima.dao.impl;

import com.itheima.dao.EmpDao;
import com.itheima.pojo.Emp;
import com.itheima.utils.XmlParseUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("dao")
public class EmpDaoA implements EmpDao {
    @Override
    public List<Emp> listEmp() {

        String file = this.getClass().getClassLoader().getResource("emp.xml").getFile();

        List<Emp> empList = null;
        try {
            empList = XmlParseUtils.parse(file, Emp.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return empList;
    }
}
