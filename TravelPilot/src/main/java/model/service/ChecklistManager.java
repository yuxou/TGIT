package model.service;

import java.sql.SQLException;
import java.util.List;

import model.dao.ChecklistDAO;
import model.domain.Checklist;
import model.domain.ChecklistItem;

public class ChecklistManager {
	private static final ChecklistManager instance = new ChecklistManager(new ChecklistDAO());
    private ChecklistDAO checklistDAO;

    // 생성자
    public ChecklistManager(ChecklistDAO checklistDAO) {
        this.checklistDAO = checklistDAO;
    }

    public static ChecklistManager getInstance() {
        return instance;
    }
    
    /**
     * 새로운 체크리스트 생성
     * @param checklist
     * @return 생성된 체크리스트 ID
     */
    public int createChecklist(Checklist checklist) {
        try {
            return checklistDAO.addChecklist(checklist);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to create checklist");
        }
    }

    /**
     * 체크리스트에 새로운 항목 추가
     * @param checklistId
     * @param item
     */
    public void addChecklistItem(int checklistId, ChecklistItem item) {
        try {
            checklistDAO.addItem(checklistId, item);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to add checklist item");
        }
    }

    /**
     * 체크리스트 상세 조회
     * @param checklistId
     * @return 체크리스트
     */
    public Checklist viewChecklist(int checklistId) {
        try {
            return checklistDAO.viewChecklist(checklistId);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to view checklist");
        }
    }

    /**
     * 완료된 항목 조회
     * @param checklistId
     * @return 완료된 항목 리스트
     */
    public List<ChecklistItem> getCompletedItems(int checklistId) {
        try {
            return checklistDAO.completedItems(checklistId);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to get completed checklist items");
        }
    }

    /**
     * 미완료 항목 조회
     * @param checklistId
     * @return 미완료 항목 리스트
     */
    public List<ChecklistItem> getIncompletedItems(int checklistId) {
        try {
            return checklistDAO.incompletedItems(checklistId);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to get incompleted checklist items");
        }
    }
}