package ru.chuvashov.course.lesson10.hw10.account.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import ru.chuvashov.course.lesson10.hw10.account.model.Account;
import ru.chuvashov.course.lesson10.hw10.account.repository.exception.DbHibernateException;
import ru.chuvashov.course.lesson10.hw10.account.service.AccountService;
import ru.chuvashov.course.lesson10.hw10.account.model.exceptions.NotEnoughMoneyException;
import ru.chuvashov.course.lesson10.hw10.account.model.exceptions.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Chuvashov Sergey
 */
@RestController
public class RestBalanceUppingController {

    private final AccountService service;

    @Autowired
    public RestBalanceUppingController(AccountService service) {
        this.service = service;
    }

    /**
     * Увеличивает счёт аккаунта на сумму указанную в соответствующем параметре.
     *
     * @param id     - Идентификатор аккаунта.
     * @param amount - Сумма денег.
     * @return - ответ для HTTP cо списком всех аккаунтов в JSON.
     * @throws DbHibernateException            - В случае каких-либо проблем с БД.
     * @throws NotEnoughMoneyException - В случае, если сумма денег в параметре будет иметь отрицательное значение.
     * @throws UnknownAccountException - В случае, если аккаунт с указанным ID не существует.
     */
    @PostMapping("account/plus")
    public ResponseEntity<List<Account>> updateAccountBalanceUpping
    (@RequestParam("id") int id,
     @RequestParam("amount") int amount)
            throws DbHibernateException, NotEnoughMoneyException, UnknownAccountException {

        service.deposit(id, amount);
        return ResponseEntity.accepted().body(service.getAllAccounts());
    }
}
