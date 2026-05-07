package aiss.peertubeminer.service;

import aiss.peertubeminer.etl.Transformer;
import aiss.peertubeminer.model.peertube.Video;
import aiss.peertubeminer.model.videominer.VMChannel;
import aiss.peertubeminer.model.videominer.VMVideo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ChannelServiceTest {
    @Autowired
    ChannelService service;

    @Autowired
    VideoService service2;

    @Test
    void getChannel() {
        String channelHandle = "13751932-5a66-45a0-9605-8466d41cc948@peertube2.cpy.re";
        VMChannel channel = service.getChannel(channelHandle);
        System.out.println(channel);
    }

    @Test
    void postChannel() {
        String channelHandle = "13751932-5a66-45a0-9605-8466d41cc948@peertube2.cpy.re";
        String apiKey = "guille6767";
        VMChannel channel = service.postChannel(channelHandle, apiKey);
        System.out.println(channel);
    }

    @Test
    void updateChannel() {
        String channelHandle = "13751932-5a66-45a0-9605-8466d41cc948@peertube2.cpy.re"; //TODO TENER EN CUENTA QUE LOS VALORES NO SON LOS CORRECTOS PARA EJECUTAR LOS TESTS
        String apiKey = "guille6767";
        VMChannel previousChannel = service.getChannel(channelHandle);
        VMChannel channel = service.updateChannel(previousChannel, apiKey);
    }
}