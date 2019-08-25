package com.employeeapi.testcases;

import com.employeeapi.base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Get_Single_Employee_Record extends TestBase {

    @BeforeClass
    void getEmployeeData() throws InterruptedException {
        logger.info("*************Started Get_Single_Employee_Record*************");
        RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1/";
        httpRequest = RestAssured.given();
        response = httpRequest.request(Method.GET,"/employee/"+empID);
        Thread.sleep(3000);
    }

    @Test
    void CheckResponseBody() {
        logger.info("*************Check Response Body*************");
        String response_body = response.getBody().asString();
        Assert.assertTrue(response_body.contains(empID));
        logger.info("Response body is "+response_body);
    }

    @Test
    void CheckStatusCode() {
        logger.info("*************Check Status Code*************");
        int status_code = response.getStatusCode();
        Assert.assertEquals(status_code, 200);
        logger.info("Status Code on the record is "+status_code);
    }

    @Test
    void CheckResponseTime() {
        logger.info("*************Check Response Time*************");
        long response_time = response.getTime();
        Assert.assertTrue(response_time<62000);
        logger.info("Response time is "+response_time);
    }

    @Test
    void CheckStatusLine() {
        logger.info("*************Check Status Line*************");
        String status_line = response.getStatusLine();
        Assert.assertEquals(status_line, "HTTP/1.1 200 OK");
        logger.info("Status Line is "+ status_line);
    }

    @Test
    void CheckContentType() {
        logger.info("*************Check Content Type*************");
        String content_type = response.header("Content-Type");
        Assert.assertEquals(content_type, "text/html; charset=UTF-8");
        logger.info("Content Type is "+ content_type);
    }

    @Test
    void CheckServerType() {
        logger.info("*************Check Server Type*************");
        String server_type = response.header("Server");
        Assert.assertEquals(server_type, "nginx/1.14.1");
        logger.info("Server Type is "+ server_type);

    }

    @Test
    void CheckContentLength() {
        logger.info("*************Check Content Length*************");
        String content_length = response.header("Content-Length");
        Assert.assertTrue(Integer.parseInt(content_length)<1500);
        logger.info("Content Length is "+ content_length);

    }

    @AfterClass
    void teardown() {
        logger.info("*************Finished Get_Single_Employee_Record*************");
    }

}
