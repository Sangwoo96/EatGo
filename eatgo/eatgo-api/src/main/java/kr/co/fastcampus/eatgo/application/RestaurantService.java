package kr.co.fastcampus.eatgo.application;

import kr.co.fastcampus.eatgo.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {
    @Autowired
    RestaurantsRepository restaurantsRepository;

    @Autowired
    MenuItemsRepository menuItemsRepository;

    public RestaurantService(RestaurantsRepository restaurantRepository, MenuItemsRepository menuItemsRepository) {
        this.restaurantsRepository = restaurantRepository;
        this.menuItemsRepository = menuItemsRepository;
    }

    public Restaurant getRestaurant(Long id){
        Restaurant restaurant = restaurantsRepository.findById(id);
        List<MenuItem> menuItems = menuItemsRepository.findByAllRestaurantId(id);

        restaurant.setMenuItems(menuItems);
        return restaurant;
    }

    public List<Restaurant> getRestaurants() {
        List<Restaurant> restaurants = restaurantsRepository.findAll();
        return restaurants;
    }
}
