package com.example.capstone2foodtruck.Service;

import com.example.capstone2foodtruck.ApiResponse.ApiExcepiton;
import com.example.capstone2foodtruck.Model.Review;
import com.example.capstone2foodtruck.Repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;



    public List<Review> getReview(){
        return reviewRepository.findAll();
    }


    public void addReview(Review review){
        reviewRepository.save(review);
    }


    public void updateReview(Integer id,Review review){
        Review oldReview=reviewRepository.findReviewById(id);

        if(oldReview==null){
            throw new ApiExcepiton("id not found");
        }

        oldReview.setComment(review.getComment());
        oldReview.setProductId(review.getProductId());
        oldReview.setRating(review.getRating());
        reviewRepository.save(oldReview);
    }


    public void deleteReview(Integer id){

        Review oldReview=reviewRepository.findReviewById(id);

        if(oldReview==null){
            throw new ApiExcepiton("id not found");
        }

        reviewRepository.delete(oldReview);

    }



    public void rateMeal(Integer productId, Integer rating) {
        if (rating < 1 || rating > 5) {
            throw new ApiExcepiton("Rating must be between 1 and 5");
        }
        Review review = new Review();
        review.setProductId(productId);
        review.setRating(rating);
        reviewRepository.save(review);
    }


    public Double getAverageRating(Integer productId) {
        return reviewRepository.getAverageRating(productId);
    }

}
