package com.henry.springboot.ioc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("accountService3")
public class AccountService3 {
    private AccountDao accountDao;

    /**
     * Constructor 注入，参数名必须与bean名相同
     * @param accountDao
     */
    @Autowired
    public AccountService3(AccountDao accountDao){
        this.accountDao = accountDao;
    }

    public void save(Account account){
        accountDao.save(account);
    }

    public Account findById(Integer id){
        return accountDao.findById(id);
    }
}
