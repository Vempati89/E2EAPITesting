package com.employeeapi.testcases;

import com.employeeapi.base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Get_All_Employees extends TestBase {

    @BeforeClass
    void getAllEmployees() throws InterruptedException {
        logger.info("*************Started Get_All_Employees*************");
        RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1/";
        httpRequest = RestAssured.given();
        response = httpRequest.request(Method.GET, "/employees");

        Thread.sleep(3);

    }

    @Test
    void CheckResponseBody() {
        logger.info("*************Check Response Body*************");
        String response_body = response.getBody().asString();
        logger.info("Response Body => " + response_body);
        Assert.assertNotNull(response_body);

    }

    @Test
    void CheckStatusCode() {
        logger.info("*************Check Status Code*************");
        int status_code = response.getStatusCode();
        logger.info("Status Code is " + status_code);
        Assert.assertEquals(200, status_code);
    }

    @Test
    void CheckResponseTime() {
        logger.info("*************Check Response Time*************");
        long response_time = response.getTime();
        logger.info("Response time is " + response_time);
        if (response_time > 2000) {
            logger.warn("Response time is greater than 2000");
        }
        Assert.assertTrue(response_time < 2000);
    }

    @Test
    void CheckStatusLine() {
        logger.info("*************Check Status Line*************");
        String status_line = response.getStatusLine();
        logger.info("Status Line is " + status_line);
        Assert.assertEquals("HTTP/1.1 200 OK", status_line);
    }

    @Test
    void CheckContentType () {
        logger.info("*************Check Status Line*************");
        String content_type = response.getContentType();
        logger.info("Content Type is "+ content_type);
        Assert.assertEquals("text/html; charset=UTF-8",content_type);
    }

    @Test
    void CheckServerType() {
        logger.info("*************Check Server Type*************");
        String server_type = response.header("Server");
        logger.info("Server Type is "+ server_type);
        Assert.assertEquals(server_type, "nginx/1.14.1");
    }

    @Test
    void CheckContentEncoding() {
        logger.info("*************Check Content Encoding*************");
        String content_encoding = response.header("Content-Encoding");
        logger.info("Content Encoding is "+ content_encoding);
        Assert.assertEquals("gzip",content_encoding);
    }

    @Test
    void CheckContentLength() {
        logger.info("*************Check Content Length*************");
        String content_length = response.header("Content-Length");
        logger.info("Content Encoding is "+ content_length);
        if(Integer.parseInt(content_length)<100)
            logger.warn("Content Length is less than 100");
        Assert.assertTrue(Integer.parseInt(content_length)>100);
    }

    @Test
    void CheckCookie () {
        logger.info("*************Print Cookie*************");
        String cookie = response.getCookie("PHPSESSID");
        logger.info("Cookie generated is "+cookie);
    }

    @AfterClass
    void teardown() {
        logger.info("*************Finished Get_All_Employees*************");
    }
}
