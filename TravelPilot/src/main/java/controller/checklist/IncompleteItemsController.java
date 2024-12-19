package controller.checklist;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.domain.ChecklistItem;
import model.service.ChecklistManager;

import java.util.List;

/**
 * 특정 체크리스트의 미완료 항목 조회를 처리하는 컨트롤러
 */
public class IncompleteItemsController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int checklistId = Integer.parseInt(request.getParameter("checklistId")); // 체크리스트 ID

        ChecklistManager checklistManager = ChecklistManager.getInstance();
        List<ChecklistItem> incompletedItems = checklistManager.getIncompletedItems(checklistId); // 미완료 항목 조회

        // 조회된 미완료 항목을 View로 전달
        request.setAttribute("incompletedItems", incompletedItems);
        request.setAttribute("checklistId", checklistId); // 관련 체크리스트 ID 전달

        return "/checklist/incompletedItems.jsp"; // 미완료 항목 리스트 페이지로 이동
    }
}
