package com.bjpowernode.test;

import com.bjpowernode.domain.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class Test1 {
    public static void main(String[] args) {
        String resource = "mybatis-config.xml";
        //输入流
        InputStream inputStream = null;
        try {
            //通过加载Mybatis的主配置文件mybatis-config.xml,创建输入流对象
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*
        sqlSessionFactoryBuilder:SqlSessionFactory的建造者
        通过该建造者对象调用建造方法，为我们创建一个SQLSessionFactory对象
        SQLSessionFactory对象唯一作用就是为我们创建SqlSession对象
         */
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);

        /*
        我们未来所有的操作，使用的都是SqlSession对象
        例如增删改查，处理事务等等，都是统一使用session对象来完成
         */
        SqlSession session = sqlSessionFactory.openSession();

        /*
       需求：根据id查单条

       如果取得的是单条记录，我们调用selectOne方法
       参数1：：根据命名空间.sqlId的形式找到我们需要使用的SQL语句
       参数2：我们要为sql语句中传递的参数
         */
        Student s = session.selectOne("test1.getById","A0001");   //跑到这里出现了bug
        System.out.println(s);//在Student类中写了toString方法，这里就可以直接输出s
        session.close();
    }
}