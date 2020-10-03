package kr.co.fastcampus.eatgo.domain;

import java.util.List;

public interface MenuItemsRepository {
   List<MenuItem> findByAllRestaurantId(Long restaurantId);
}
