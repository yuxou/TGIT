package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.domain.Checklist;
import model.domain.ChecklistItem;

/**
 * Checklist 테이블에서 체크리스트와 항목 정보를 관리하는 DAO 클래스
 */
public class ChecklistDAO {
    private JDBCUtil jdbcUtil = null;

    public ChecklistDAO() {
        jdbcUtil = new JDBCUtil(); // JDBCUtil 객체 생성
    }

    /**
     * 새로운 체크리스트를 생성하고 ID를 반환
     * @param checklist
     * @return
     * @throws SQLException
     */
    public int addChecklist(Checklist checklist) throws SQLException {
        String sql = "INSERT INTO checklists (category) VALUES (?)";
        Object[] params = new Object[] { checklist.getCategory() };
        jdbcUtil.setSqlAndParameters(sql, params);

        String[] key = { "checklist_id" }; // 자동 생성되는 PK 컬럼
        try {
            jdbcUtil.executeUpdate(key);
            ResultSet rs = jdbcUtil.getGeneratedKeys();
            if (rs.next()) {
                int generatedId = rs.getInt(1); // 생성된 체크리스트 ID
                return generatedId;
            }
        } catch (Exception e) {
            jdbcUtil.rollback();
            e.printStackTrace();
        } finally {
            jdbcUtil.commit();
            jdbcUtil.close();
        }
        return -1; // 실패 시 -1 반환
    }

    /**
     * 특정 체크리스트에 항목 추가
     * @param checklistId
     * @param item
     * @throws SQLException
     */
    public void addItem(int checklistId, ChecklistItem item) throws SQLException {
        String sql = "INSERT INTO checklist_items (name, completed, checklist_id) VALUES (?, ?, ?)";
        Object[] params = new Object[] { item.getName(), item.isCompleted(), checklistId };
        jdbcUtil.setSqlAndParameters(sql, params);

        try {
            jdbcUtil.executeUpdate();
        } catch (Exception e) {
            jdbcUtil.rollback();
            e.printStackTrace();
        } finally {
            jdbcUtil.commit();
            jdbcUtil.close();
        }
    }
    
    /**
     * 체크리스트 삭제
     * @param checklistId
     * @throws SQLException
     */
    public void deleteChecklist(int checklistId) throws SQLException {
        // 체크리스트 항목 삭제
        String deleteItemsSql = "DELETE FROM checklist_items WHERE checklist_id = ?";
        jdbcUtil.setSqlAndParameters(deleteItemsSql, new Object[] { checklistId });

        try {
            jdbcUtil.executeUpdate();
        } catch (Exception e) {
            jdbcUtil.rollback();
            e.printStackTrace();
            throw new RuntimeException("Failed to delete checklist items");
        }

        // 체크리스트 삭제
        String deleteChecklistSql = "DELETE FROM checklists WHERE checklist_id = ?";
        jdbcUtil.setSqlAndParameters(deleteChecklistSql, new Object[] { checklistId });

        try {
            jdbcUtil.executeUpdate();
        } catch (Exception e) {
            jdbcUtil.rollback();
            e.printStackTrace();
            throw new RuntimeException("Failed to delete checklist");
        } finally {
            jdbcUtil.commit();
            jdbcUtil.close();
        }
    }
    
    /**
     * 특정 항목을 삭제
     * @param itemId 
     * @throws SQLException
     */
    public void deleteChecklistItem(int itemId) throws SQLException {
        String sql = "DELETE FROM checklist_items WHERE item_id = ?";
        Object[] params = new Object[] { itemId };
        jdbcUtil.setSqlAndParameters(sql, params);

        try {
            jdbcUtil.executeUpdate();
        } catch (Exception e) {
            jdbcUtil.rollback();
            e.printStackTrace();
            throw new RuntimeException("Failed to delete checklist item");
        } finally {
            jdbcUtil.commit();
            jdbcUtil.close();
        }
    }

