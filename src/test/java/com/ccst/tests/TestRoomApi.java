package com.ccst.tests;

import com.ccst.api.RoomApi;
import com.ccst.data.RoomExistingData;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestRoomApi {
    @Test
    public void createdRoomCanBeFoundDeleted()
    {
        RoomExistingData roomExistingData = new RoomExistingData();
        String token = RoomApi.Login();

        Response created = RoomApi.createRoom(token, roomExistingData);
        Assert.assertEquals(created.getStatusCode(), 200);
        Assert.assertEquals(created.path("success"), true);
    }
}
