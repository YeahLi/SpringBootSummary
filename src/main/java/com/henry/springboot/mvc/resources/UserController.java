package com.henry.springboot.mvc.resources;

import com.henry.springboot.mvc.beans.User;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * 总结：
 *      1. Controller: @Controller + @ResponseBody
 *      2. Path mapping: @RequestMapping 作用于Controller或者方法上
 *      3. 传参：
 *          a. @PathVariable(value = "占位符")
 *          b. @RequestParam(value = "param_in_url", rquired=true)
 *          c. @RequestBody User user --> Used for POST and PUT method
 *          d. @RequestParam Map<String,String> map --> Used for get form data
 *      4. Servlet API 作用参数
 *      5. Cookie and Session
 */

@RestController //equals @Controller + @ResponseBody
//@RequestMapping 作用在Controller上，表示Map整个一个controller
@RequestMapping(path = "/mvc_demo")
public class UserController {
    @RequestMapping(path = "/user/{id}", method = RequestMethod.GET)
    //@PathVariable(value = "id") 拥有绑定url中的占位符的。例如:url中有/delete/{id}，{id}就是占位符
    public User getUserById(@PathVariable(value = "id") String id){
        System.out.println("Find user by Id "+ id);
        return new User(id, "Henry Li", 30);
    }

    @RequestMapping(path = "/user", method = RequestMethod.GET)
    //@RequestParam(value = "name", required = true) 把请求中的指定名称的参数传递给控制器中的形参赋值
    public User getUserByName(@RequestParam(value = "name", required = true) String name){
        System.out.println("Find user by name "+name);
        return new User("1", name, 30);
    }

    @RequestMapping(path = "/user", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    //@RequestBody 用于获取paylaod的内容(注意:get方法不可以)
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User newUser){
        System.out.println("Create a new user: "+ newUser);
        return newUser;
    }

    @RequestMapping(path = "/user", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    //使用@RequestParam 和 Map 获取表单数据
    public User createUserWithForm(@RequestParam Map<String,String> map){
        String name = map.get("name");
        String id = map.get("id");
        int age = Integer.valueOf(map.get("age"));
        User newUser = new User(id, name, age);
        System.out.println("Create a new user: "+ newUser);
        return newUser;
    }

    @RequestMapping(path = "/user", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public User updateUser(@RequestBody User updateUser){
        System.out.println("User "+ updateUser.getName() +" will be updated to"+": "+updateUser);
        return updateUser;
    }

    //传入ServletAPI demo
    @RequestMapping("/testSetRequestAttrs")
    public void setModel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置request域对象
        request.setAttribute("username", "泰斯特");
        request.setAttribute("password","123456");
        request.setAttribute("age", 31);
        //！request的转发操作
        request.getRequestDispatcher("/mvc_demo/testGetModel").forward(request,response);
    }

    @RequestMapping("/testGetRequestAttrs")
    public void setModel(HttpServletRequest request){
        //读取request域对象
        System.out.println(request.getAttribute("username"));
        System.out.println(request.getAttribute("password"));
        System.out.println(request.getAttribute("age"));
    }

    /**
     *  CookieDemo
     */
    //@CookieValue Demo
    @RequestMapping("/useCookieValue")
    public String useCookieValue(@CookieValue(value="JSESSIONID",required=false) String cookieValue){
        System.out.println(cookieValue);
        return "success";
    }

    //Cookie
    @RequestMapping(value = "/setCookies",method = RequestMethod.GET)
    public  String setCookies(HttpServletResponse response){
        Cookie cookie=new Cookie("sessionId","CookieTestInfo");
        response.addCookie(cookie);
        return "添加cookies信息成功";
    }

    @RequestMapping(value = "/getCookies",method = RequestMethod.GET)
    public  String getCookies(HttpServletRequest request){
        Cookie[] cookies =  request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("sessionId")){
                    return cookie.getValue();
                }
            }
        }
        return  null;
    }

    /**
     * Session Demo
     */
    @RequestMapping(path = "/setSession", method = RequestMethod.GET)
    public void setSession(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.setAttribute("username", "abc");
        session.setAttribute("password", "123");
        System.out.println(session.getId());
    }

    @RequestMapping(path = "/getSession", method = RequestMethod.GET)
    public void getSession(HttpSession session){
        System.out.println(session.getAttribute("username"));
        System.out.println(session.getAttribute("password"));
    }
}
