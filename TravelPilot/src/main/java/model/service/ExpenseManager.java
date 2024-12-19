package model.service;

import java.util.Map;

import model.dao.ExpenseDAO;
import model.domain.Expense;

public class ExpenseManager {
	private static final ExpenseManager instance = new ExpenseManager(new ExpenseDAO());
    private ExpenseDAO expenseDAO;

    // 생성자
    public ExpenseManager(ExpenseDAO expenseDAO) {
        this.expenseDAO = expenseDAO;
    }

    public static ExpenseManager getInstance() {
        return instance;
    }
   
    /**
     * 지출 추가
     * @param expense
     */
    public void addExpense(Expense expense) {
        try {
            expenseDAO.addExpense(expense);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to add expense");
        }
    }

    /**
     * 지출 수정
     * @param expense
     */
    public void editExpense(Expense expense) {
        try {
            expenseDAO.editExpense(expense);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to edit expense");
        }
    }

    /**
     * 지출 삭제
     * @param expenseId
     */
    public void deleteExpense(int expenseId) {
        try {
            expenseDAO.deleteExpense(expenseId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to delete expense");
        }
    }

    /**
     * 특정 계획의 전체 지출 조회
     * @param planId
     * @return 총 지출
     */
    public double getTotalExpense(int planId) {
        try {
            return expenseDAO.getTotalExpense(planId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to get total expense");
        }
    }

    /**
     * 경비 분할
     * @param expenseId
     * @param numPeople
     * @return 분할된 경비 정보
     */
    public Map<String, Double> splitExpense(int expenseId, int numPeople) {
        try {
            return expenseDAO.splitExpense(expenseId, numPeople);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to split expense");
        }
    }
}