package org.example.HomeWork_6;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.example.HomeWork_5.api.ProductService;
import org.example.HomeWork_5.dto.Product;
import org.example.HomeWork_5.utils.RetrofitUtils;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * @author Konstantin Babenko
 * @create 10.04.2022
 */

public class modifyProductTest {

    static ProductService productService;
    Product product = null;
    Faker faker = new Faker();
    int id, price, priceNew;
    String title;

    @BeforeAll
    static void beforeAll() {
        productService = RetrofitUtils.getRetrofit()
                .create(ProductService.class);

    }

    @BeforeEach
    void setUp() {
        product = new Product()
                .withTitle(faker.food().ingredient())
                .withCategoryTitle("Food")
                .withPrice((int) (Math.random() * 10000));
    }

    //    @Test
    void createProductInFoodCategoryTest() throws IOException {
        Response<Product> response = productService.createProduct(product)
                .execute();
        id = response.body().getId();
        title = response.body().getTitle();
        price = response.body().getPrice();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        System.out.println(id + ", " + title + ", " + price);
    }

    @Test
    void putProductInFoodCategoryTest() throws IOException {
        createProductInFoodCategoryTest();
        ObjectMapper mapper = new ObjectMapper();
        StringReader reader = new StringReader("{ \"id\": 1, \"title\": \"" + title + "\", \"price\": 1000, \"categoryTitle\": \"Food\"}");
        Product product1 = mapper.readValue(reader, Product.class);

        Response<Product> response = productService.modifyProduct(product1)
                .execute();
        priceNew = response.body().getPrice();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        assertThat(response.body().getPrice(), equalTo(1000));

        System.out.println(id + ", " + title + ", " + price + " => " + priceNew);

        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        db.dao.ProductsMapper productsMapper = session.getMapper(db.dao.ProductsMapper.class);
        db.model.ProductsExample productsExample = new db.model.ProductsExample();
        System.out.println(productsExample.createCriteria().andPriceEqualTo(priceNew).isValid());
        System.out.println(productsMapper.countByExample(productsExample));
        session.close();

    }


    @SneakyThrows
    @AfterEach
    void tearDown() {
        Response<ResponseBody> response = productService.deleteProduct(id).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }

}
