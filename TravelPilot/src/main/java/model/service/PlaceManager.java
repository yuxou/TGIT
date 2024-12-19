package model.service;

import java.util.List;

import model.dao.PlaceDAO;
import model.domain.Place;
import model.domain.Weather;

public class PlaceManager {
    private static final PlaceManager instance = new PlaceManager(new PlaceDAO());
    private PlaceDAO placeDAO;

    // 생성자
    public PlaceManager(PlaceDAO placeDAO) {
        this.placeDAO = placeDAO;
    }
    
    public static PlaceManager getInstance() {
        return instance;
    }

    /**
     * 새로운 장소 추가
     * @param place
     */
    public void addPlace(Place place) {
        try {
            placeDAO.addPlace(place);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to add place");
        }
    }

    /**
     * 모든 장소 조회
     * @return 장소 리스트
     */
    public List<Place> getAllPlaces() {
        try {
            return placeDAO.getAllPlaces();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to retrieve all places");
        }
    }

    /**
     * 장소 메모 추가
     * @param placeId
     * @param memo
     */
    public void addPlaceMemo(int placeId, String memo) {
        try {
            placeDAO.addPlaceMemo(placeId, memo);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to add memo to place");
        }
    }

    /**
     * 장소 메모 수정
     * @param placeId
     * @param newMemo
     */
    public void editPlaceMemo(int placeId, String newMemo) {
        try {
            placeDAO.editPlaceMemo(placeId, newMemo);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to edit memo for place");
        }
    }

    /**
     * 장소 메모 삭제
     * @param placeId
     */
    public void deletePlaceMemo(int placeId) {
        try {
            placeDAO.deletePlaceMemo(placeId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to delete memo from place");
        }
    }

    /**
     * 특정 장소의 날씨 정보 조회
     * @param placeId
     * @return 날씨 정보
     */
    public Weather getWeatherInfo(int placeId) {
        try {
            return placeDAO.getWeatherInfo(placeId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to retrieve weather info for place");
        }
    }

    /**
     * 장소 검색
     * @param keyword
     * @return 검색된 장소 리스트
     */
    public List<Place> searchPlaces(String keyword) {
        try {
            return placeDAO.searchPlaces(keyword);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to search places");
        }
    }
}