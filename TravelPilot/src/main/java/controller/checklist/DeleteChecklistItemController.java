package controller.checklist;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.service.ChecklistManager;

public class DeleteChecklistItemController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int itemId = Integer.parseInt(request.getParameter("itemId")); // 삭제할 항목 ID
        int checklistId = Integer.parseInt(request.getParameter("checklistId")); // 소속된 체크리스트 ID

        ChecklistManager checklistManager = ChecklistManager.getInstance();
        checklistManager.deleteChecklistItem(itemId); // 항목 삭제

        return "/checklist/viewChecklist.jsp?checklistId=" + checklistId; // 체크리스트 상세 페이지로 이동
    }
}
