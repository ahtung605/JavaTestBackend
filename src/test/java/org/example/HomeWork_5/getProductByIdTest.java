package org.example.HomeWork_5;

import org.example.HomeWork_5.api.ProductService;
import org.example.HomeWork_5.dto.Product;
import org.example.HomeWork_5.utils.RetrofitUtils;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * @author Konstantin Babenko
 * @create 10.04.2022
 */

public class getProductByIdTest {

    static ProductService productService;

    @BeforeAll
    static void beforeAll() {
        productService = RetrofitUtils.getRetrofit()
                .create(ProductService.class);
    }


    @Test
    void getProductTest() throws IOException {
        Response<Product> response = productService.getProductById(5)
                .execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        assertThat(response.body().getCategoryTitle(), equalTo("Electronic"));    // Electronic
        assertThat(response.body().getTitle(), equalTo("LG TV 1"));    // Electronic
    }

    @Test
    void getProductNegativeTest() throws IOException {
        Response<Product> response = productService.getProductById(55)
                .execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(false));
    }

}
