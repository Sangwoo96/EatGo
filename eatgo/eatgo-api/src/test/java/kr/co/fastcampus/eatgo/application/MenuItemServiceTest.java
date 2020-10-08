package kr.co.fastcampus.eatgo.application;

import kr.co.fastcampus.eatgo.domain.MenuItem;
import kr.co.fastcampus.eatgo.domain.MenuItemsRepository;
import kr.co.fastcampus.eatgo.domain.RestaurantsRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MenuItemServiceTest {

    @InjectMocks
    private MenuItemService menuItemService;

    @Mock
    private MenuItemsRepository menuItemsRepository;

    @Mock
    private RestaurantsRepository restaurantsRepository;

//    @Before
//    public void setUp(){
//        MockitoAnnotations.initMocks(this); @RunWith(MockitoJUnitRunner.class) 사용으로 대체
//        menuItemService = new MenuItemService(menuItemsRepository);
//    }

    @Test
    public void bulkUpdate(){
        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(MenuItem.builder().name("kimbob").build()); //입력테스트
        menuItems.add(MenuItem.builder().id(1L).name("kimchi").build()); //수정테스트
        menuItems.add(MenuItem.builder().destroy(true).name("Gukbob").build()); //삭제테스트
        menuItemService.bulkUpdate(1L, menuItems);


        //verify(restaurantsRepository).findAllById(any());
        verify(menuItemsRepository).deleteById(any());
        verify(menuItemsRepository, times(2)).save(any());
    }
}