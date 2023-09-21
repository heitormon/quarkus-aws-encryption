package br.nozu.heitor.aws.encryption.it;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class AwsEncryptionResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
                .when().get("/aws-encryption")
                .then()
                .statusCode(200)
                .body(is("Hello aws-encryption"));
    }
}
