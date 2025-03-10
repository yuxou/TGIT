package model.dao;

import java.sql.*;
import java.util.*;
import model.domain.Expense;

public class ExpenseDAO {
    private JDBCUtil jdbcUtil = null;

    public ExpenseDAO() {
        jdbcUtil = new JDBCUtil();
    }

    /**
     * 지출 추가
     * @param expense
     * @throws Exception
     */
    public void addExpense(Expense expense) throws Exception {
        String sql = "INSERT INTO expenses (place, expenseDate, category, cost, notes) VALUES (?, ?, ?, ?, ?)";
        Object[] params = {
            expense.getPlace(),
            expense.getSqlExpenseDate(),
            expense.getCategory(),
            expense.getCost(),
            expense.getNotes()
        };

        try {
            jdbcUtil.setSqlAndParameters(sql, params);
            jdbcUtil.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.close();
        }
    }

    /**
     * 지출 수정
     * @param expense
     * @throws Exception
     */
    public void editExpense(Expense expense) throws Exception {
        String sql = "UPDATE expenses SET place = ?, expenseDate = ?, category = ?, cost = ?, notes = ? WHERE expenseId = ?";
        Object[] params = {
            expense.getPlace(),
            expense.getSqlExpenseDate(),
            expense.getCategory(),
            expense.getCost(),
            expense.getNotes(),
            expense.getExpenseId()
        };

        try {
            jdbcUtil.setSqlAndParameters(sql, params);
            jdbcUtil.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.close();
        }
    }

    /**
     * 지출 삭제 
     * @param expenseId
     * @throws Exception
     */
    public void deleteExpense(int expenseId) throws Exception {
        String sql = "DELETE FROM expenses WHERE expenseId = ?";
        Object[] params = {expenseId};

        try {
            jdbcUtil.setSqlAndParameters(sql, params);
            jdbcUtil.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.close();
        }
    }

    /**
     * 전체 지출 조회
     * @param planId
     * @return
     */
    public double getTotalExpense(int planId) {
        String sql = "SELECT SUM(cost) AS totalCost FROM expenses WHERE planId = ?";
        Object[] params = {planId};

        double total = 0;
        try {
            jdbcUtil.setSqlAndParameters(sql, params);
            ResultSet rs = jdbcUtil.executeQuery();
            if (rs.next()) {
                total = rs.getDouble("totalCost");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.close();
        }
        return total;
    }

    /**
     * 경비 분할
     * @param expenseId
     * @param numPeople
     * @return
     */
    public Map<String, Double> splitExpense(int expenseId, int numPeople) {
        String sql = "SELECT cost FROM expenses WHERE expenseId = ?";
        Object[] params = {expenseId};

        Map<String, Double> splitMap = new HashMap<>();
        try {
            jdbcUtil.setSqlAndParameters(sql, params);
            ResultSet rs = jdbcUtil.executeQuery();
            if (rs.next()) {
                double cost = rs.getDouble("cost");
                double splitAmount = cost / numPeople;
                for (int i = 1; i <= numPeople; i++) {
                    splitMap.put("인당 " + i, splitAmount);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.close();
        }
        return splitMap;
    }
}