package controller.checklist;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.service.ChecklistManager;

public class MarkChecklistItemController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int itemId = Integer.parseInt(request.getParameter("itemId")); // 항목 ID
        boolean isCompleted = Boolean.parseBoolean(request.getParameter("isCompleted")); // 완료 여부

        // ChecklistManager를 통해 항목 상태 업데이트
        ChecklistManager checklistManager = ChecklistManager.getInstance();
        checklistManager.updateItemStatus(itemId, isCompleted);

        return "/checklist/viewChecklist.jsp"; // 수정된 체크리스트 상세 페이지로 이동
    }
}
