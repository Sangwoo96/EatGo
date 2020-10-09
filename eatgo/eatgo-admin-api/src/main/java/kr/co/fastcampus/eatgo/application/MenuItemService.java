package kr.co.fastcampus.eatgo.application;

import kr.co.fastcampus.eatgo.domain.MenuItem;
import kr.co.fastcampus.eatgo.domain.MenuItemsRepository;
import kr.co.fastcampus.eatgo.domain.RestaurantsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class MenuItemService {

    @Autowired
    private MenuItemsRepository menuItemsRepository;

    @Autowired
    private RestaurantsRepository restaurantsRepository;
    
//    @InjectMock 어노테이션 사용
//    public MenuItemService(MenuItemsRepository menuItemsRepository) {
//        this.menuItemsRepository = menuItemsRepository;
//    }


    //BulkUpdate => 하나의 service에서 create, update, delete의 기능을 다 하는 것 -> save 메서드 활용
    public void bulkUpdate(Long restaurantId, List<MenuItem> menuItemList) {
        for(MenuItem menuItem : menuItemList) {
            update(restaurantId, menuItem);
        }
    }

    private void update(Long restaurantId, MenuItem menuItem) {
        if(menuItem.isDestroy()){
            menuItemsRepository.deleteById(menuItem.getId());
            return;
        }
        menuItem.setRestaurantId(restaurantId);
        menuItemsRepository.save(menuItem);
    }

    public List<MenuItem> getMenuItems(long restaurantId) {
        return menuItemsRepository.findAllByRestaurantId(restaurantId);
    }
}
