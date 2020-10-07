package kr.co.fastcampus.eatgo.application;

import kr.co.fastcampus.eatgo.domain.MenuItem;
import kr.co.fastcampus.eatgo.domain.MenuItemsRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

public class MenuItemServiceTest {


    private MenuItemService menuItemService = new MenuItemService();

    @Mock
    private MenuItemsRepository menuItemsRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this); //05:05
    }

    @Test
    public void bulkUpdate(){
        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(MenuItem.builder().name("kimchi").build());
        menuItemService.bulkUpdate(1L, menuItems);

        verify(menuItemsRepository).save(any());
    }
}