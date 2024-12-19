package controller.checklist;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.service.ChecklistManager;

public class DeleteChecklistController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int checklistId = Integer.parseInt(request.getParameter("checklistId"));

        ChecklistManager checklistManager = ChecklistManager.getInstance();
        checklistManager.deleteChecklist(checklistId); // 체크리스트 삭제

        return "/checklist/listChecklists.jsp"; // 체크리스트 목록 페이지로 이동
    }
}
