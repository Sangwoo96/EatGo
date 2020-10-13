package kr.co.fastcampus.eatgo.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {
    @Test
    void creation(){
        Category category = Category.builder().name("Korean Food").build();
        assertEquals(category.getName(), "Korean Food");
    }
}