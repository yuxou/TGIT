package controller.expense;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.domain.Expense;
import model.service.ExpenseManager;

public class EditExpenseController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int expenseId = Integer.parseInt(request.getParameter("expenseId"));
        String place = request.getParameter("place");
        String[] expenseDateParts = request.getParameter("expenseDate").split("-");
        int year = Integer.parseInt(expenseDateParts[0]);
        int month = Integer.parseInt(expenseDateParts[1]);
        int day = Integer.parseInt(expenseDateParts[2]);
        String category = request.getParameter("category");
        double cost = Double.parseDouble(request.getParameter("cost"));
        String notes = request.getParameter("notes");

        Expense updatedExpense = new Expense(expenseId, place, year, month, day, category, cost, notes);

        ExpenseManager expenseManager = ExpenseManager.getInstance();
        expenseManager.editExpense(updatedExpense);

        return "/myPage.jsp";
    }
}
