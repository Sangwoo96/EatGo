package kr.co.fastcampus.eatgo.domain;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RestaurantsRepositoryImpl implements RestaurantsRepository {
    private List<Restaurant> restaurants = new ArrayList<>();

    public RestaurantsRepositoryImpl(){
        restaurants.add( new Restaurant(1004L,"bob zip", "seoul"));
        restaurants.add( new Restaurant(2020L,"cyber zip", "seoul"));
    }

    @Override
    public List<Restaurant> findAll() {
        return restaurants;
    }

    @Override
    public Restaurant findById(Long id){
        Restaurant restaurant = restaurants.stream().filter(r -> r.getId().equals(id)).findFirst().orElse(null);
        return restaurant;
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        restaurant.setId(1234L);
        restaurants.add(restaurant);
        return restaurant;
    }
}
