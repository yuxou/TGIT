package controller.expense;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.domain.Expense;
import model.service.ExpenseManager;

import java.sql.Date;

public class AddExpenseController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String place = request.getParameter("place");
        Date expenseDate = Date.valueOf(request.getParameter("expenseDate")); // yyyy-MM-dd 형식
        String category = request.getParameter("category");
        double cost = Double.parseDouble(request.getParameter("cost"));
        String notes = request.getParameter("notes");

        Expense expense = new Expense(0, place, expenseDate, category, cost, notes);

        ExpenseManager expenseManager = ExpenseManager.getInstance();
        expenseManager.addExpense(expense);

        return "/myPage.jsp"; 
    }
}