package ru.chuvashov.course.lesson10.hw10.account.controllers;

import ru.chuvashov.course.lesson10.hw10.account.model.Account;
import ru.chuvashov.course.lesson10.hw10.account.model.exceptions.NotEnoughMoneyException;
import ru.chuvashov.course.lesson10.hw10.account.repository.Repository;
import ru.chuvashov.course.lesson10.hw10.account.repository.exception.DbHibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Chuvashov Sergey
 */

@Controller
public class RegisterController {

    private final Repository<Account> accountRepository;

    @Autowired
    public RegisterController(Repository<Account> accountRepository) {
        this.accountRepository = accountRepository;
    }

    @GetMapping(value = "/form/register")
    public String formRegistration() {
        return "register";
    }

    /**
     * Сохраняет (создаёт) новый аккаунт.
     *
     * @param holder - Имя владельца счёта.
     * @param amount - Сумма денег на счёте.
     * @return - JSP страница со списком всех аккаунтов.
     * @throws DbHibernateException - В случае каких-либо проблем с БД.
     * @throws NotEnoughMoneyException - В случае, если сумма денег в параметре будет иметь отрицательное значение.
     */
    @PostMapping("account/register")
    public String register
            (@RequestParam("holder") String holder,
             @RequestParam("amount") int amount) throws NotEnoughMoneyException, DbHibernateException {
        Account account = new Account();
        account.setHolder(holder);
        account.setAmount(amount);
        accountRepository.create(account);
        return "redirect:/accounts";
    }

}
