package com.henry.springboot.ioc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 演示Set方法注入
 */
@Component("acountService2")
public class AccountService2 {

    private AccountDao accountDao;

    @Autowired
    public void setAccountDao(AccountDao accountDao){
        this.accountDao = accountDao;
    }

    public void save(Account account){
        accountDao.save(account);
    }

    public Account findById(Integer id){
        return accountDao.findById(id);
    }
}
