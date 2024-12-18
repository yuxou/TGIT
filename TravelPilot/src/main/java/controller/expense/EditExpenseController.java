package controller.expense;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.domain.Expense;
import model.service.ExpenseManager;

import java.sql.Date;

public class EditExpenseController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int expenseId = Integer.parseInt(request.getParameter("expenseId"));
        String place = request.getParameter("place");
        Date expenseDate = Date.valueOf(request.getParameter("expenseDate"));
        String category = request.getParameter("category");
        double cost = Double.parseDouble(request.getParameter("cost"));
        String notes = request.getParameter("notes");

        Expense updatedExpense = new Expense(expenseId, place, expenseDate, category, cost, notes);

        ExpenseManager expenseManager = ExpenseManager.getInstance();
        expenseManager.editExpense(updatedExpense);

        return "/myPage.jsp";
    }
}
