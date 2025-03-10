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
        Object[] param = new Object[] { 
            plan.getPlanTitle(), 
            plan.getCountry(),
            new java.sql.Date(plan.getStartDate().getTime()), 
            new java.sql.Date(plan.getEndDate().getTime()), 
            plan.isPublic(), 
            plan.getWriter().getUserId() 
        };

        jdbcUtil.setSqlAndParameters(sql, param);

        String[] key = { "planId" };
        try {
            jdbcUtil.executeUpdate(key);
            ResultSet rs = jdbcUtil.getGeneratedKeys();
            if (rs.next()) {
                int generatedKey = rs.getInt(1);
                plan.setPlanId(generatedKey);
            }

            // 비행 일정 추가
            if (plan.getFlightInfo() != null && !plan.getFlightInfo().isEmpty()) {
                for (Flight flight : plan.getFlightInfo()) {
                    addFlightToPlan(plan.getPlanId(), flight);
                }
            }

            // 숙소 추가
            if (plan.getAccommodationInfo() != null && !plan.getAccommodationInfo().isEmpty()) {
                addAccommodationsToPlan(plan.getPlanId(), plan.getAccommodationInfo());
            }

            return plan;
        } catch (SQLException e) {
            jdbcUtil.rollback();
            throw e;
        } finally {
            jdbcUtil.commit();
            jdbcUtil.close();
        }
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
     * 특정 계획에 비행 일정 추가
     * @param planId
     * @param flight
     */
    public void addFlightToPlan(int planId, Flight flight) throws Exception {
        String sql = "INSERT INTO flights (planId, flightId, departure, destination, departureDate, departureTime, arrivalDate, arrivalTime, cost) "
                   + "VALUES (?, flight_seq.nextval, ?, ?, ?, ?, ?, ?, ?)";
        Object[] param = new Object[] {
            planId, flight.getDeparture(), flight.getDestination(),
            new java.sql.Date(flight.getDepartureDate().getTime()), flight.getDepartureTime(),
            new java.sql.Date(flight.getArrivalDate().getTime()), flight.getArrivalTime(), flight.getCost()
        };

        jdbcUtil.setSqlAndParameters(sql, param);

        try {
            jdbcUtil.executeUpdate();
        } catch (SQLException e) {
            jdbcUtil.rollback();
            throw e;
        }
    }


    /**
     * 특정 계획의 모든 비행 일정 조회
     * @param planId
     * @return 비행 일정 리스트
     */
    public List<Flight> getFlightsByPlanId(int planId) throws SQLException {
        String sql = "SELECT * FROM flights WHERE planId = ?";
        jdbcUtil.setSqlAndParameters(sql, new Object[] { planId });

        List<Flight> flights = new ArrayList<>();
        try (ResultSet rs = jdbcUtil.executeQuery()) {
            while (rs.next()) {
                // 출발 날짜와 도착 날짜를 java.sql.Date로 가져오기
                java.sql.Date departureDate = rs.getDate("departureDate");
                java.sql.Date arrivalDate = rs.getDate("arrivalDate");

                // Flight 객체 생성
                Flight flight = new Flight(
                    rs.getInt("flightId"),
                    rs.getString("departure"),
                    rs.getString("destination"),
                    departureDate.toLocalDate().getYear(),
                    departureDate.toLocalDate().getMonthValue(),
                    departureDate.toLocalDate().getDayOfMonth(),
                    rs.getString("departureTime"),
                    arrivalDate.toLocalDate().getYear(),
                    arrivalDate.toLocalDate().getMonthValue(),
                    arrivalDate.toLocalDate().getDayOfMonth(),
                    rs.getString("arrivalTime"),
                    rs.getDouble("cost")
                );

                flights.add(flight);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.close();
        }

        return flights;
    }


    /**
     * 특정 비행 일정 수정
     * @param flightId
     * @param updatedFlight
     */
    public void updateFlight(int flightId, Flight updatedFlight) throws Exception {
        String sql = "UPDATE flights SET departure = ?, destination = ?, departureDate = ?, departureTime = ?, arrivalDate = ?, arrivalTime = ?, cost = ? "
                   + "WHERE flightId = ?";
        Object[] param = new Object[] {
            updatedFlight.getDeparture(), updatedFlight.getDestination(),
            new java.sql.Date(updatedFlight.getDepartureDate().getTime()), updatedFlight.getDepartureTime(),
            new java.sql.Date(updatedFlight.getArrivalDate().getTime()), updatedFlight.getArrivalTime(),
            updatedFlight.getCost(), flightId
        };

        jdbcUtil.setSqlAndParameters(sql, param);

        try {
            jdbcUtil.executeUpdate();
        } catch (SQLException e) {
            jdbcUtil.rollback();
            e.printStackTrace();
        } finally {
            jdbcUtil.commit();
            jdbcUtil.close();
        }
    }

    /**
     * 특정 비행 일정 삭제
     * @param flightId
     */
    public void deleteFlight(int flightId) throws Exception {
        String sql = "DELETE FROM flights WHERE flightId = ?";
        jdbcUtil.setSqlAndParameters(sql, new Object[] { flightId });

        try {
            jdbcUtil.executeUpdate();
        } catch (SQLException e) {
            jdbcUtil.rollback();
            e.printStackTrace();
        } finally {
            jdbcUtil.commit();
            jdbcUtil.close();
        }
    }

    /**
     * 숙소 추가
     * @param planId
     * @param accommodationInfo
     * @throws Exception
     */
    private void addAccommodationsToPlan(int planId, List<Accommodation> accommodationInfo) throws Exception {
        String sql = "INSERT INTO accommodations (planId, name, checkInDate, checkOutDate, cost) "
                   + "VALUES (?, ?, ?, ?, ?)";

        try {
            for (Accommodation accommodation : accommodationInfo) {
                Object[] param = new Object[] {
                    planId, accommodation.getName(),
                    new java.sql.Date(accommodation.getCheckInDate().getTime()),
                    new java.sql.Date(accommodation.getCheckOutDate().getTime()), accommodation.getCost()
                };

                jdbcUtil.setSqlAndParameters(sql, param);
                jdbcUtil.executeUpdate();
            }
            jdbcUtil.commit(); // 모든 작업 성공 시 커밋
        } catch (SQLException e) {
            jdbcUtil.rollback(); // 오류 발생 시 롤백
            throw new Exception("숙소 추가 중 오류 발생: " + e.getMessage(), e);
        } finally {
            jdbcUtil.close(); // 리소스 정리
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