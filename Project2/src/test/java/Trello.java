import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.http.ContentType;
import org.junit.Test;
import static io.restassured.RestAssured.baseURI;

public class Trello {
//key ve token bilgilerini güvenlik nedeniyle sildim.
    static String Boardid,ListId,CardId;
    static String Key="**";
    static String Token="**";
    public Trello(){
     baseURI="https://api.trello.com/";
    }

    @Test
    public void CreateBoard(){

        String BoardName="TrelloTest";
        Response response= RestAssured.given()
                .header("Content-Type","application/json")
                .when()
                .queryParam("key",Key)
                .queryParam("token",Token)
                .post(baseURI+"1/boards?name="+BoardName)
                .then().statusCode(200).contentType(ContentType.JSON).extract().response();

        System.out.println("Response Code: "+response.statusCode());
        Boardid= response.then().contentType(ContentType.JSON).extract().path("id");
        System.out.println("BoardId: "+Boardid);


    }
    @Test
    public void CreateList(){

        //curl --request POST \
        //  --url 'https://api.trello.com/1/lists?name={name}&idBoard=*&key=APIKey&token=APIToken'
        String ListName="TestList";
        Response response= RestAssured.given()
                .header("Content-Type","application/json")
                .when()
                .queryParam("key",Key)
                .queryParam("token",Token)
                .queryParam("idBoard","638b787cc4df6d01b0e2789c")
                .post(baseURI+"1/lists?name="+ListName)
                .then().statusCode(200).contentType(ContentType.JSON).extract().response();
        System.out.println("Response Code: "+response.statusCode());
        ListId= response.then().contentType(ContentType.JSON).extract().path("id");
        System.out.println("ListId: "+ListId);
    }
    @Test
    public void CreateCard(){

        //POST /1/cards
        String CardName="TestCard2";
        Response response= RestAssured.given()
                .header("Content-Type","application/json")
                .when()
                .queryParam("key",Key)
                .queryParam("token",Token)
                .queryParam("idBoard","638b787cc4df6d01b0e2789c")
                .queryParam("idList","638b78a24fd86101082e6dba")
                .post(baseURI+"1/cards?name="+CardName)
                .then().statusCode(200).contentType(ContentType.JSON).extract().response();
        System.out.println("Response Code: "+response.statusCode());
        CardId= response.then().contentType(ContentType.JSON).extract().path("id");
        System.out.println("CardId: "+CardId);
    }
    @Test
    public void UpdateCard(){
        //cardId=CardId: 638b813519959000bbd5f096
       // curl --request PUT \
       // --url 'https://api.trello.com/1/cards/{id}?key=APIKey&token=APIToken' \
       // --header 'Accept: application/json'

        String CardName="UpdateTestCard";
        Response response= RestAssured.given()
                .header("Content-Type","application/json")
                .when()
                .queryParam("key",Key)
                .queryParam("token",Token)
                .queryParam("name",CardName)
                .put(baseURI+"1/cards/638b813519959000bbd5f096")
                .then().statusCode(200).contentType(ContentType.JSON).extract().response();
        System.out.println("Response Code: "+response.statusCode());
    }
    @Test
    public void DeleteCard(){
       // curl --request DELETE \
       // --url 'https://api.trello.com/1/cards/{id}?key=APIKey&token=APIToken'

        Response response= RestAssured.given()
                .header("Content-Type","application/json")
                .when()
                .queryParam("key",Key)
                .queryParam("token",Token)
                .delete(baseURI+"1/cards/638b813519959000bbd5f096")
                .then().statusCode(200).contentType(ContentType.JSON).extract().response();
        System.out.println("Response Code: "+response.statusCode());
    }
    @Test
    public void DeleteBoard(){
      //  curl --request DELETE \
     //   --url 'https://api.trello.com/1/boards/{id}?key=APIKey&token=APIToken'
        Response response= RestAssured.given()
                .header("Content-Type","application/json")
                .when()
                .queryParam("key",Key)
                .queryParam("token",Token)
                .delete(baseURI+"1/boards/638b787cc4df6d01b0e2789c")
                .then().statusCode(200).contentType(ContentType.JSON).extract().response();
        System.out.println("Response Code: "+response.statusCode());
    }
}
