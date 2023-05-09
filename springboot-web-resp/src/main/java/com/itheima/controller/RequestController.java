package com.itheima.controller;


import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.itheima.pojo.Result;
import com.itheima.pojo.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * 使用原始方法接受参数
 * 可以接受get也可以接受set
 */
@RestController
public class RequestController {
    @RequestMapping("/simpleParam")
    public String simpleParam (HttpServletRequest request) {
        // 获取请求参数
        String name = request.getParameter("name");
        String ageStr = request.getParameter("age");
        int age = Integer.parseInt(ageStr);

        System.out.println(name + ":" + ageStr);
        return "ok";
    }
}


/**
 * 使用springboot方式接受简单参数
 * 可以接受get set 参数
 */
@RestController
class RequestController2 {
    @RequestMapping("/simpleParam2")
    public String simpleParams (String name, Integer age) {
        System.out.println("springboot 方式简单参数");
        System.out.println(name + "：" + age);
        return "ok";
    }
}
/**
 * 参数别名的使用，使用springboot方式简单参数
 * 要是用@RequestParam 这个注解，默认别名参数是必传，可以通过required false来取消这一默认行为
 */
@RestController
class RequestController3 {
    @RequestMapping("/simpleParam3")
    public String simpleParams (@RequestParam(name="name", required = false) String userName, Integer age) {
        System.out.println("别名参数，springboot方式简单参数");
        System.out.println(userName + "：" + age);
        return "ok";
    }
}

/**
 * 简单参数实体类
 */

@RestController
class RequestController4 {
    @RequestMapping("/simpleParam4")
    public  String simpleParam (User user) {
        System.out.println(user);
        return "ok";
    }
}

/**
 * 数组参数传递
 * get
 */
@RestController
class RequestController5 {
    @RequestMapping("/arrayParam")
    public String arrayParam (String[] hobby) {
        System.out.println(Arrays.toString(hobby));
        return "ok";
    }
}

/**
 * 集合参数，@RequestParam 绑定参数的关系
 * get
 */

@RestController
class RequestController6 {
    @RequestMapping("/listParam")
    public String arrayParam (@RequestParam List<String> hobby) {
        System.out.println(hobby);
        return "ok";
    }
}

/**
 * 日期参数，使用
 */

@RestController
class RequestController7 {
    @RequestMapping("/dateParam")
    public String dateParam(@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime updateTime){
        // 将日期格式化
        DateTimeFormatter dtf  = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String Date = dtf.format(updateTime);
        System.out.println(Date);
        return "ok";
    }
}

/**
 * content-type=""application/json"
 */

@RestController
class RequestController8 {
    @RequestMapping("/jsonParam")
    // 将json数据封装到实体对象中
    public String jsonParam(@RequestBody User user){
        System.out.println(user);
        return "ok";
    }
}

/**
 * 接受一个json数据，返回接受的json数据,不做任何api转化
 * @RequestBody
 */
@RestController
class RequestController9 {
    @PostMapping("/getJson")
    public User getJson(@RequestBody User user) {
        System.out.println(user);
        return user;
    }

}

/**
 * 使用fastJson 进行转换
 */
@RestController
class RequestController10 {
    @PostMapping("/getJsonToTransferString")
    public JSONObject getJsonToTransferString(@RequestBody User user) {
        String userStr = JSONObject.toJSONString(user);
        JSONObject jsonObject = JSONObject.parseObject(userStr);
        return jsonObject;
    }
}

/**
 * 使用反射获取所有的key
 */
@RestController
class RequestController11 {
    @PostMapping("/getJsonToTransferMap")
    public Map<String, Object> getJsonToTransferString(@RequestBody User user) throws Exception {
        Map<String, Object> map = new HashMap<>();

        Class aClass = user.getClass();
        Field[] fields = aClass.getDeclaredFields();

        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true);
            map.put(field.getName(), field.get(user));
        }
        return map;
    }
}

/**
 * 路径参数
 * 使用@pathVariable注解
 */
@RestController
class RequestController12 {
    @RequestMapping("/pathParam/{id}")
    public String pathParam(@PathVariable Integer id) {
        System.out.println("路径参数" + id);
        return "ok";
    }
}

/**
 *
 */
@RestController
class RequestController13 {
    @RequestMapping("/array")
    public String array(@RequestBody String[] array) {
        System.out.println(Arrays.toString(array));
        return "ok";
    }
}

/**
 * 统一使用实体类进行接口返回封装
 *
 */
@RestController
class RequestController14 {
    @RequestMapping("/resultObject")
    public Result array(@RequestBody Map<Object, Object> map) {
        System.out.println(map);
        return Result.success("1212121");
    }
}