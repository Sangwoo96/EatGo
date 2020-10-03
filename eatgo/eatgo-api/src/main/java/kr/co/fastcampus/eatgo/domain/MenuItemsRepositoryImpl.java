package kr.co.fastcampus.eatgo.domain;

import java.util.ArrayList;
import java.util.List;

public class MenuItemsRepositoryImpl implements MenuItemsRepository{

    List<MenuItem> menuItems = new ArrayList<>();

    public MenuItemsRepositoryImpl() {
        menuItems.add(new MenuItem("kimchi", 1004L));
    }

    @Override
    public List<MenuItem> findByAllRestaurantId(Long restaurantId) {
        return menuItems;
    }
}
