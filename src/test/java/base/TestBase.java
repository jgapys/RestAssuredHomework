package base;

import configuration.AppProperties;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {

    private static AppProperties appProperties;

    @BeforeAll
    static void setDriver() {
        appProperties = AppProperties.getInstance();
    }

    public static RequestSpecification getRequestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(System.getProperty("baseUri"))
                .setBasePath(System.getProperty("weatherPath"))
                .addParam("appId", System.getProperty("appId"))
                .build();
    }

    public static RequestSpecification getWeatherRequestSpec(String cityName, String countrySymbol, int cityId) {
        return new RequestSpecBuilder()
                .addParam("name", cityName)
                .addParam("sys.country", countrySymbol)
                .addParam("id", cityId)
                .addHeader("name", cityName)
                .build()
                .filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    public static ResponseSpecification getResponseSpec() {
        return new ResponseSpecBuilder()
                .build()
                .statusCode(200);
    }
}
