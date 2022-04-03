package org.example;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.equalToIgnoringCase;
//import static org.hamcrest.Matchers.lessThanOrEqualTo;

/**
 * @author Konstantin Babenko
 * @create 03.04.2022
 */

public class HomeWork_3Test<prop> {

    private final String apiKey = "51e3d26cb8ab4880b0e20b97bd1b2fe7";
    private final String baseUrl = "https://api.spoonacular.com";
    private final String baseSearch = "/recipes/complexSearch";

    @BeforeAll
    static void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void getRecipeComplexSearchBaseTest(){
        given()
                .queryParam("apiKey", apiKey)
                .when()
                .get(baseUrl+baseSearch)
                .then()
                .statusCode(200);
    }

    @Test
    void getRecipeComplexSearchJsonTest(){
        JsonPath response = given()
                .queryParam("apiKey", apiKey)
                .when()
                .get(baseUrl+baseSearch)
                .body()
                .jsonPath();
        assertThat(response.get("offset"), equalTo(0));
        assertThat(response.get("number"), equalTo(10));
    }

    @Test
    void getRecipeComplexSearchJsonQueryAppleTest(){
        JsonPath response = given()
                .queryParam("apiKey", apiKey)
                .queryParam("query", "apple")
                .when()
                .get(baseUrl+baseSearch)
                .then()
                .statusCode(200)
                .extract().jsonPath();
        assertThat(response.get("offset"), equalTo(0));
        assertThat(response.get("number"), equalTo(10));
        assertThat(response.prettify(), containsString("Apple"));
    }

    @Test
    void getRecipeComplexSearchJsonMaxCaloriesTest(){
        JsonPath response = given()
                .queryParam("apiKey", apiKey)
                .queryParam("maxCalories", "200")
                .when()
                .get(baseUrl+baseSearch)
                .then()
                .statusCode(200)
                .extract().jsonPath();
        assertThat(response.get("offset"), equalTo(0));
        assertThat(response.get("number"), equalTo(10));
    }

    @Test
    void getRecipeComplexSearchJsonTitleMatchTest(){
        JsonPath response = given()
                .queryParam("apiKey", apiKey)
                .queryParam("titleMatch", "potato")
                .when()
                .get(baseUrl+baseSearch)
//                .prettyPeek()
                .then()
                .statusCode(200)
                .extract().jsonPath();
        assertThat(response.get("offset"), equalTo(0));
        assertThat(response.get("number"), equalTo(10));
    }

    @Test
    void addRecipe1() {
        String cuisine = given()
                .queryParam("apiKey", apiKey)
//                .queryParam("hash", "9f70227c0d9d382aa3a7601299550aae10d19077")
                .body("{\n"
                        + " \"title\": Pork roast with green beans, \n"
                        + " \"ingredientList\": 3 oz pork shoulder apple potato \n"
                        + "}")
                .when()
                .post(baseUrl+"/recipes/cuisine")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .get("cuisine")
                .toString();
        System.out.println(cuisine);
        assertThat(cuisine, equalTo("Mediterranean"));
    }

    @Test
    void addRecipe2() {
        String cuisine = given()
                .queryParam("apiKey", apiKey)
                .body("{\n"
                        + " \"title\": Tea, \n"
                        + " \"ingredientList\": tea and water \n"
                        + "}")
                .when()
                .post(baseUrl+"/recipes/cuisine")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .get("cuisine")
                .toString();
        System.out.println(cuisine);
        assertThat(cuisine, equalTo("Mediterranean"));
    }
    @Test
    void addRecipe3() {
        String cuisine = given()
                .queryParam("apiKey", apiKey)
                .body("{\n"
                        + " \"title\": eggs, \n"
                        + " \"ingredientList\": eggs and oil \n"
                        + "}")
                .when()
                .post(baseUrl+"/recipes/cuisine")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .get("cuisine")
                .toString();
        System.out.println(cuisine);
        assertThat(cuisine, equalTo("Mediterranean"));
    }
    @Test
    void addRecipe4() {
        String cuisine = given()
                .queryParam("apiKey", apiKey)
//                .queryParam("hash", "9f70227c0d9d382aa3a7601299550aae10d19077")
                .body("{\n"
                        + " \"title\": Potato \n"
                        + " \"ingredientList\": potato and oil\n"
                        + "}")
                .when()
                .post(baseUrl+"/recipes/cuisine")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .get("cuisine")
                .toString();
        System.out.println(cuisine);
        assertThat(cuisine, equalTo("Mediterranean"));
    }
    @Test
    void addRecipe5() {
        String cuisine = given()
                .queryParam("apiKey", apiKey)
//                .queryParam("hash", "9f70227c0d9d382aa3a7601299550aae10d19077")
                .body("{\n"
                        + " \"title\": Soup, \n"
                        + " \"ingredientList\": water \n meat \n"
                        + "}")
                .when()
                .post(baseUrl+"/recipes/cuisine")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .get("cuisine")
                .toString();
        System.out.println(cuisine);
        assertThat(cuisine, equalTo("Mediterranean"));
    }


    @Test // из примера урока - не заработало!!!! не могу авторизоаться
    void addMealTest() {
        String hash = given()
                .queryParam("apiKey", apiKey)
                .body("{\n"
                        + " \"username\": Bob\n"
                        + "}")
                .when()
                .post(baseUrl+"/users/connect")
//                .prettyPeek()
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .get("hash")
                .toString();
        System.out.println(hash);


        String id = given()
                .queryParam("username", "Bob") // не могу авторизоаться
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
                .post(baseUrl+"/mealplanner/sname/shopping-list/items")
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
                .delete("https://api.spoonacular.com/mealplanner/sname/items/" + id)
                .then()
                .statusCode(200);
    }


}
