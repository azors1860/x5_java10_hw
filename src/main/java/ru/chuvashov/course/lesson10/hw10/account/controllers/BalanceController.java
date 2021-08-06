package ru.chuvashov.course.lesson10.hw10.account.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import ru.chuvashov.course.lesson10.hw10.account.repository.exception.DbHibernateException;
import ru.chuvashov.course.lesson10.hw10.account.service.AccountService;
import ru.chuvashov.course.lesson10.hw10.account.model.exceptions.NotEnoughMoneyException;
import ru.chuvashov.course.lesson10.hw10.account.model.exceptions.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Chuvashov Sergey
 */

@Controller
public class BalanceController {

    private final AccountService service;

    @Autowired
    public BalanceController(AccountService service) {
        this.service = service;
    }

    @GetMapping(value = "/form/transfer")
    public String formTransfer() {
        return "transfer";
    }

    @GetMapping(value = "/form/withdraw")
    public String formWithdraw() {
        return "withDraw";
    }

    //метод для увеличения счёта находится в соответствующем классе REST (согласно условиям должны быть методы на REST)
    @GetMapping(value = "/form/deposite")
    public String formDeposite() {
        return "deposite";
    }

    /**
     * Перевод денег с одного счёта на другой.
     *
     * @param idFrom - Идентификатор аккаунта со счёта которого будут перечислены деньги.
     * @param idTo   - Идентификатор аккаунта на счёт которого будут перечислены деньги.
     * @param amount - Сумма для перевода.
     * @return - JSP страница со списком всех аккаунтов.
     * @throws NotEnoughMoneyException - В случае, если сумма денег в параметре будет иметь отрицательное значение,
     *                                 либо если на счёте-отправителе после операции будет отрицательное значение.
     * @throws DbHibernateException    - В случае каких-либо проблем с БД.
     * @throws UnknownAccountException - В случае, если аккаунты с указанными ID не будут найдены.
     */
    @PostMapping("account/transfer")
    public String balanceTransferAccounts
    (@RequestParam("idFrom") int idFrom,
     @RequestParam("idTo") int idTo,
     @RequestParam("amount") int amount)
            throws NotEnoughMoneyException, DbHibernateException, UnknownAccountException {

        service.transfer(idFrom, idTo, amount);
        return "redirect:/accounts";
    }

    /**
     * Уменьшает счёт на указанную сумму.
     *
     * @param id     - Идентификатор аккаунта счёт которого будут уменьшен.
     * @param amount - На сколько будет уменьшен счёт.
     * @return - JSP страница со списком всех аккаунтов.
     * @throws NotEnoughMoneyException - В случае, если сумма денег в параметре будет иметь отрицательное значение,
     *                                 либо если на счёте-отправителе после операции будет отрицательное значение.
     * @throws DbHibernateException    - В случае каких-либо проблем с БД.
     * @throws UnknownAccountException - В случае, если аккаунты с указанными ID не будут найдены.
     */
    @PostMapping("account/withdraw")
    public String withdrawAmountAccount
    (@RequestParam("id") int id,
     @RequestParam("amount") int amount)
            throws NotEnoughMoneyException, DbHibernateException, UnknownAccountException {

        service.withDraw(id, amount);
        return "redirect:/accounts";
    }
}
