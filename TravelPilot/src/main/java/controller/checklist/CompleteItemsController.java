package controller.checklist;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.domain.ChecklistItem;
import model.service.ChecklistManager;

import java.util.List;

/**
 * 특정 체크리스트의 완료된 항목 조회를 처리하는 컨트롤러
 */
public class CompleteItemsController implements Controller {
	
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int checklistId = Integer.parseInt(request.getParameter("checklistId")); // 체크리스트 ID

        ChecklistManager checklistManager = ChecklistManager.getInstance();
        List<ChecklistItem> completedItems = checklistManager.getCompletedItems(checklistId); // 완료된 항목 조회

        // 조회된 완료된 항목을 View로 전달
        request.setAttribute("completedItems", completedItems);
        request.setAttribute("checklistId", checklistId); // 관련 체크리스트 ID 전달

        return "/checklist/completedItems.jsp"; // 완료된 항목 리스트 페이지로 이동
    }
}
