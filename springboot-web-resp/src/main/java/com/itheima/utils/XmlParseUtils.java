package com.itheima.utils;

import org.dom4j.Document;

import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

/**
 * 解析XML文件
 */
public class XmlParseUtils {
   public static <T> List<T> parse (String file, Class<T> TargetClass) throws Exception {
       ArrayList<T> list = new ArrayList<>();

       SAXReader saxReader = new SAXReader();
       Document document = saxReader.read(file);
       Element rootElement = document.getRootElement();

       List<Element> elements = rootElement.elements("emp");

       for (Element element : elements) {
           String name = element.elementText("name");
           String age = element.elementText("age");
           String gender = element.elementText("gender");
           String job = element.elementText("job");
           String image = element.elementText("image");

           Constructor<T> constructor = TargetClass.getDeclaredConstructor(String.class, Integer.class, String.class, String.class, String.class);
           constructor.setAccessible(true);
           T object = (T) constructor.newInstance(name, Integer.parseInt(age), gender, job, image);
           list.add(object);
       }
       return list;
   }
}