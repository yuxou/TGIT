package controller.checklist;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.domain.ChecklistItem;
import model.service.ChecklistManager;

public class EditChecklistController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int itemId = Integer.parseInt(request.getParameter("itemId")); // 수정할 항목 ID
        String newName = request.getParameter("name"); // 새로운 이름
        boolean isCompleted = Boolean.parseBoolean(request.getParameter("isCompleted")); // 완료 여부

        // ChecklistItem 생성 및 수정
        ChecklistManager checklistManager = ChecklistManager.getInstance();
        ChecklistItem updatedItem = new ChecklistItem();
        updatedItem.setItemId(itemId);
        updatedItem.setName(newName);
        updatedItem.setCompleted(isCompleted);

        checklistManager.editChecklistItem(updatedItem); // DAO를 통해 업데이트

        int checklistId = Integer.parseInt(request.getParameter("checklistId"));
        return "/checklist/viewChecklist.jsp?checklistId=" + checklistId; // 수정된 체크리스트 상세 페이지로 이동
    }
}
