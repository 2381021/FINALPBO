package service;

import entity.Review;
import repository.ReviewRepository;

import java.util.List;

public class ReviewService {
    private final ReviewRepository repository;

    public ReviewService(ReviewRepository repository) {
        this.repository = repository;
    }

    public void submitReview(Review review) {
        repository.save(review);
    }

    public List<Review> getAllReviews() {
        return repository.findAll();
    }
}
