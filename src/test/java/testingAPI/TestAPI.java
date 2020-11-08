

package testingAPI;

import api.EndPoints;
import dataBaseManager.DBManager;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import responce.Body;

import java.time.LocalDateTime;
import java.time.ZoneId;

import static io.restassured.RestAssured.given;

public class TestAPI {

    private static ResponseSpecification responseSpecificationOK;
    private static RequestSpecification requestSpecification;
    Body body = new Body();



    @BeforeClass(groups = {"TestAPI"})
    public void prepare(){
        System.out.println("Начался выполняться API тест");

        body.setKey(DBManager.getKey());
        body.setToken(DBManager.getToken());
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(EndPoints.basePath)
                .setBody(body)
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
        responseSpecificationOK = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .log(LogDetail.ALL)
                .build();

}


    @Test(priority = 1, groups = {"TestAPI"})
    public void checkCreateBoard(){
        body.setName("KanbanTool");
                Response response = given()
                .spec(requestSpecification)
                .body(body)
                .when()
                .post(EndPoints.postCreateBoard)
                .then()
                .spec(responseSpecificationOK)
                .contentType(ContentType.JSON).extract().response();

        String idBoard = response.jsonPath().getString("id");
        body.setIdBoard(idBoard);

    }


    @Test(priority = 2, groups = {"TestAPI"})
    public void createList(){
        body.setName("Backlog");
        Response response = given()
                .spec(requestSpecification)
                .body(body)
                .when()
                .post(EndPoints.postCreateList)
                .then()
                .spec(responseSpecificationOK)
                .contentType(ContentType.JSON).extract().response();

        String idList = response.jsonPath().getString("id");
        body.setIdList(idList);
        body.getIdAllLists().add(idList);
    }

    @Test(priority = 3, groups = {"TestAPI"})
    public void createCard(){
        body.setName("Карточка для изучения API");
        Response response = given()
                .spec(requestSpecification)
                .body(body)
                .when()
                .post(EndPoints.postCreateCard)
                .then()
                .spec(responseSpecificationOK)
                .contentType(ContentType.JSON).extract().response();

        String idCard = response.jsonPath().getString("id");
        body.setIdCard(idCard);

    }

    @Test(priority = 4, groups = {"TestAPI"})
    public void createAttachment(){
        body.setUrl("http://risovach.ru/upload/2015/03/mem/kotyaka-ulibaka_75985067_orig_.jpg");
        given()
                .spec(requestSpecification)
                .body(body)
                .when()
                .pathParam("id", body.getIdCard())
                .post(EndPoints.postCreateAttachment)
                .then()
                .spec(responseSpecificationOK);
    }

    @Test(priority = 5, groups = {"TestAPI"})
    public void createDeadline(){
        body.setDue(LocalDateTime.now(ZoneId.of("Europe/Moscow")).plusHours(16).toString());
        given()
                .spec(requestSpecification)
                .body(body)
                .when()
                .pathParam("id", body.getIdCard())
                .put(EndPoints.putUpdateCard)
                .then()
                .spec(responseSpecificationOK);
    }


    @Test(priority = 6, groups = {"TestAPI"})
    public void createDescription(){
        body.setDesc("Тут будет отмечаться прогресс обучения");
        given()
                .spec(requestSpecification)
                .body(body)
                .when()
                .pathParam("id", body.getIdCard())
                .put(EndPoints.putUpdateCard)
                .then()
                .spec(responseSpecificationOK);
    }

    @Test(priority = 7, groups = {"TestAPI"})
    public void createCheckList(){
        body.setName("Чек-лист");
        Response response = given()
                .spec(requestSpecification)
                .body(body)
                .when()
                .pathParam("id", body.getIdCard())
                .post(EndPoints.postCreateCheclList)
                .then()
                .spec(responseSpecificationOK)
                .contentType(ContentType.JSON).extract().response();
        body.setIdCheckList(response.jsonPath().getString("id"));

        body.setName("Понять протокол HTTP");
        Response response1 = given()
                .spec(requestSpecification)
                .body(body)
                .when()
                .pathParam("id", body.getIdCheckList())
                .post(EndPoints.postCreateCheckitems)
                .then()
                .spec(responseSpecificationOK)
                .contentType(ContentType.JSON).extract().response();
        body.getIdAllCheckItems().add(response1.jsonPath().getString("id"));

         body.setName("Выучить методы запросов");
        Response response2 = given()
                .spec(requestSpecification)
                .body(body)
                .when()
                .pathParam("id", body.getIdCheckList())
                .post(EndPoints.postCreateCheckitems)
                .then()
                .spec(responseSpecificationOK)
                .contentType(ContentType.JSON).extract().response();
        body.getIdAllCheckItems().add(response2.jsonPath().getString("id"));

    }

    @Test(priority = 8, groups = {"TestAPI"})
    public void updateCheckItem(){
        body.setName("Понять протокол HTTP");
       body.setState("complete");
        given()
                .spec(requestSpecification)
                .body(body)
                .when()
                .pathParam("id", body.getIdCard())
                .pathParam("idCheckItem", body.getIdAllCheckItems().get(0))
                .put(EndPoints.putUpdateCheckItem)
                .then()
                .spec(responseSpecificationOK);
    }

    @Test(priority = 9, groups = {"TestAPI"})
    public void createListNewColumn(){
        body.setName("Done");
        Response response = given()
                .spec(requestSpecification)
                .body(body)
                .when()
                .post(EndPoints.postCreateList)
                .then()
                .spec(responseSpecificationOK)
                .contentType(ContentType.JSON).extract().response();

        String idList = response.jsonPath().getString("id");
        body.setIdList(idList);
        body.getIdAllLists().add(idList);
    }

    @Test(priority = 10, groups = {"TestAPI"})
    public void moveCard(){
        body.setName("Карточка для изучения API");
        given()
                .spec(requestSpecification)
                .body(body)
                .when()
                .pathParam("id", body.getIdCard())
                .put(EndPoints.putUpdateCard)
                .then()
                .spec(responseSpecificationOK);

    }

    @Test(priority = 11, groups = {"TestAPI"})
    public void archiveList(){
        Body body1 = new Body();
        body1.setKey(DBManager.getKey());
        body1.setToken(DBManager.getToken());
        body1.setValue("true");
        given()
                .spec(requestSpecification)
                .body(body1)
                .when()
                .pathParam("id", body.getIdAllLists().get(0))
                .put(EndPoints.putArchiveList)
                .then()
                .spec(responseSpecificationOK);

    }

    @Test(priority = 12, groups = {"TestAPI"})
    public void updateCheckItemNew(){
        body.setName("Выучить методы запросов");
        body.setState("complete");
        given()
                .spec(requestSpecification)
                .body(body)
                .when()
                .pathParam("id", body.getIdCard())
                .pathParam("idCheckItem", body.getIdAllCheckItems().get(1))
                .put(EndPoints.putUpdateCheckItem)
                .then()
                .spec(responseSpecificationOK);
    }

    @Test(priority = 13, groups = {"TestAPI"})
    public void createComment(){
        body.setText(":thumbsup:");
        given()
                .spec(requestSpecification)
                .body(body)
                .when()
                .pathParam("id", body.getIdCard())
                .post(EndPoints.postCreateComment)
                .then()
                .spec(responseSpecificationOK);

    }

    @AfterClass(groups = {"TestAPI"})
    public void theEnd(){
        System.out.println("API тест завершился");
    }

}
