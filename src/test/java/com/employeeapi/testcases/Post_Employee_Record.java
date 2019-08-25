package com.employeeapi.testcases;

import com.employeeapi.base.TestBase;
import com.employeeapi.utilities.RestUtils;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Post_Employee_Record extends TestBase {

    String empName = RestUtils.empName();
    String empAge = RestUtils.empAge();
    String empSal = RestUtils.empSal();

    @BeforeClass
    void CreateEmployee() throws InterruptedException {
        logger.info("*************Started Post_Employee_Record*************");
        RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1/";
        httpRequest = RestAssured.given();
        JSONObject request_params = new JSONObject();
        request_params.put("name", empName);
        request_params.put("salary", empSal);
        request_params.put("age", empAge);

        httpRequest.header("Content-Type", "application/json");
        httpRequest.body(request_params.toJSONString());
        response = httpRequest.request(Method.POST, "/create");

        Thread.sleep(5000);
    }

    @Test
    void CheckResponseBody() {
        String response_body = response.getBody().asString();
        logger.info("Response body is "+ response_body);
        Assert.assertTrue(response_body.contains(empName));
        Assert.assertTrue(response_body.contains(empAge));
        Assert.assertTrue(response_body.contains(empSal));
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
        logger.info("*************Finished Post_Employee_Record*************");
    }

}
