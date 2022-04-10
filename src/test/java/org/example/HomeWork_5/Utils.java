package org.example.HomeWork_5;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.HomeWork_5.dto.GetCategoryResponse;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * @author Konstantin Babenko
 * @create 10.04.2022
 */

public class Utils {

    @Test
    void test() throws IOException {
        GetCategoryResponse getCategoryResponse = new GetCategoryResponse();
        getCategoryResponse.setId(1);
        getCategoryResponse.setTitle("myTitle");

        StringWriter writer = new StringWriter();

        //это объект Jackson, который выполняет сериализацию
        ObjectMapper mapper = new ObjectMapper();

        // сама сериализация: 1-куда, 2-что
        mapper.writeValue(writer, getCategoryResponse);

        //преобразовываем все записанное во StringWriter в строку
        String result = writer.toString();
        System.out.println(result);

        StringReader reader = new StringReader("{\"id\":1,\"title\":\"myTitle\",\"products\":[]}");

        GetCategoryResponse getCategoryResponseReader = mapper.readValue(reader, GetCategoryResponse.class);
    }

}
