package sber_test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static sber_test.TestBase.getConfigParametr;

public class RegistrationTestAPI{

    @Test(description = "POST")
    public void postRequestExampleTest() {
        String someRandomString = String.format("%1$TH%1$TM%1$TS", new Date());

        JSONObject requestBody = new JSONObject();
        String currentTime = new SimpleDateFormat("MMdd_HHmmss").format(Calendar.getInstance().getTime());
        requestBody.put("email", getConfigParametr("email") + currentTime + "@mail.ru");
        requestBody.put("name", getConfigParametr("loginName"));
        requestBody.put("password", getConfigParametr("password"));

        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("Accept-Encoding", "gzip, deflate, br");
        request.body(requestBody.toString());
        Response response = request.post("http://users.bugred.ru/tasks/rest/doregister");

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
        System.out.println(response.getBody().asString());
        response.getBody().print();
    }
}
