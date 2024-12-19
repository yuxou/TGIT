package model.domain;

import java.util.List;
import java.util.stream.Collectors;

public class Search {
    private String keyword;

    public Search() { }

    public Search(String keyword) {
        this.keyword = keyword;
    }

    public List<Plan> searchPlans(List<Plan> plans) {
        return plans.stream()
                .filter(plan -> plan.getPlaces().isPresent() && 
                        plan.getPlaces().get().stream().anyMatch(place -> 
                                place.getName().contains(keyword) || 
                                place.getLocation().contains(keyword)))
                .collect(Collectors.toList());
    }

    public List<Place> searchPlaces(List<Place> places) {
        return places.stream()
                .filter(place -> 
                        place.getName().contains(keyword) || 
                        place.getLocation().contains(keyword))
                .collect(Collectors.toList());
    }

    public List<Review> searchReviews(List<Review> reviews) {
        return reviews.stream()
                .filter(review -> review.getComment() != null && 
                        review.getComment().contains(keyword))
                .collect(Collectors.toList());
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}