    /**
     * 체크리스트 아이템 수정
     * @param checklistId
     * @param item
     * @throws SQLException
     */
    public void editChecklistItem(ChecklistItem item) throws SQLException {
        String sql = "UPDATE checklist_items SET name = ?, completed = ? WHERE item_id = ?";
        Object[] params = new Object[] { item.getName(), item.isCompleted(), item.getItemId() };
        jdbcUtil.setSqlAndParameters(sql, params);

        try {
            jdbcUtil.executeUpdate();
        } catch (Exception e) {
            jdbcUtil.rollback();
            e.printStackTrace();
            throw new RuntimeException("Failed to update checklist item");
        } finally {
            jdbcUtil.commit();
            jdbcUtil.close();
        }
    }

    /**
     * 특정 체크리스트와 항목을 조회
     * @param checklistId
     * @return
     * @throws SQLException
     */
    public Checklist viewChecklist(int checklistId) throws SQLException {
        String checklistSql = "SELECT * FROM checklists WHERE checklist_id = ?";
        String itemsSql = "SELECT * FROM checklist_items WHERE checklist_id = ?";

        jdbcUtil.setSqlAndParameters(checklistSql, new Object[] { checklistId });

        Checklist checklist = null;
        try {
            ResultSet checklistRs = jdbcUtil.executeQuery();
            if (checklistRs.next()) {
                String category = checklistRs.getString("category");
                checklist = new Checklist(checklistId, category, new ArrayList<>());
            }

            if (checklist != null) {
                jdbcUtil.setSqlAndParameters(itemsSql, new Object[] { checklistId });
                ResultSet itemsRs = jdbcUtil.executeQuery();
                while (itemsRs.next()) {
                    ChecklistItem item = new ChecklistItem(
                        itemsRs.getInt("item_id"),
                        itemsRs.getString("name"),
                        itemsRs.getBoolean("completed")
                    );
                    checklist.getItems().add(item);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.close();
        }
        return checklist;
    }
    
    /**
     * 체크리스트 아이템 완료/미완료 변경
     * @param checklistId
     * @return
     * @throws SQLException
     */
    public void updateItemStatus(int itemId, boolean isCompleted) throws SQLException {
        String sql = "UPDATE checklist_items SET completed = ? WHERE item_id = ?";
        Object[] params = new Object[] { isCompleted, itemId };
        jdbcUtil.setSqlAndParameters(sql, params);

        try {
            jdbcUtil.executeUpdate();
        } catch (Exception e) {
            jdbcUtil.rollback();
            e.printStackTrace();
            throw new RuntimeException("Failed to update item status");
        } finally {
            jdbcUtil.commit();
            jdbcUtil.close();
        }
    }

    /**
     * 특정 체크리스트의 완료된 항목 조회
     * @param checklistId
     * @return
     * @throws SQLException
     */
    public List<ChecklistItem> completedItems(int checklistId) throws SQLException {
        return findItemsByCompletion(checklistId, true);
    }

    /**
     * 특정 체크리스트의 미완료 항목 조회
     * @param checklistId
     * @return
     * @throws SQLException
     */
    public List<ChecklistItem> incompletedItems(int checklistId) throws SQLException {
        return findItemsByCompletion(checklistId, false);
    }

    /**
     * 항목 완료 상태에 따라 항목 리스트를 조회
     * @param checklistId
     * @param completed
     * @return
     * @throws SQLException
     */
    private List<ChecklistItem> findItemsByCompletion(int checklistId, boolean completed) throws SQLException {
        String sql = "SELECT * FROM checklist_items WHERE checklist_id = ? AND completed = ?";
        Object[] params = new Object[] { checklistId, completed };
        jdbcUtil.setSqlAndParameters(sql, params);

        List<ChecklistItem> items = new ArrayList<>();
        try {
            ResultSet rs = jdbcUtil.executeQuery();
            while (rs.next()) {
                ChecklistItem item = new ChecklistItem(
                    rs.getInt("item_id"),
                    rs.getString("name"),
                    rs.getBoolean("completed")
                );
                items.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.close();
        }
        return items;
    }
}