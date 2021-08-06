package ru.chuvashov.course.lesson10.hw10.account.controllers;

import ru.chuvashov.course.lesson10.hw10.account.repository.DbAccountHibernate;
import ru.chuvashov.course.lesson10.hw10.account.repository.exception.DbHibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.chuvashov.course.lesson10.hw10.account.service.AccountService;

/**
 * @author Chuvashov Sergey
 */

@Controller
public class AccountController {

    private final AccountService service;

    @Autowired
    public AccountController(DbAccountHibernate hib, AccountService service) {
        this.service = service;
    }

    /**
     * Переводит на страницу, где указаны все аккаунты.
     *
     * @return - JSP страница со списком всех аккаунтов.
     * @throws DbHibernateException - В случае каких-либо проблем с БД.
     */
    @GetMapping(value = "/accounts")
    public String getListAccounts(Model model) throws DbHibernateException {
        model.addAttribute("accounts", service.getAllAccounts());
        return "listAccounts";
    }
}
