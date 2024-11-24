package controller.checklist;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Controller;
import model.service.ChecklistManager;

public class DeleteChecklistController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	int checklistId = Integer.parseInt(request.getParameter("checklistId"));

    	// Checklist 및 관련 항목 삭제
    	ChecklistManager checklistManager = ChecklistManager.getInstance();
    	checklistManager.deleteChecklist(checklistId);

    	return "/checklist/listChecklists.jsp";
    }
}
