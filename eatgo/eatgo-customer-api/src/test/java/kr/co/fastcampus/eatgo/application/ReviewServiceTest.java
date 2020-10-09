package kr.co.fastcampus.eatgo.application;

import kr.co.fastcampus.eatgo.domain.Review;
import kr.co.fastcampus.eatgo.domain.ReviewRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ReviewServiceTest {

    @InjectMocks
    private ReviewService reviewService;
    @Mock
    private ReviewRepository reviewRepository;

    @Test
    public void addReview(){
        Review review = Review.builder()
                .name("JOKER")
                .score(3)
                .description("Mat-it-da")
                .build();
        reviewService.addReview(review, 1004L);

        verify(reviewRepository).save(any());
    }
}