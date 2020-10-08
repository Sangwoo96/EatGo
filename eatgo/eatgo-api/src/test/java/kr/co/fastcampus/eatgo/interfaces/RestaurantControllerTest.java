package kr.co.fastcampus.eatgo.interfaces;

import kr.co.fastcampus.eatgo.application.RestaurantService;
import kr.co.fastcampus.eatgo.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
        restaurants.add(Restaurant.builder()
                        .id(1004L)
                        .name("JOKER HOUSE")
                        .address("Seoul")
                        .build());
        given(restaurantService.getRestaurants()).willReturn(restaurants);
        //Service의 기능을 테스트하는 것이 아니고 controller를 테스트하는 것이기 때문에 repository 의존성을 주입시킬 필요가 없다.
        //list() 컨트롤러에서 사용하는 Service의 getRestaurants의 결과를 임의로 지정한다.

        mvc.perform(get("/restaurants"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"name\":\"JOKER HOUSE\"")))
                .andExpect(content().string(containsString("\"id\":1004")));
    }

    @Test
    public void detailWithExisted() throws Exception {
        Restaurant restaurant = Restaurant.builder()
                .id(1004L)
                .name("JOKER HOUSE")
                .address("Seoul")
                .build();
        restaurant.setMenuItems(Arrays.asList(MenuItem.builder().name("kimchi").build()));
        restaurant.setReviews(Arrays.asList(Review.builder().name("JOKER").score(5).description("Great!").build()));

        given(restaurantService.getRestaurant(1004L)).willReturn(restaurant);

        mvc.perform(get("/restaurants/1004"))
                .andExpect(status().isOk()) //isOk => status 200
                .andExpect(content().string(containsString("\"name\":\"JOKER HOUSE\"")))
                .andExpect(content().string(containsString("\"id\":1004")))
                .andExpect(content().string(containsString("kimchi")))
                .andExpect(content().string((containsString("Great!"))));

    }

    @Test
    public void detailWithNotExisted() throws Exception{
        given(restaurantService.getRestaurant(404L)).willThrow(new RestaurantNotFoundException(404L));
        mvc.perform(get("/restaurants/404"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("{}"));
    }

    //isOk => 200 , isCreated => 201
    @Test
    public void createWithValidData() throws Exception {
        given(restaurantService.addRestaurant(any())).will(invocation -> {
            Restaurant restaurant = invocation.getArgument(0);
            restaurant.setId(1234L);
            return restaurant;
        });

        mvc.perform(post("/restaurants")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\" : \"BeRyong\", \"address\" : \"Busan\"}"))
                .andExpect(status().isCreated()) //isCreated => status 201
                .andExpect(header().string("location", "/restaurants/1234"))
                .andExpect(content().string("{}"));

        verify(restaurantService).addRestaurant(any());
        //restaurantService mock 객체의 addRestaurant 메소드가 호출되었는지 확인
    }

    @Test
    public void createWithInvalidData() throws Exception {
        mvc.perform(post("/restaurants")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\" : \"\", \"address\" : \"\"}"))
                .andExpect(status().isBadRequest()); //isCreated => status 201
    }

    @Test
    public void updateWithValidData() throws Exception {
        mvc.perform(patch("/restaurants/1004")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\" : \"JOKER Bar\", \"address\" : \"Busan\"}"))
                .andExpect(status().isOk());

        verify(restaurantService).updateRestaurant(1004L, "JOKER Bar", "Busan");
    }

    @Test
    public void updateWithInvalidData() throws Exception {
        mvc.perform(patch("/restaurants/1004")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\" : \"\", \"address\" : \"\"}"))
                .andExpect(status().isBadRequest());

    }
}