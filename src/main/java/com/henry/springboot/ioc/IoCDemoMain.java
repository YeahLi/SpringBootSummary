package com.henry.springboot.ioc;

import com.henry.springboot.SpringBootSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Spring IoC 总结：
 * 1. Create Spring Bean:
 * 方法一：add @Component(@Service, @Controller, @Repository) on class
 * 方法二：Under @Configuration class, use @Bean to decorate a method:
 *          @Bean
 *          public Account createAccount1() {
 *              return new Account(1, "Henry", 500.5f);
 *          }
 *  2. Get Spring Bean from Context
 *          ApplicationContext ac = new AnnotationConfigApplicationContext(SpringBootSummary.class);
 *          Account account1 = (Account)ac.getBean("account1");
 *          Person person1 = ac.getBean(Person.class);
 *
 *  3. Dependency Injection:
 *      Note: 如果要注入List或Map类型，
 *  类型一：Spring Bean的注入使用 @Autowired, 这些都是根据参数的类型注入。
 *      方法一：标注在成员变量上，如AccountService
 *          @Autowired
 *          private AccountDao accountDao;
 *      方法二：标注在Set方法上，如AccountService2
 *          @Autowired
 *          public void setAccountDao(AccountDao accountDao){
 *              this.accountDao = accountDao;
 *          }
 *      方法三：标注在Constructor上，如AccountService3
 *          @Autowired
 *          public AccountService3(AccountDao accountDao){
 *              this.accountDao = accountDao;
 *          }
 *  类型二：使用@Value注入基本类型成员， 如Person类
 *          @Component
 *          public class Person {
 *              @Value("Henry Li")
 *              String name;
 *          }
 *  类型三：@Qualifier("bean_id")
 *        在自动按照类型注入的基础之上，再按照 Bean 的 id 注入。它在给字段注入时不能独立使用，必须和
 *        @Autowire 一起使用;但是给方法参数注入时，可以独立使用。
 *
 *  类型四：@Resource(name = "bean_id")
 *        直接按照 Bean 的 id 注入。它也只能注入其他 bean 类型。
 *
 *  4. @Scope 指定 bean 的作用范围。取值:singleton prototype request session globalsession
 *
 *  5. @PostConstruct 和 @PreDestroy
 *      Spring Bean生命周期的执行顺序： 构造方法 -> @Autowired -> @PostConstruct -> @PreDestroy -> Destroy
 *      @PostConstruct使用场景:
 *      a. 在Spring Bean生成后，给其成员如List，Map等赋值。
 *      b. 在Bean的初始化操作中，有时候会遇到调用其他Bean的时候报空指针错误。
 *         这时候就可以将调用另一个Bean的方法这个操作放到@PostConstruct注解的方法中，将其延迟执行。
 *         示例：PostConstructTest1 and PostConstructTest2
 */

@Service
public class IoCDemoMain {
    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountService2 accountService2;

    @Autowired
    private AccountService3 accountService3;

    @Autowired
    private PostConstructTest1 postConstructTest11;

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

        //6. PostConstruct演示
        //看看有没有打印 "hello, i am PostConstructTest2"
    }
}
