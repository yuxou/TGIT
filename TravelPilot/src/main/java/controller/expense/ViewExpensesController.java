package controller.expense;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.service.ExpenseManager;

public class ViewExpensesController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int planId = Integer.parseInt(request.getParameter("planId"));

        ExpenseManager expenseManager = ExpenseManager.getInstance();
        double totalExpense = expenseManager.getTotalExpense(planId);

        request.setAttribute("totalExpense", totalExpense);
        return "/viewExpenses.jsp";
    }
}
