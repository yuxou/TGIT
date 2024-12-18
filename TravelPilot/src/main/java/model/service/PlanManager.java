package model.service;

import java.sql.SQLException;
import java.util.List;

import model.dao.PlanDAO;
import model.domain.Flight;
import model.domain.Plan;

public class PlanManager {
    private PlanDAO planDAO;

    // 생성자
    public PlanManager(PlanDAO planDAO) {
        this.planDAO = planDAO;
    }

    /**
     * 새로운 계획 생성
     * @param plan
     * @return 생성된 계획
     */
    public Plan createPlan(Plan plan) {
        try {
            return planDAO.createPlan(plan);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to create plan");
        }
    }

    /**
     * 계획 수정
     * @param plan
     * @return 수정된 행 수
     */
    public int editPlan(Plan plan) {
        try {
            return planDAO.editPlan(plan);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to edit plan");
        }
    }

    /**
     * 계획 삭제
     * @param planId
     */
    public void deletePlan(int planId) {
        try {
            planDAO.deletePlan(planId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to delete plan");
        }
    }

    /**
     * 모든 계획 조회
     * @return 계획 리스트
     */
    public List<Plan> getAllPlans() {
        try {
            return planDAO.getAllPlans();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to retrieve plans");
        }
    }

    /**
     * 특정 계획에 포함된 모든 비행 일정 조회
     * @param planId
     * @return 비행 일정 리스트
     */
    public List<Flight> getFlightsByPlanId(int planId) {
        try {
            return planDAO.getFlightsByPlanId(planId);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to retrieve flights for plan ID: " + planId);
        }
    }

    /**
     * 특정 계획에 비행 일정 추가
     * @param planId
     * @param flight
     */
    public void addFlightToPlan(int planId, Flight flight) {
        try {
            planDAO.addFlightToPlan(planId, flight);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to add flight to plan ID: " + planId);
        }
    }

    /**
     * 특정 비행 일정 수정
     * @param flightId
     * @param updatedFlight
     */
    public void updateFlight(int flightId, Flight updatedFlight) {
        try {
            planDAO.updateFlight(flightId, updatedFlight);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to update flight ID: " + flightId);
        }
    }

    /**
     * 특정 비행 일정 삭제
     * @param flightId
     */
    public void deleteFlight(int flightId) {
        try {
            planDAO.deleteFlight(flightId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to delete flight ID: " + flightId);
        }
    }
    
    
    
    //동반자 companion 관련 메서드 추가 
    
    /**
     * 계획에 동반자 추가
     * @param planId
     * @param companion
     */
    public void addCompanionToPlan(int planId, User companion) {
        try {
            planDAO.addCompanionToPlan(planId, companion);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to add companion to plan ID: " + planId);
        }
    }

    /**
     * 계획에서 동반자 삭제
     * @param planId
     * @param companionId
     */
    public void deleteCompanion(int planId, int companionId) {
        try {
            planDAO.deleteCompanion(planId, companionId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to delete companion from plan ID: " + planId);
        }
    }
}
}