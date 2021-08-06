package ru.chuvashov.course.lesson10.hw10.account.service;


import ru.chuvashov.course.lesson10.hw10.account.model.Account;
import ru.chuvashov.course.lesson10.hw10.account.model.exceptions.NotEnoughMoneyException;
import ru.chuvashov.course.lesson10.hw10.account.model.exceptions.UnknownAccountException;
import ru.chuvashov.course.lesson10.hw10.account.repository.exception.DbHibernateException;

import java.util.List;


/**
 * @author Chuvashov Sergey
 */
public interface AccountService {

    /**
     * Снять с указанного счёта указанную сумму денег.
     *
     * @param accountId - Идентификатор аккаунта.
     * @param amount    - Сумма, которая будет списана со счёта.
     * @throws NotEnoughMoneyException - В случае, если указанная в параметре сумма имеет отрицательное значение,
     *                                 либо если после вычета со счёта аккаунта на аккаунте будет отрицательеый баланс.
     * @throws UnknownAccountException - В случае, если указанный параметр имеет отрицательное значение,
     *                                 либо если аккаут с указанным идентификатором не будет найден в списке.
     * @throws DbHibernateException    - В случае, если возникнут проблемы с выполнениями операций
     *                                 связанные с базой данных.
     */
    void withDraw(int accountId, int amount)
            throws NotEnoughMoneyException, UnknownAccountException, DbHibernateException;

    /**
     * Возвращает баланс указанного аккаунта в консоль.
     *
     * @param accountId - Идентификатор аккаунта.
     * @return - Баланс аккаунта.
     * @throws UnknownAccountException - В случае, если указанный параметр имеет отрицательное значение,
     *                                 либо если аккаут с указанным идентификатором не будет найден в списке.
     * @throws DbHibernateException    - В случае, если возникнут проблемы с выполнениями операций
     *                                 связанные с базой данных.
     */
    int getBalance(int accountId)
            throws UnknownAccountException, DbHibernateException;

    /**
     * Пополнить указанный счёт на указанную сумму денег.
     *
     * @param accountId - Идентификатор аккаунта.
     * @param amount    - Сумма, на которую будет пополнен счёт.
     * @throws NotEnoughMoneyException - В случае, если указанная в параметре сумма имеет отрицательное значение.
     * @throws UnknownAccountException - В случае, если указанный параметр имеет отрицательное значение,
     *                                 либо если аккаут с указанным идентификатором не будет найден в списке.
     * @throws DbHibernateException    - В случае, если возникнут проблемы с выполнениями операций
     *                                 связанные с базой данных.
     */
    void deposit(int accountId, int amount)
            throws NotEnoughMoneyException, UnknownAccountException, DbHibernateException;

    /**
     * Перевести деньги с одного счёта на другой (указаны в параметре) на указанную в параметре сумму.
     *
     * @param from   - Идентификатор аккаунта, со счёта которого будут списаны деньги.
     * @param to     - Идентификатор аккаунта, на счёт которого будут зачислены деньги.
     * @param amount - Сумма денег для перевода.
     * @throws NotEnoughMoneyException - В случае, если указанная в параметре сумма имеет отрицательное значение,
     *                                 либо если после вычета со счёта аккаунта на аккаунте будет отрицательеый баланс.
     * @throws UnknownAccountException - В случае, если указанный параметр имеет отрицательное значение,
     *                                 либо если аккаут с указанным идентификатором не будет найден в списке.
     * @throws DbHibernateException    - В случае, если возникнут проблемы с выполнениями операций
     *                                 связанные с базой данных.
     */
    void transfer(int from, int to, int amount)
            throws NotEnoughMoneyException, UnknownAccountException, DbHibernateException;

    /**
     * Возвращает все аккаунты из репозитория.
     *
     * @return List со всеми аккаунтами.
     * @throws DbHibernateException - В случае проблем связанные с базой данных.
     */
    List<Account> getAllAccounts() throws DbHibernateException;

    /**
     * Возвращает экзампляр аккаунта, идентифиактор которого указан в параметре.
     *
     * @param id - Идентифиактор аккаунта.
     * @return - Экземпляр аккаунта.
     * @throws DbHibernateException - В случае проблем связанные с базой данных.
     * @throws UnknownAccountException - В случае, если параметр (id) указан некорректно.
     */
    public Account getAccount(int id) throws DbHibernateException, UnknownAccountException;
}
