package aiss.peertubeminer.service;

import aiss.peertubeminer.model.videominer.VMUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService service;

    @Test
    void getUser() {
        String channelHandle = "13751932-5a66-45a0-9605-8466d41cc948@peertube2.cpy.re";
        List<VMUser> users = service.getUser(channelHandle);
        System.out.println(users);
    }

    @Test
    void postUser() {
        String channelHandle = "13751932-5a66-45a0-9605-8466d41cc948@peertube2.cpy.re";
        String apiKey = "guille6767";
        List<VMUser> users = service.postUser(channelHandle, apiKey);
        System.out.println(users);
    }
}