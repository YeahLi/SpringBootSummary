package com.henry.springboot.ioc;

import com.henry.springboot.SpringBootSummary;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * SpringBoot 与Junit整合
 */

//SpringRunner继承自SpringJUnit4ClassRunner
@RunWith(SpringRunner.class)
//@SpringBootTest的属性指定的是引导类的字节码对象
@SpringBootTest(classes = SpringBootSummary.class)
public class IoCTest {
    @Autowired
    private IoCDemoMain ioCDemoMain;

    @Test
    public void testIoCDemoMain(){
        ioCDemoMain.testIoC();
    }
}
