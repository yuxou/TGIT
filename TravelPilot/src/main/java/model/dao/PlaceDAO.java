package model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import model.domain.Place;
import model.domain.Weather;

public class PlaceDAO {
    private JDBCUtil jdbcUtil = null;

    public PlaceDAO() {
        jdbcUtil = new JDBCUtil(); // JDBCUtil 객체 생성
    }

    /**
     * 장소 추가
     * @param place
     * @throws Exception
     */
    public void addPlace(Place place) throws Exception {
        String sql = "INSERT INTO places (placeId, name, location, memo) VALUES (?, ?, ?, ?)";
        Object[] param = new Object[] {place.getPlaceId(), place.getName(), place.getLocation(), place.getMemo()};
        jdbcUtil.setSqlAndParameters(sql, param); // JDBCUtil 에 insert문과 매개 변수 설정
        try {
            jdbcUtil.executeUpdate(); // insert 문 실행
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.close(); // resource 반환
        }
    }

    /**
     * 모든 장소 조회
     * @return
     */
    public List<Place> getAllPlaces() {
        String sql = "SELECT * FROM places";
        jdbcUtil.setSqlAndParameters(sql, null); // JDBCUtil에 query문 설정
        List<Place> places = new ArrayList<>();
        try {
            ResultSet rs = jdbcUtil.executeQuery(); // query 실행
            while (rs.next()) {
                Place place = new Place(
                    rs.getInt("placeId"),
                    rs.getString("name"),
                    rs.getString("location"),
                    rs.getString("memo")
                );
                places.add(place);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.close(); // resource 반환
        }
        return places;
    }

    /**
     * 장소 메모 추가
     * @param placeId
     * @param memo
     * @throws Exception
     */
    public void addPlaceMemo(int placeId, String memo) throws Exception {
        String sql = "UPDATE places SET memo = ? WHERE placeId = ?";
        Object[] param = new Object[] {memo, placeId};
        jdbcUtil.setSqlAndParameters(sql, param); // JDBCUtil에 update문과 매개 변수 설정
        try {
            jdbcUtil.executeUpdate(); // update 문 실행
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.close(); // resource 반환
        }
    }

    /**
     * 장소 메모 수정
     * @param placeId
     * @param newMemo
     * @throws Exception
     */
    public void editPlaceMemo(int placeId, String newMemo) throws Exception {
        String sql = "UPDATE places SET memo = ? WHERE placeId = ?";
        Object[] param = new Object[] {newMemo, placeId};
        jdbcUtil.setSqlAndParameters(sql, param); // JDBCUtil에 update문과 매개 변수 설정
        try {
            jdbcUtil.executeUpdate(); // update 문 실행
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.close(); // resource 반환
        }
    }

    /**
     * 장소 메모 삭제
     * @param placeId
     * @throws Exception
     */
    public void deletePlaceMemo(int placeId) throws Exception {
        String sql = "UPDATE places SET memo = NULL WHERE placeId = ?";
        Object[] param = new Object[] {placeId};
        jdbcUtil.setSqlAndParameters(sql, param); // JDBCUtil에 update문과 매개 변수 설정
        try {
            jdbcUtil.executeUpdate(); // update 문 실행
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.close(); // resource 반환
        }
    }

    /**
     * 날씨 정보 조회
     * @param placeId
     * @return
     */
    public Weather getWeatherInfo(int placeId) {
        String sql = "SELECT * FROM weather WHERE location = (SELECT location FROM places WHERE placeId = ?)";
        Object[] param = new Object[] {placeId};
        jdbcUtil.setSqlAndParameters(sql, param); // JDBCUtil에 query문과 매개 변수 설정
        Weather weather = null;
        try {
            ResultSet rs = jdbcUtil.executeQuery(); // query 실행
            if (rs.next()) {
                weather = new Weather(
                    rs.getInt("weatherId"),
                    rs.getDouble("temperature"),
                    rs.getString("condition"),
                    rs.getString("updateTime"),
                    rs.getString("location")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.close(); // resource 반환
        }
        return weather;
    }

    /**
     * 장소 검색
     * @param keyword
     * @return
     */
    public List<Place> searchPlaces(String keyword) {
        return getAllPlaces().stream()
                .filter(place -> place.getName().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }
}