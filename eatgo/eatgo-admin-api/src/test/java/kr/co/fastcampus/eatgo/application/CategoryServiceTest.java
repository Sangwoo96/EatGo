package kr.co.fastcampus.eatgo.application;
import kr.co.fastcampus.eatgo.domain.Category;
import kr.co.fastcampus.eatgo.domain.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {
    @InjectMocks
    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @Test
    void getRegions(){
        List<Category> MockCategories = new ArrayList<>();
        MockCategories.add(Category.builder().name("Korean Food").build());

        given(categoryRepository.findAll()).willReturn(MockCategories);
        List<Category> categories = categoryService.getCategories();

        Category category = categories.get(0);
        assertEquals(category.getName(), "Korean Food");

    }

    @Test
    void addRegions(){
        Category category = categoryService.addCategories("Korean Food");

        verify(categoryRepository).save(any());

        assertEquals(category.getName(), "Korean Food");
    }
}