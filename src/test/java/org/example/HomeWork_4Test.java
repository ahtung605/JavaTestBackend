package org.example;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.example.HomeWork_4.MyConnect;
import org.example.HomeWork_4.MyCuisine;
import org.example.HomeWork_4.MyResponse;
import org.example.HomeWork_4.MyResult;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Konstantin Babenko
 * @create 03.04.2022
 */

public class HomeWork_4Test {

    ResponseSpecification responseSpecification = null;
    RequestSpecification requestSpecification = null;

    private final String apiKey = "51e3d26cb8ab4880b0e20b97bd1b2fe7";
    private final String baseUrl = "https://api.spoonacular.com";
    private final String baseSearch = "/recipes/complexSearch";
//    private MyResult result;

    @BeforeAll
    static void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @BeforeEach
    void beforeTest() {
        responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectStatusLine("HTTP/1.1 200 OK")
                .expectContentType(ContentType.JSON)
                .expectResponseTime(Matchers.lessThan(5000L))
                //.expectHeader("Access-Control-Allow-Credentials", "true")
                .build();

        RestAssured.responseSpecification = responseSpecification;
//        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        requestSpecification = new RequestSpecBuilder()
                .addQueryParam("apiKey", apiKey)
//                .addQueryParam("includeNutrition", "false")
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();


    }
    @Test
    void getRecipeComplexSearchBaseTest(){
        given().spec(requestSpecification)
                .when()
                .get(baseUrl+baseSearch)
                .then()
                .spec(responseSpecification);
    }

    @Test
    void getRecipeComplexSearchJsonTest(){
        MyResponse response = given().spec(requestSpecification)
                .when()
                .get(baseUrl+baseSearch)
                .then()
                .spec(responseSpecification)
                .extract()
                .response()
                .body()
                .as(MyResponse.class);
        assertThat(response.getOffset(), equalTo(0));
        assertThat(response.getNumber(), equalTo(10));
    }

    @Test
    void getRecipeComplexSearchJsonQueryAppleTest(){
        MyResponse response = given().spec(requestSpecification)
                .queryParam("query", "apple")
                .when()
                .get(baseUrl+baseSearch)
                .then()
                .spec(responseSpecification)
                .extract()
                .response()
                .body()
                .as(MyResponse.class);
        assertThat(response.getOffset(), equalTo(0));
        assertThat(response.getNumber(), equalTo(10));


        MyResult result = given().spec(requestSpecification)
                .queryParam("query", "apple")
                .when()
                .get(baseUrl+baseSearch)
                .then()
                .spec(responseSpecification)
                .extract()
                .response()
                .body()
                .as(MyResult.class);
        assertThat(result.toString(), containsString("Apple"));
    }

    @Test
    void getRecipeComplexSearchJsonMaxCaloriesTest(){
        MyResponse response = given().spec(requestSpecification)
                .queryParam("maxCalories", "200")
                .when()
                .get(baseUrl+baseSearch)
                .then()
                .spec(responseSpecification)
                .extract()
                .response()
                .body()
                .as(MyResponse.class);
        assertThat(response.getOffset(), equalTo(0));
        assertThat(response.getNumber(), equalTo(10));
    }

    @Test
    void getRecipeComplexSearchJsonTitleMatchTest(){
        MyResponse response = given().spec(requestSpecification)
                .queryParam("titleMatch", "potato")
                .when()
                .get(baseUrl+baseSearch)
//                .prettyPeek()
                .then()
                .spec(responseSpecification)
                .extract()
                .response()
                .body()
                .as(MyResponse.class);
        assertThat(response.getOffset(), equalTo(0));
        assertThat(response.getNumber(), equalTo(10));
        assertThat(response.toString(), containsString("potato"));
    }

    @Test
    void addRecipe1() {
        MyCuisine cuisine = given().spec(requestSpecification)
                .body("{\n"
                        + " \"title\": Pork roast with green beans, \n"
                        + " \"ingredientList\": 3 oz pork shoulder apple potato \n"
                        + "}")
                .when()
                .post(baseUrl+"/recipes/cuisine")
                .then()
                .spec(responseSpecification)
                .extract()
                .response()
                .body()
                .as(MyCuisine.class);
        assertThat(cuisine.toString(), containsString("Mediterranean"));
    }

