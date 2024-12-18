package controller.expense;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.domain.Expense;
import model.service.ExpenseManager;

import java.util.List;

public class ViewExpensesController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int planId = Integer.parseInt(request.getParameter("planId"));

        ExpenseManager expenseManager = ExpenseManager.getInstance();
        List<Expense> expenses = expenseManager.getExpensesByPlan(planId);

        request.setAttribute("expenses", expenses);
        return "/viewExpenses.jsp"; // 지출 목록 JSP 페이지로 이동
    }
}