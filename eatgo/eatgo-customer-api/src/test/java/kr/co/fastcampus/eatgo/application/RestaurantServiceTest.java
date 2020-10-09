package kr.co.fastcampus.eatgo.application;

import kr.co.fastcampus.eatgo.domain.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;


public class RestaurantServiceTest {

    private RestaurantService restaurantService;
    
    @Mock
    private RestaurantsRepository restaurantRepository;

    @Mock
    private MenuItemsRepository menuItemsRepository;

    @Mock
    private ReviewRepository reviewsRepository;
    
    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);

        mockRestaurantRepository();
        mockMenuItemRepository();
        mockReviewRepository();

        restaurantService = new RestaurantService(restaurantRepository, menuItemsRepository, reviewsRepository);
    }

    private void mockRestaurantRepository() {
        List<Restaurant> restaurants = new ArrayList<>();
        Restaurant restaurant = Restaurant.builder()
                .id(1004L)
                .name("bob zip")
                .address("seoul")
                .build();
        restaurants.add(restaurant);

        given(restaurantRepository.findAll()).willReturn(restaurants);
        given(restaurantRepository.findById(1004L)).willReturn(Optional.of(restaurant));
    }

    private void mockMenuItemRepository() {
        List<MenuItem> menuItems = new ArrayList<>();
        MenuItem menuItem =MenuItem.builder()
                .id(1004L)
                .name("kimchi")
                .build();
        menuItems.add(menuItem);

        given(menuItemsRepository.findAllByRestaurantId(1004L)).willReturn(menuItems);
    }

    private void mockReviewRepository() {
        List<Review> reviews = new ArrayList<>();
        Review review = Review.builder()
                .name("BeRyong")
                .score(1)
                .description("Bad!")
                .build();
        reviews.add(review);
        given(reviewsRepository.findAllByRestaurantId(1004L)).willReturn(reviews);

    }

    @Test
    public void getRestaurantWithExisted(){
        Restaurant restaurant = restaurantService.getRestaurant(1004L);

        verify(menuItemsRepository).findAllByRestaurantId(eq(1004L));
        verify(reviewsRepository).findAllByRestaurantId(eq(1004L));

        assertThat(restaurant.getId(), is(1004L));
        MenuItem menuItem = restaurant.getMenuItems().get(0);
        assertThat(menuItem.getName(), is("kimchi"));

        Review review = restaurant.getReviews().get(0);
        assertThat(review.getDescription(), is("Bad!"));
    }

    //테스트에서의 예외처리
    @Test(expected = RestaurantNotFoundException.class)
    public void getRestaurantWithNotExisted(){
        restaurantService.getRestaurant(404L);
    }

    @Test
    public void getRestaurants(){
        List<Restaurant> restaurants = restaurantService.getRestaurants();
        Restaurant restaurant = restaurants.get(0);
        assertThat(restaurant.getId(), is(1004L));
    }
}