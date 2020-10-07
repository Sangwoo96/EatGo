package kr.co.fastcampus.eatgo.application;

import kr.co.fastcampus.eatgo.domain.MenuItem;
import kr.co.fastcampus.eatgo.domain.RestaurantsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuItemService {

    @Autowired
    private RestaurantsRepository menuItemsRepository;

    public void bulkUpdate(Long restaurantId, List<MenuItem> menuItemList) {
        menuItemsRepository.save(menuItem);
    }
}
