package controller.checklist;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Controller;
import model.service.ChecklistManager;

public class MarkChecklistController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
    	// itemId와 새로운 완료 상태(isCompleted) 가져오기
    	int itemId = Integer.parseInt(request.getParameter("itemId"));
    	boolean isCompleted = Boolean.parseBoolean(request.getParameter("isCompleted"));

    	// ChecklistItem 상태 업데이트
    	ChecklistManager checklistManager = ChecklistManager.getInstance();
    	checklistManager.updateItemStatus(itemId, isCompleted);

    	// 변경된 Checklist로 리디렉션
    	return "/checklist/view?checklistId=" + request.getParameter("checklistId");

        
    }
}
