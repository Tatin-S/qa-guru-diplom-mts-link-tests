package api.specs;

import common.config.ApiConfig;
import common.config.AuthDataConfig;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.internal.http.AuthConfig;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.aeonbits.owner.ConfigFactory;

import static common.helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
public class Specs {
    static final ApiConfig apiConfig = ConfigFactory.create(ApiConfig.class, System.getProperties());
    public static RequestSpecification requestSpecAuth = with()
            .filter(withCustomTemplates())
            .contentType(ContentType.JSON)
            .baseUri(apiConfig.authBaseURI())
            .basePath(apiConfig.authBasePath())
            .log().all();
    static final AuthDataConfig authConfig = ConfigFactory.create(AuthDataConfig.class, System.getProperties());
    public static RequestSpecification requestSpecEvent = with()
            .filter(withCustomTemplates())
            .contentType(ContentType.JSON)
            .log().all()
            .baseUri(apiConfig.baseURI())
            .basePath(apiConfig.basePath())
            .header("x-auth-token",authConfig.userToken());

    public static ResponseSpecification responseSpecStatusCode200 = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(LogDetail.ALL)
            .build();

    public static ResponseSpecification responseSpecStatusCode201 = new ResponseSpecBuilder()
            .expectStatusCode(201)
            .log(LogDetail.ALL)
            .build();
    public static ResponseSpecification responseSpecStatusCode404 = new ResponseSpecBuilder()
            .expectStatusCode(404)
            .log(LogDetail.ALL)
            .build();
    public static ResponseSpecification responseSpecStatusCode400 = new ResponseSpecBuilder()
            .expectStatusCode(400)
            .log(LogDetail.ALL)
            .build();
    public static ResponseSpecification responseSpecStatusCode204 = new ResponseSpecBuilder()
            .expectStatusCode(204)
            .log(LogDetail.ALL)
            .build();
}
