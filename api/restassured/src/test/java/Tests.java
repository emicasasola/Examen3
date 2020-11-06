import org.testng.annotations.Test;


import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;


public class Tests extends BaseTest{

    private static final String ResourceGetStatus= "/v1/examen/status";
    private static final String ResourcePutName= "/v1/examen/updateName";
    private static final String ResourceGetRandomName= "/v1/examen/randomName";
    private static final String ResourcePostSameName= "/v1/examen/randomName";


    @Test
    public void GetStatus_Test(){
        request
                .get (String.format("%s", ResourceGetStatus))
                .then()
                .statusCode(200)
                .header("Access-Control-Allow-Origin", equalTo("http://localhost"))
                .body("status", equalTo("Listos para el examen"));
    }

    @Test
    public void PutName_Test(){

        request
                .put (String.format("%s", ResourcePutName))
                .then()
                .statusCode(406)
                .header("Access-Control-Allow-Origin", equalTo("http://localhost"))
                .body("message", equalTo("Name was not provided"));
    }

    @Test
    public void GetRandomNameNoAuth_Test(){
        request
                .get (String.format("%s", ResourceGetRandomName))
                .then()
                .statusCode(401)
                .header("Access-Control-Allow-Origin", equalTo("http://localhost"))
                .body("message", equalTo("Please login first"));
    }

    @Test
    public void GetRandomName_Test(){

        request
                .given()
                .auth()
                .basic ("testuser", "testpass")
                .get (String.format("%s", ResourceGetRandomName))
                .then()
                .statusCode(200)
                .header("Access-Control-Allow-Origin", equalTo("http://localhost"))
                .body("name", is(not(equalTo(null))));
    }

    //@Test
    //public void PostSameName_Test(){

      //  request
        //        .post (String.format("%s", ResourcePostSameName))
          //      .then()
            //    .statusCode(200)
              //  .header("Access-Control-Allow-Origin", equalTo("http://localhost"))
               // .body("message", equalTo("Please login first"));
    //}

}
