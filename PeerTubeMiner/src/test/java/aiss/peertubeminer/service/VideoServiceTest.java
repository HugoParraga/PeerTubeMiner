package aiss.peertubeminer.service;

import aiss.peertubeminer.model.videominer.VMChannel;
import aiss.peertubeminer.model.videominer.VMVideo;
import ch.qos.logback.core.net.SyslogOutputStream;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VideoServiceTest {

    @Autowired
    VideoService service;

    @Autowired
    ChannelService service2;

    @Test
    void getVideo() {
        String channelHandle = "13751932-5a66-45a0-9605-8466d41cc948@peertube2.cpy.re";
        List<VMVideo> videos = service.getVideo(channelHandle, 10);
        System.out.println(videos);
    }

    @Test
    void getVideoPeerTube() {
        String channelHandle = "13751932-5a66-45a0-9605-8466d41cc948@peertube2.cpy.re";
        List<VMVideo> videos = service.getVideo(channelHandle, 10);
        System.out.println(videos);
    }

    @Test
    void postVideo() {
        String channelHandle = "13751932-5a66-45a0-9605-8466d41cc948@peertube2.cpy.re";
        String apiKey = "guille6767";
        VMChannel channel = service2.getChannel(channelHandle);
        service.postVideo(channelHandle, channel.getId(), 10, apiKey);
        System.out.println(channel);
    }

    @Test
    void testPostVideo() {
        String channelHandle = "13751932-5a66-45a0-9605-8466d41cc948@peertube2.cpy.re";
        String apiKey = "guille6767";
        VMChannel channel = service2.getChannel(channelHandle);
        VMVideo video = new VMVideo();
        service.postVideo(channel.getId(), video, apiKey);
        System.out.println(channel);
    }

    @Test
    void updateVideo() {
        String channelHandle = "13751932-5a66-45a0-9605-8466d41cc948@peertube2.cpy.re";
        String apiKey = "guille6767";
        VMVideo video = service.updateVideo(new VMVideo(), apiKey);
        System.out.println(video);
    }
}