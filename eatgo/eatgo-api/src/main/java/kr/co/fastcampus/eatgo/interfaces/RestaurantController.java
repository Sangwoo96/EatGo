package kr.co.fastcampus.eatgo.interfaces;

import kr.co.fastcampus.eatgo.application.RestaurantService;
import kr.co.fastcampus.eatgo.domain.MenuItem;
import kr.co.fastcampus.eatgo.domain.MenuItemsRepository;
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
    private RestaurantService restaurantService;

//  @Autowired
//  private RestaurantsRepository restaurantsRepository;
//
//  @Autowired
//  private MenuItemsRepository menuItemRepository;

    @GetMapping("/restaurants")
    public List<Restaurant> list(){
        List<Restaurant> restaurants = restaurantService.getRestaurants();
        return restaurants;
    }

    @GetMapping("/restaurants/{id}")
    public Restaurant detail(@PathVariable("id") Long id){
        //기본 정보 + 메뉴 정보
        Restaurant restaurant = restaurantService.getRestaurant(id);

        //기본 정보와 메뉴 정보를 합쳐준다. 이 과정을 위의 application Layer(Service)에서 해준다.
        ////Restaurant restaurant = restaurantsRepository.findById(id);
        //db에 레스토랑 정보와 레스토랑 메뉴 테이블을 따로 관리하는 느낌...
        ////List<MenuItem> menuItems = menuItemRepository.findByAllRestaurantId(id);
        ////restaurant.setMenuItems(menuItems);

        return restaurant;
    }
}
