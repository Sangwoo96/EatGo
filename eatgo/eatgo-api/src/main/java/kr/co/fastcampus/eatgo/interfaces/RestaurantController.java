package kr.co.fastcampus.eatgo.interfaces;

import kr.co.fastcampus.eatgo.domain.Restaurant;
import kr.co.fastcampus.eatgo.domain.RestaurantsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestaurantController {

    @Autowired
    private RestaurantsRepository restaurantsRepository;

    @GetMapping("/restaurants")
    public List<Restaurant> list(){
//        domain/RestaurantRepositoryImpl로 이동
//        List<Restaurant> restaurants = new ArrayList<>();
//        restaurants.add( new Restaurant(1004L,"bob zip", "seoul"));
//        restaurants.add( new Restaurant(2020L,"cyber zip", "seoul"));
        
        List<Restaurant> restaurants = restaurantsRepository.findAll();
        return restaurants;
    }

    @GetMapping("/restaurants/{id}")
    public Restaurant detail(@PathVariable("id") Long id){
        Restaurant restaurant = restaurantsRepository.findById(id);
        return restaurant;
    }
}
