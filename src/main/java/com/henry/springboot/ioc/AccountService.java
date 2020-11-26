package com.henry.springboot.ioc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 演示 service autoWired 注入
 */
@Component("accountService")
public class AccountService {
    @Autowired
    private AccountDao accountDao;

    public void save(Account account){
        accountDao.save(account);
    }

    public Account findById(Integer id){
        return accountDao.findById(id);
    }
}
