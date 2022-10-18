package weather;

import base.TestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class WeatherAPITest extends TestBase {

    @ParameterizedTest(name = "{index}. weather for city = {0}")
    @CsvFileSource(resources = "/weatherData.csv", numLinesToSkip = 1)
    @DisplayName("Checking weather info for specific city")
    @Tag("weather")
    public void shouldGetCurrentWeatherForSpecificCity(String cityName, String countrySymbol, int cityId) {
        int actualId = given()
                .spec(getRequestSpec())
                .spec(getWeatherRequestSpec(cityName, countrySymbol, cityId))
        .when()
                .get()
        .then()
                .spec(getResponseSpec())
                .extract()
                .jsonPath()
                .get("id");

        assertThat(actualId).isEqualTo(cityId);
        //zrobić assercje na 3 miejsca
    }
}
