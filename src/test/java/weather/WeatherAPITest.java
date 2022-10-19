package weather;

import base.TestBase;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WeatherAPITest extends TestBase {

    @ParameterizedTest(name = "{index}. weather for city = {0}")
    @CsvFileSource(resources = "/weatherData.csv", numLinesToSkip = 1)
    @DisplayName("Checking weather info for specific city")
    @Tag("weather")
    public void shouldGetCurrentWeatherForSpecificCity(String cityName, String countrySymbol, int cityId) {
        JsonPath jsonPath = given()
                .spec(getRequestSpec())
                .spec(getWeatherRequestSpec(cityName, countrySymbol, cityId))
        .when()
                .get()
        .then()
                .spec(getResponseSpec())
                .extract()
                .jsonPath();

        String actualCity = jsonPath.get("name");
        String actualCountry = jsonPath.get("sys.country");
        int actualId = jsonPath.get("id");

        assertTrue(actualCity.equals(cityName) && actualCountry.equals(countrySymbol) && actualId == cityId);
    }
}
