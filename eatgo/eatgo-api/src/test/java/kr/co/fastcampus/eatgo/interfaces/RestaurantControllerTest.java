package kr.co.fastcampus.eatgo.interfaces;

import kr.co.fastcampus.eatgo.application.RestaurantService;
import kr.co.fastcampus.eatgo.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RestaurantController.class)
public class RestaurantControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private RestaurantService restaurantService;
    //mockbean 가짜 객체 생성

//    @SpyBean(RestaurantsRepositoryImpl.class)
//    private RestaurantsRepository restaurantsRepository;
//
//    @SpyBean(MenuItemsRepositoryImpl.class)
//    private MenuItemsRepository menuItemsRepository;

    @Test
    public void list() throws Exception {
        List<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(new Restaurant(1004L, "JOKER HOUSE", "seoul"));
        given(restaurantService.getRestaurants()).willReturn(restaurants);
        //Service의 기능을 테스트하는 것이 아니고 controller를 테스트하는 것이기 때문에 repository 의존성을 주입시킬 필요가 없다.
        //list() 컨트롤러에서 사용하는 Service의 getRestaurants의 결과를 임의로 지정한다.

        mvc.perform(get("/restaurants"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"name\":\"JOKER HOUSE\"")))
                .andExpect(content().string(containsString("\"id\":1004")));
    }

    @Test
    public void detail() throws Exception {
        Restaurant restaurant1 = new Restaurant(1004L, "JOKER HOUSE", "seoul" );
        restaurant1.addMenuItem(new MenuItem("kimchi", 1004L));

        Restaurant restaurant2 = new Restaurant(2020L, "cyber zip", "seoul" );

        given(restaurantService.getRestaurant(1004L)).willReturn(restaurant1);
        given(restaurantService.getRestaurant(2020L)).willReturn(restaurant2);

        mvc.perform(get("/restaurants/1004"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"name\":\"JOKER HOUSE\"")))
                .andExpect(content().string(containsString("\"id\":1004")))
                .andExpect(content().string(containsString("kimchi")));

        mvc.perform(get("/restaurants/2020"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"name\":\"cyber zip\"")))
                .andExpect(content().string(containsString("\"id\":2020")));;
    }
}