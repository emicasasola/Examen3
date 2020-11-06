import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {

    public RequestSpecification request;

    @BeforeClass
    @Parameters ("baseURL")
    public void SetupRestAssured(@Optional String baseURL){
        if (baseURL == null){
            baseURL = "https://api-coffee-testing.herokuapp.com";
        }
        RestAssured.baseURI = baseURL;
        request = RestAssured.given();

        System.out.println(String.format("Running on %s environment", baseURL));
    }

    @BeforeMethod
    public void before (){
        request = RestAssured.given();
    }
}
