package kr.co.fastcampus.eatgo.interfaces;

import kr.co.fastcampus.eatgo.application.MenuItemService;
import kr.co.fastcampus.eatgo.domain.MenuItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MenuItemController {

    @Autowired
    MenuItemService menuItemService;

    @PatchMapping("/restaurants/{restaurantId}/menuitems")
    public String bulkUpdate(@PathVariable("restaurantId") Long restaurantId, @RequestBody List<MenuItem> menuItemList){
        menuItemService.bulkUpdate(restaurantId, menuItemList);
        return "";
    }

    @GetMapping("/restaurants/{restaurantId}/menuitems")
    public List<MenuItem> getMenuItems(@PathVariable("restaurantId") Long restaurantId){
        List<MenuItem> menuItems = menuItemService.getMenuItems(restaurantId);
        return menuItems;
    }
}
