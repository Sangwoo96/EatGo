package kr.co.fastcampus.eatgo.application;

import kr.co.fastcampus.eatgo.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RestaurantService {
    @Autowired
    private RestaurantsRepository restaurantsRepository;

    @Autowired
    private MenuItemsRepository menuItemsRepository;

    @Autowired
    private ReviewRepository reviewsRepository;

    //RestaurantServiceTest를 위한 생성자
    public RestaurantService(RestaurantsRepository restaurantRepository, MenuItemsRepository menuItemsRepository, ReviewRepository reviewsRepository) {
        this.restaurantsRepository = restaurantRepository;
        this.menuItemsRepository = menuItemsRepository;
        this.reviewsRepository = reviewsRepository;
    }

    public Restaurant getRestaurant(Long id){
        Restaurant restaurant = restaurantsRepository.findById(id)
                .orElseThrow(()-> new RestaurantNotFoundException(id));
        List<MenuItem> menuItems = menuItemsRepository.findAllByRestaurantId(id);
        List<Review> reviews = reviewsRepository.findAllByRestaurantId(id);
        restaurant.setMenuItems(menuItems);
        restaurant.setReviews(reviews);
        return restaurant;
    }

    public List<Restaurant> getRestaurants() {
        List<Restaurant> restaurants = restaurantsRepository.findAll();
        return restaurants;
    }

    public Restaurant addRestaurant(Restaurant restaurant) {
        Restaurant saved = restaurantsRepository.save(restaurant);
        return saved;
    }

    @Transactional
    public Restaurant updateRestaurant(long id, String name, String address) {
        // TODO: update Restaurant...
        Restaurant restaurant = restaurantsRepository.findById(id).orElse(null);
        restaurant.updateInformation(name, address);
        return restaurant;
    }
}
