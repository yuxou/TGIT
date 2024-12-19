package controller.expense;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.service.ExpenseManager;

public class DeleteExpenseController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int expenseId = Integer.parseInt(request.getParameter("expenseId"));

        ExpenseManager expenseManager = ExpenseManager.getInstance();
        expenseManager.deleteExpense(expenseId);

        return "/myPage.jsp";
    }
}
