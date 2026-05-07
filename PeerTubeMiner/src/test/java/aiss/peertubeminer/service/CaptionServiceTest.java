package aiss.peertubeminer.service;

import aiss.peertubeminer.model.videominer.VMCaption;
import aiss.peertubeminer.model.videominer.VMComment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CaptionServiceTest {

    @Autowired
    CaptionService service;

    @Test
    void getCaption() {
        String videoId = "13751932-5a66-45a0-9605-8466d41cc948@peertube2.cpy.re";
        List<VMCaption> captions = service.getCaption(videoId);
        System.out.println(captions);
    }

    @Test
    void postCaption() { //TODO SERÍA REALMENTE postCaption UNA LIST?
        String videoId = "13751932-5a66-45a0-9605-8466d41cc948@peertube2.cpy.re";
        String vmVideoId = "13751932-5a66-45a0-9605-8466d41cc948@peertube2.cpy.re";
        String apiKey = "guille6767";
        List<VMCaption> captions = service.postCaption(videoId, vmVideoId, apiKey);
        System.out.println(captions);
    }
}