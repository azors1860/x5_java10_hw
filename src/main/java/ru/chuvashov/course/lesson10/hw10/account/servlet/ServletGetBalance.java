package ru.chuvashov.course.lesson10.hw10.account.servlet;

import ru.chuvashov.course.lesson10.hw10.account.repository.exception.DbHibernateException;
import ru.chuvashov.course.lesson10.hw10.account.service.AccountService;
import ru.chuvashov.course.lesson10.hw10.account.service.AccountServiceImpl;
import ru.chuvashov.course.lesson10.hw10.account.model.Account;
import ru.chuvashov.course.lesson10.hw10.account.model.exceptions.UnknownAccountException;
import ru.chuvashov.course.lesson10.hw10.account.repository.Repository;
import ru.chuvashov.course.lesson10.hw10.account.repository.DbAccountHibernate;
import ru.chuvashov.course.lesson10.hw10.account.servlet.exceptions.AccountsServletException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Chuvashov Sergey
 */

public class ServletGetBalance extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }

    /**
     * Возвращает страницу с балансом, номер аккаунта которого указан в реквесте.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Repository<Account> accountRepository = new DbAccountHibernate();
        String parameter = req.getParameter("id");
        int balance = 0;
        int id = 0;
        try {
            id = Integer.parseInt(req.getParameter("id"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            throw new AccountsServletException(
                    String.format("Произошла ошибка при преобразовании '%s' в число", parameter), e);
        }

        try {
            AccountService accountService = new AccountServiceImpl(accountRepository);
            balance = accountService.getBalance(id);
        } catch (DbHibernateException e) {
            e.printStackTrace();
            throw new AccountsServletException("Произошла ошибка c БД при получении баланса", e);
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            throw new AccountsServletException(String.format
                    ("Произошла ошибка при получении баланса: аккаунт с указанным id (%s) не найнен", id), e);
        }

        resp.getWriter().write(String.format("<html>\n" +
                "<head>\n" +
                "<link rel=\"stylesheet\"\n" +
                "href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\"\n" +
                "integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">\n" +
                "</head>\n" +
                "<body>\n" +
                "<div class=\"container\">\n" +
                "    <a href=\"/accounts\" class=\"bth btn-primary\">home</a>\n" +
                "    <a href=\"/form/register\" class=\"bth btn-primary\">register</a>\n" +
                "    <a href=\"/form/deposite\" class=\"bth btn-primary\">deposite</a>\n" +
                "    <a href=\"/form/withdraw\" class=\"bth btn-primary\">withDraw</a>\n" +
                "    <a href=\"/form/transfer\" class=\"bth btn-primary\">transfer</a>\n" +
                "    <br>\n" +
                "    <br>\n" +
                "    <p>Balance id:%s = %s </p>\n" +
                "</div>\n" +
                "</body>\n" +
                "</html>", id, balance));
    }

    @Override
    public void destroy() {
        super.destroy();
    }

}
