package controller.checklist;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import controller.Controller;
import model.domain.Checklist;
import model.domain.ChecklistItem;
import model.service.ChecklistManager;

public class ViewChecklistController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	int checklistId = Integer.parseInt(request.getParameter("checklistId"));
    	
    	// Checklist 및 모든 항목 조회
    	ChecklistManager checklistManager = ChecklistManager.getInstance();
    	Checklist checklist = checklistManager.getChecklistWithItems(checklistId);
    	
    	// View에 Checklist 및 항목 전달
    	request.setAttribute("checklist", checklist);
    
    	return "/checklist/viewChecklist.jsp";
    }
}
