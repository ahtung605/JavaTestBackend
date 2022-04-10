package org.example.HomeWork_5.utils;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

import static okhttp3.logging.HttpLoggingInterceptor.Level.BODY;

/**
 * @author Konstantin Babenko
 * @create 10.04.2022
 */

@UtilityClass
public class RetrofitUtils {
    Properties prop = new Properties();
    private static InputStream configFile;

    static {
        try {
            configFile = new FileInputStream("src/main/resources/my.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @SneakyThrows
    public String getBaseUrl() {
        prop.load(configFile);
        return prop.getProperty("url");
    }


    HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
    org.example.HomeWork_5.utils.LoggingInterceptor logging2 = new org.example.HomeWork_5.utils.LoggingInterceptor();
    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();


//    public Retrofit getRetrofit(){
//        return new Retrofit.Builder()
//                .baseUrl(getBaseUrl())
//                .addConverterFactory(JacksonConverterFactory.create())
//                .build();
//
//    }

    public Retrofit getRetrofit() {
        logging.setLevel(BODY);
        httpClient.addInterceptor(logging2);
        return new Retrofit.Builder()
                .baseUrl(getBaseUrl())
                .addConverterFactory(JacksonConverterFactory.create())
                .client(httpClient.build())
                .build();
    }

}
