package aiss.peertubeminer.service;

import aiss.peertubeminer.model.peertube.Caption;
import aiss.peertubeminer.model.videominer.VMCaption;
import aiss.peertubeminer.etl.Transformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class CaptionService {
    //https://peertube.cpy.re/api/v1/videos/{videoId}/captions
    //http://localhost:8080/videominer/captions/videos/{videoId}/captions

    @Autowired
    RestTemplate restTemplate;

    public VMCaption getCaption(String videoId){
        String uri = String.format("https://peertube.cpy.re/api/v1/videos/%s/captions", videoId);
        Caption caption = restTemplate.getForObject(uri,Caption.class);
        VMCaption postCaption = Transformer.createVMCaption(caption);
        return postCaption;
    }

    public VMCaption postCaption(String videoId, String vmVideoId){
        String getUri = String.format("https://peertube.cpy.re/api/v1/videos/%s/captions", videoId);
        String postUri = String.format("http://localhost:8080/videominer/captions/videos/%s/captions", vmVideoId);
        Caption caption = restTemplate.getForObject(getUri,Caption.class);
        VMCaption postCaption = Transformer.createVMCaption(caption);
        ResponseEntity<VMCaption> response = restTemplate.postForEntity(postUri, postCaption, VMCaption.class);
        return response.getBody();
    }


}