    @Test
    void addRecipe2() {
        MyCuisine cuisine = given().spec(requestSpecification)
                .body("{\n"
                        + " \"title\": Tea, \n"
                        + " \"ingredientList\": tea and water \n"
                        + "}")
                .when()
                .post(baseUrl+"/recipes/cuisine")
                .then()
                .spec(responseSpecification)
                .extract()
                .response()
                .body()
                .as(MyCuisine.class);
        assertThat(cuisine.toString(), containsString("Mediterranean"));
    }
    @Test
    void addRecipe3() {
        MyCuisine cuisine = given().spec(requestSpecification)
                .body("{\n"
                        + " \"title\": eggs, \n"
                        + " \"ingredientList\": eggs and oil \n"
                        + "}")
                .when()
                .post(baseUrl+"/recipes/cuisine")
                .then()
                .spec(responseSpecification)
                .extract()
                .response()
                .body()
                .as(MyCuisine.class);
        assertThat(cuisine.toString(), containsString("Mediterranean"));
    }
    @Test
    void addRecipe4() {
        MyCuisine cuisine = given().spec(requestSpecification)
                .body("{\n"
                        + " \"title\": Potato \n"
                        + " \"ingredientList\": potato and oil\n"
                        + "}")
                .when()
                .post(baseUrl+"/recipes/cuisine")
                .then()
                .spec(responseSpecification)
                .extract()
                .response()
                .body()
                .as(MyCuisine.class);
        assertThat(cuisine.toString(), containsString("Mediterranean"));
    }
    @Test
    void addRecipe5() {
        MyCuisine cuisine = given().spec(requestSpecification)
                .body("{\n"
                        + " \"title\": Soup, \n"
                        + " \"ingredientList\": water \n meat \n"
                        + "}")
                .when()
                .post(baseUrl+"/recipes/cuisine")
                .then()
                .spec(responseSpecification)
                .extract()
                .response()
                .body()
                .as(MyCuisine.class);
        assertThat(cuisine.toString(), containsString("Mediterranean"));
    }


    @Test // из примера урока - не заработало!!!! не могу авторизоаться
    void addShoppingListTest() {
        // при добавлении пользователя со своим именем - сервис присваивает свои имена!!!!!!!!!!!!!!!!
        MyConnect response = given().spec(requestSpecification)
                .body("{\n"
                        + " \"username\": Bob \n"
//                        + " \"firstName\": firstname \n"
//                        + " \"lastName\": lastname \n"
//                        + " \"email\": z@z.com \n"
                        + "}")
                .when()
                .post(baseUrl+"/users/connect")
                .prettyPeek()
                .then()
                .spec(responseSpecification)
                .extract()
                .response()
                .body()
                .as(MyConnect.class);
        String userN = response.getUsername(); // при добавлении пользователя со своим именем - сервис присваивает свои имена!!!!!!!!!!!!!!!!
        String hash = response.getHash();
        System.out.println(userN + "\n" + hash);

        //попытка получить шопингЛист созданного пользователя
        JsonPath resp = given()
                .pathParam("username", userN)
                .queryParam("hash", hash)                  // не могу авторизоаться
                .when()
                .get(baseUrl+"/mealplanner/{username}/shopping-list")
                .then()
                .statusCode(200)
                .extract().jsonPath();

        String id = given()
//                .queryParam("username", username)          // не могу авторизоаться
                .pathParam("username", userN)               // не могу авторизоаться
                .queryParam("hash", hash)
//                .queryParam("apiKey", apiKey)
                .body("{\n"
                        + " \"date\": 1644881179,\n"
                        + " \"slot\": 1,\n"
                        + " \"position\": 0,\n"
                        + " \"type\": \"INGREDIENTS\",\n"
                        + " \"value\": {\n"
                        + "     \"ingredients\": [\n"
                        + "         {\n"
                        + "             \"name\": \"1 banana\"\n"
                        + "         }\n"
                        + "     ]\n"
                        + " }\n"
                        + "}")
                .when()
                .post(baseUrl+"/mealplanner/{username}/shopping-list/items")
//                .prettyPeek()
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .get("id")
                .toString();
        System.out.println(id);

        given()
                .queryParam("hash", hash)
                .queryParam("apiKey", apiKey)
                .delete("https://api.spoonacular.com/mealplanner/:username/items/" + id)
                .then()
                .statusCode(200);
    }

}
