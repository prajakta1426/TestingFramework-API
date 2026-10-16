package com.ccst.api;

import com.ccst.data.RoomExistingData;
import com.ccst.framework.Config;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public final class RoomApi {
    private RoomApi(){}

    private static RequestSpecification request(){
        return given().baseUri(Config.get("baseUrl")).contentType(ContentType.JSON);
    }

    public static String Login(){
        return login(Config.get("admin.username"),Config.get("admin.password"));
    }

    public static String login(String username,String password){
        return request()
                .body("{\"username\":\""+username+"\",\"password\":\""+password+"\"}")
                .post("/api/auth/login")
                .then().statusCode(200)
                .extract().path("token");

    }

    public static Response createRoom(String token, RoomExistingData room){
        return request().cookie("token",token).body(room.toJson()).post("/api/room");
    }
/*
    public static Response createRoomWithoutLogin(RoomData room){
        return request().body(room.toJson()).post("/api/room");
    }
    */
}
