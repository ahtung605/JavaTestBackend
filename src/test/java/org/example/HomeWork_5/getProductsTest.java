package org.example.HomeWork_5;

import okhttp3.ResponseBody;
import org.example.HomeWork_5.api.ProductService;
import org.example.HomeWork_5.utils.RetrofitUtils;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Konstantin Babenko
 * @create 10.04.2022
 */

public class getProductsTest {

    static ProductService productService;

    @BeforeAll
    static void beforeAll() {
        productService = RetrofitUtils.getRetrofit()
                .create(ProductService.class);
    }


    @Test
    void getProductFoodTest() throws IOException {
        Response<ResponseBody> response = productService.getProducts()
                .execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        assertThat(response.body().string(), containsString("Food"));
    }

    @Test
    void getProductElectronicTest() throws IOException {
        Response<ResponseBody> response = productService.getProducts()
                .execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        assertThat(response.body().string(), containsString("Electronic"));
    }
}
