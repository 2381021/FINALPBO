package repository;

import entity.Review;

import java.util.ArrayList;
import java.util.List;

public class ReviewRepository {
    private final List<Review> reviews = new ArrayList<>();

    public void save(Review review) {
        reviews.add(review);
    }

    public List<Review> findAll() {
        return new ArrayList<>(reviews);
    }
}
