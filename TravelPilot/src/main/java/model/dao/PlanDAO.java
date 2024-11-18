package model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import model.domain.Plan;
import model.domain.Flight;
import model.domain.Accommodation;
import model.domain.User;

public class PlanDAO {
    private JDBCUtil jdbcUtil = null;

    public PlanDAO() {
        jdbcUtil = new JDBCUtil(); // JDBCUtil 객체 생성
    }

    /**
     * 일정 추가
     * @param plan
     * @return
     * @throws Exception
     */
    public Plan createPlan(Plan plan) throws Exception {
        String sql = "INSERT INTO plans (planId, planTitle, country, startDate, endDate, isPublic, writerId) VALUES (planId_seq.nextval, ?, ?, ?, ?, ?, ?)";
        Object[] param = new Object[] { plan.getPlanTitle(), plan.getCountry(),
                new java.sql.Date(plan.getStartDate().getTime()), new java.sql.Date(plan.getEndDate().getTime()), 
                plan.isPublic(), plan.getWriter().getUserId() };

        jdbcUtil.setSqlAndParameters(sql, param);

        String[] key = { "planId" }; 
        try {
            jdbcUtil.executeUpdate(key); 
            ResultSet rs = jdbcUtil.getGeneratedKeys();
            if (rs.next()) {
                int generatedKey = rs.getInt(1); // 생성된 planId
                plan.setPlanId(generatedKey); // 생성된 planId를 plan 객체에 저장
            }

            // 비행기 일정 및 숙소 추가
            addFlightsToPlan(plan.getPlanId(), plan.getFlightInfo());
            addAccommodationsToPlan(plan.getPlanId(), plan.getAccommodationInfo());

            return plan;
        } catch (SQLException e) {
            jdbcUtil.rollback();
            e.printStackTrace();
        } finally {
            jdbcUtil.commit();
            jdbcUtil.close(); // resource 반환
        }
        return null;
    }

    /**
     * 일정 수정 
     * @param updatedPlan
     * @return
     * @throws Exception
     */
    public int editPlan(Plan updatedPlan) throws Exception {
        String sql = "UPDATE plans SET planTitle = ?, startDate = ?, endDate = ?, country = ? WHERE planId = ?";
        Object[] param = new Object[] { updatedPlan.getPlanTitle(), 
            new java.sql.Date(updatedPlan.getStartDate().getTime()), 
            new java.sql.Date(updatedPlan.getEndDate().getTime()), 
            updatedPlan.getCountry(), updatedPlan.getPlanId() };

        jdbcUtil.setSqlAndParameters(sql, param);

        try {
            return jdbcUtil.executeUpdate(); 
        } catch (SQLException e) {
            jdbcUtil.rollback();
            e.printStackTrace();
        } finally {
            jdbcUtil.commit();
            jdbcUtil.close(); // resource 반환
        }
        return 0;
    }

    /**
     * 일정 삭제
     * @param planId
     * @return
     * @throws Exception
     */
    public int deletePlan(int planId) throws Exception {
        String sql = "DELETE FROM plans WHERE planId = ?";
        jdbcUtil.setSqlAndParameters(sql, new Object[] { planId }); // JDBCUtil에 delete문과 매개 변수 설정

        try {
            return jdbcUtil.executeUpdate(); 
        } catch (SQLException e) {
            jdbcUtil.rollback();
            e.printStackTrace();
        } finally {
            jdbcUtil.commit();
            jdbcUtil.close(); // resource 반환
        }
        return 0;
    }
    
