package org.example.HomeWork_5;

import lombok.SneakyThrows;
import org.example.HomeWork_5.api.CategoryService;
import org.example.HomeWork_5.dto.GetCategoryResponse;
import org.example.HomeWork_5.utils.RetrofitUtils;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * @author Konstantin Babenko
 * @create 10.04.2022
 */

public class GetCategoryTest {

    static CategoryService categoryService;
    @BeforeAll
    static void beforeAll() {
        categoryService = RetrofitUtils.getRetrofit().create(CategoryService.class);
    }

    @SneakyThrows
    @Test
    void getCategoryByIdPositiveTest() {
        Response<GetCategoryResponse> response = categoryService.getCategory(1).execute();

        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        assertThat(response.body().getId(), equalTo(1));            // 2
        assertThat(response.body().getTitle(), equalTo("Food"));    // Electronic
        response.body().getProducts().forEach(product ->
                assertThat(product.getCategoryTitle(), equalTo("Food")));
    }

    @SneakyThrows
    @Test
    void getCategoryByIdNegativeTest() {
        Response<GetCategoryResponse> response = categoryService.getCategory(3).execute(); // категорий всего две - 1, 2

        assertThat(response.isSuccessful(), CoreMatchers.is(false));
    }

    @SneakyThrows
    @Test
    void getCategoryByTitlePositiveTest() {
        Response<GetCategoryResponse> response = categoryService.getCategory(1).execute();

        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        assertThat(response.body().getId(), equalTo(1));            // 2
        assertThat(response.body().getTitle(), equalTo("Food"));    // Electronic
        response.body().getProducts().forEach(product ->
                assertThat(product.getCategoryTitle(), equalTo("Food")));
    }

}
