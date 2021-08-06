package ru.chuvashov.course.lesson10.hw10.account.controllers;

import ru.chuvashov.course.lesson10.hw10.account.model.Account;
import ru.chuvashov.course.lesson10.hw10.account.model.exceptions.UnknownAccountException;
import ru.chuvashov.course.lesson10.hw10.account.repository.exception.DbHibernateException;
import ru.chuvashov.course.lesson10.hw10.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Chuvashov Sergey
 */

@RestController()
public class RestAccountController {

    private final AccountService service;

    @Autowired
    public RestAccountController (AccountService service) {
        this.service = service;
    }

    /**
     * Возвращает аккаунт id которого указано в параметре.
     *
     * @param number - Идентификатор аккаунта.
     * @return ответ для HTTP c указанным в параметре аккаунтом в JSON.
     * @throws DbHibernateException    - В случае каких-либо проблем с БД.
     * @throws UnknownAccountException - В случае, если аккаунт с указанным ID не существует.
     */
    @GetMapping("/account/{number}")
    @ResponseBody
    public ResponseEntity<Account>
    getAccount(@PathVariable("number") String number)
            throws DbHibernateException, UnknownAccountException {
        Account account = service.getAccount(Integer.parseInt(number));
        return ResponseEntity.accepted().body(account);
    }
}