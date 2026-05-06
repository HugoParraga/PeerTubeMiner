package aiss.peertubeminer.service;

import aiss.peertubeminer.model.peertube.Caption;
import aiss.peertubeminer.model.peertube.CaptionList;
import aiss.peertubeminer.model.peertube.CommentList;
import aiss.peertubeminer.model.videominer.VMCaption;
import aiss.peertubeminer.etl.Transformer;
import aiss.peertubeminer.model.videominer.VMComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class CaptionService {
    //https://peertube.cpy.re/api/v1/videos/{videoId}/captions
    //http://localhost:8080/videominer/captions/videos/{videoId}/captions

    @Autowired
    RestTemplate restTemplate;

    public List<VMCaption> getCaption(String videoId){
        String uri = String.format("https://peertube.cpy.re/api/v1/videos/%s/captions", videoId);
        CaptionList captionList = restTemplate.getForObject(uri, CaptionList.class);
        return captionList.getCaptions().stream()
                .map(cap -> Transformer.createVMCaption(cap))
                .toList();
    }

    public List<VMCaption> postCaption(String videoId, String vmVideoId){
        List<VMCaption> res = new ArrayList<>();
        String getUri = String.format("https://peertube.cpy.re/api/v1/videos/%s/captions", videoId);
        String postUri = String.format("http://localhost:8080/videominer/captions/videos/%s/captions", vmVideoId);
        CaptionList captionList = restTemplate.getForObject(getUri, CaptionList.class);
        List<VMCaption> captions = captionList.getCaptions().stream()
                .map(cap -> Transformer.createVMCaption(cap))
                .toList();
        for (VMCaption cap: captions){
            ResponseEntity<VMCaption> response = restTemplate.postForEntity(postUri, cap, VMCaption.class);
            res.add(response.getBody());
        }
        return res;
    }

}
