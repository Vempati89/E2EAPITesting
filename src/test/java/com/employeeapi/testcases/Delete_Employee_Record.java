package com.employeeapi.testcases;

import com.employeeapi.base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Delete_Employee_Record extends TestBase {

    @BeforeClass
    void DeleteEmployee() throws InterruptedException {
        logger.info("*************Started Delete_Employee_Record*************");
        RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1/";
        httpRequest = RestAssured.given();
        response = httpRequest.request(Method.GET, "/employees");

        JsonPath jsonPathEvaluator = response.jsonPath();
        String empID = jsonPathEvaluator.get("[0].id");
        response = httpRequest.request(Method.DELETE, "/delete/" + empID);

        Thread.sleep(5000);
    }

    @Test
    void CheckResponseBody() {
        String response_body = response.getBody().asString();
        Assert.assertTrue(response_body.contains("successfully! deleted Records"));
    }

    @Test
    void CheckStatusCode() {
        int status_code = response.getStatusCode();
        Assert.assertEquals(status_code, 200);
    }

    @Test
    void CheckStatusLine() {
        String status_line = response.getStatusLine();
        Assert.assertEquals(status_line, "HTTP/1.1 200 OK");
    }

    @Test
    void CheckContentType() {
        String content_type = response.header("Content-Type");
        Assert.assertEquals(content_type, "text/html; charset=UTF-8");
    }

    @AfterClass
    void teardown() {
        logger.info("*************Finished Delete_Employee_Record*************");
    }


}
