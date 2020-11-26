package com.henry.springboot.ioc;

import com.henry.springboot.SpringBootSummary;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component("accountDao")
public class AccountDao {
    public void save(Account account){
        System.out.println("inset account(name,money) values("+ account.getName() +"," + account.getMoney()+")");
    }

    public Account findById(Integer id){
        //通过Annotation获取ApplicationContext
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringBootSummary.class);
        return (Account)ac.getBean("account"+id);
    }
}
