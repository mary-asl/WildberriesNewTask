package WebServiceTests;

import businessObject.Product;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.restassured.RestAssured.given;

public class WildberriesWebServiceTest {

    private static final String BASE_URL = "https://www.wildberries.kz";

    @BeforeTest
    public void init() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test(description = "Validation: status code of the obtained response is 200 OK")
    public void checkStatusCode() {
        Response response = given().get("/recommendations/catalog?type=bestsallers").andReturn();
        int actualStatusCode = response.getStatusCode();
        Assert.assertEquals(actualStatusCode, 200, "status code of the response isn't 200");
    }

    @Test(description = "the value of the content-type header is text/html; charset=utf-8v")
    public void checkContentType() {
        Response response = given().get("/recommendations/catalog?type=bestsallers").andReturn();
        String actualContentType = response.getContentType();
        Assert.assertTrue(actualContentType.contains("text/html"), "unexpected value of the content-type header");
    }

    @Test(description = "the content of the response body contains the array of 100 products")
    public void checkResponseBody() throws Exception {
        Response response = given().get("/brands/eksmo/neknizhnaya-produkciya").andReturn();
        List<Product> listOfProducts = new ArrayList<>();
        Pattern scriptPattern = Pattern.compile("(?<=shortProducts: ).*?(?=\\}\\))");
        Matcher matcher = scriptPattern.matcher(response.asString());
        if (!matcher.find()) {
            throw new Exception("Didn't match");
        }
        String jsonItems = response.asString().substring(matcher.start(), matcher.end());
        Map<String, Product> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();

        try {
            map = mapper.readValue(jsonItems, new TypeReference<Map<String, Product>>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Map.Entry<String, Product> entry : map.entrySet()) {
            listOfProducts.add(entry.getValue());
        }

        Assert.assertEquals(listOfProducts.size(),  100);
    }

}
