package kr.co.fastcampus.eatgo.domain;

public class MenuItem {

    private final String name;
    private final Long restaurantId;

    public MenuItem(String name, Long restaurantId) {
        this.name = name;
        this.restaurantId = restaurantId;
    }

    public String getName() {
        return name;
    }

    public Long getRestaurantId(){
        return restaurantId;
    }
}
