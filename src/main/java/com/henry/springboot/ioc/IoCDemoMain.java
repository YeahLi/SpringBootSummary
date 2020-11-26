package com.henry.springboot.ioc;

import com.henry.springboot.SpringBootSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

/**
 * Spring IoC 总结：
 * 1. Create Spring Bean:
 * 方法一：add @Component(@Service, @Controller, @Repository) on class
 * 方法二：Under @Configuration class, use @Bean to decorate a method:
 *          @Bean
 *          public Account createAccount1() {
 *              return new Account(1, "Henry", 500.5f);
 *          }
 *  2. Get Spring Bean from
 */

@Service
public class IoCDemoMain {
    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountService2 accountService2;

    @Autowired
    private AccountService3 accountService3;

    public void testIoC(){
        //1. @Configuration 和 @Bean 创建Spring Bean的演示：
        // 从容器中获取 account1 和 account2
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringBootSummary.class);
        Account account1 = (Account)ac.getBean("account1");
        System.out.println(account1);
        Account account2 = (Account)ac.getBean("account2");
        System.out.println(account2);

        //2. @Component 创建Spring Bean 和 @Value 注入String变量的演示：
        // 从容器中获取person
        Person person1 = ac.getBean(Person.class);
        System.out.println(person1);
        Person person2 = (Person)ac.getBean("person");
        System.out.println(person2);

        //3. @Autowire注入AccountDao的演示：
        accountService.save(account1);

        //4. Set方法注入AccountDao的演示：
        accountService2.save(account1);

        //5. Constructor注入AccountDao的演示
        accountService3.save(account1);
    }
}
