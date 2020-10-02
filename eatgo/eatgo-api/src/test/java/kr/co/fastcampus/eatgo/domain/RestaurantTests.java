package kr.co.fastcampus.eatgo.domain;

import org.junit.jupiter.api.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RestaurantTests {
    @Test
    public void creation() {
        Restaurant restaurant = new Restaurant(1004L,"bob zip", "seoul");
        assertThat(restaurant.getId(), is(1004L));
        assertThat(restaurant.getName(), is("bob zip"));
        assertThat(restaurant.getAddress(), is("seoul"));
        }
    @Test
    public void information(){
        Restaurant restaurant = new Restaurant(1004L,"bob zip", "seoul");
        assertThat(restaurant.getInformation(), is("bob zip in seoul"));
    }
}