    /**
     * 일정 검색 기능
     * @param keyword
     * @return
     * @throws SQLException
     */
    public List<Plan> searchPlans(String keyword) throws SQLException {
        return getAllPlans().stream()
                .filter(plan -> plan.getPlanTitle().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    /**
     * 일정 상세 조회 
     * @param planId
     * @return
     * @throws SQLException
     */
    public Optional<Plan> findPlanById(int planId) throws SQLException {
        String sql = "SELECT * FROM plans WHERE planId = ?";
        jdbcUtil.setSqlAndParameters(sql, new Object[] { planId }); // JDBCUtil에 query문과 매개 변수 설정
        Plan plan = null;

        try (ResultSet rs = jdbcUtil.executeQuery()) {
            if (rs.next()) {
                plan = new Plan(
                    rs.getInt("planId"),
                    rs.getString("planTitle"),
                    rs.getString("country"),
                    rs.getDate("startDate"),
                    rs.getDate("endDate"),
                    new ArrayList<>(),
                    new ArrayList<>(),
                    rs.getBoolean("isPublic"),
                    new User()
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.close(); // resource 반환
        }
        return Optional.ofNullable(plan);
    }

    /**
     * 모든 일정 조회 (키워드 없이 전체 조회)
     * @return
     * @throws SQLException
     */
    public List<Plan> getAllPlans() throws SQLException {
        return getAllPlans(""); // 빈 문자열을 키워드로 전달하여 전체 목록을 조회
    }

    /**
     * 키워드 기반 모든 일정 조회 
     * @param keyword
     * @return
     * @throws SQLException
     */
    public List<Plan> getAllPlans(String keyword) throws SQLException {
        String sql = "SELECT * FROM plans";
        jdbcUtil.setSqlAndParameters(sql, null);

        List<Plan> plans = new ArrayList<>();
        try (ResultSet rs = jdbcUtil.executeQuery()) {
            while (rs.next()) {
                Plan plan = new Plan(
                    rs.getInt("planId"),
                    rs.getString("planTitle"),
                    rs.getString("country"),
                    rs.getDate("startDate"),
                    rs.getDate("endDate"),
                    new ArrayList<>(),  // 기본값으로 빈 리스트
                    new ArrayList<>(),  // 기본값으로 빈 리스트
                    rs.getBoolean("isPublic"),
                    new User()  // 기본 User 객체 생성
                );
                plans.add(plan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.close(); // resource 반환
        }

        if (!keyword.isEmpty()) {
            plans.removeIf(plan -> !plan.getPlanTitle().toLowerCase().contains(keyword.toLowerCase()));
        }

        return plans;
    }

    /**
     * 비행 일정 추가
     * @param planId
     * @param flightInfo
     * @throws Exception
     */
    private void addFlightsToPlan(int planId, List<Flight> flightInfo) throws Exception {
        String sql = "INSERT INTO flights (planId, flightId, departure, destination, departureDate, departureTime, arrivalDate, arrivalTime, cost) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        for (Flight flight : flightInfo) {
            Object[] param = new Object[] {
                planId, flight.getFlightId(), flight.getDeparture(),
                flight.getDestination(), new java.sql.Date(flight.getDepartureDate().getTime()),
                flight.getDepartureTime(), new java.sql.Date(flight.getArrivalDate().getTime()),
                flight.getArrivalTime(), flight.getCost()
            };
            
            jdbcUtil.setSqlAndParameters(sql, param);  // JDBCUtil 객체를 사용하여 SQL과 파라미터 설정
            try {
                jdbcUtil.executeUpdate();  // SQL 실행
            } catch (SQLException e) {
                jdbcUtil.rollback();
                e.printStackTrace();
            } finally {
                jdbcUtil.commit();
                jdbcUtil.close();  // 리소스 반환
            }
        }
    }

    /**
     * 숙소 추가
     * @param planId
     * @param accommodationInfo
     * @throws Exception
     */
    private void addAccommodationsToPlan(int planId, List<Accommodation> accommodationInfo) throws Exception {
        String sql = "INSERT INTO accommodations (planId, accommodationId, name, checkInDate, checkOutDate, cost) "
                   + "VALUES (?, ?, ?, ?, ?, ?)";
        
        for (Accommodation accommodation : accommodationInfo) {
            Object[] param = new Object[] {
                planId, accommodation.getAccommodationId(), accommodation.getName(),
                new java.sql.Date(accommodation.getCheckInDate().getTime()),
                new java.sql.Date(accommodation.getCheckOutDate().getTime()), accommodation.getCost()
            };
            
            jdbcUtil.setSqlAndParameters(sql, param);  // JDBCUtil 객체를 사용하여 SQL과 파라미터 설정
            try {
                jdbcUtil.executeUpdate();  // SQL 실행
            } catch (SQLException e) {
                jdbcUtil.rollback();
                e.printStackTrace();
            } finally {
                jdbcUtil.commit();
                jdbcUtil.close();  // 리소스 반환
            }
        }
    }
    
    /**
     * 동행자 추가
     * @param planId
     * @param companion 동행자 (User 객체)
     * @throws Exception 
     */
    public void addCompanionToPlan(int planId, User companion) throws Exception {
        String sql = "INSERT INTO plan_companions (planId, companionId) VALUES (?, ?)"; 
        Object[] param = new Object[] {planId, companion.getUserId()}; 

        jdbcUtil.setSqlAndParameters(sql, param); // JDBCUtil에 insert문과 매개 변수 설정

        try {
            jdbcUtil.executeUpdate(); // SQL 실행
        } catch (SQLException e) {
            jdbcUtil.rollback();
            e.printStackTrace();
        } finally {
            jdbcUtil.commit();
            jdbcUtil.close(); // resource 반환
        }
    }

    /**
     * 동행자 삭제
     * @param planId
     * @param companionId 동행자 ID
     * @throws Exception 
     */
    public void deleteCompanion(int planId, int companionId) throws Exception {
        String sql = "DELETE FROM plan_companions WHERE planId = ? AND companionId = ?"; 
        Object[] param = new Object[] {planId, companionId}; 

        jdbcUtil.setSqlAndParameters(sql, param); // JDBCUtil에 delete문과 매개 변수 설정

        try {
            jdbcUtil.executeUpdate(); // SQL 실행
        } catch (SQLException e) {
            jdbcUtil.rollback();
            e.printStackTrace();
        } finally {
            jdbcUtil.commit();
            jdbcUtil.close(); // resource 반환
        }
    }

}