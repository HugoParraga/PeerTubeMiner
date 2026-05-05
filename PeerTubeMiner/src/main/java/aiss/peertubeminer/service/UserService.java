package aiss.peertubeminer.service;

import aiss.peertubeminer.etl.Transformer;
import aiss.peertubeminer.model.peertube.User;
import aiss.peertubeminer.model.peertube.VideoList;
import aiss.peertubeminer.model.videominer.VMUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    //https://peertube.cpy.re/api/v1/video-channels/{channelHandle}/videos
    //http://localhost:8080/videominer/users

    @Autowired
    RestTemplate restTemplate;

    public List<VMUser> getUser(String channelHandle){
        String uri = String.format("https://peertube.cpy.re/api/v1/video-channels/%s/videos", channelHandle);
        VideoList videoList = restTemplate.getForObject(uri, VideoList.class);
        return videoList.getVideo().stream()
                .map(vid -> Transformer.createVMUser(vid.getUser()))
                .toList();
    }

    public List<VMUser> postUser(String channelHandle, String apiKey) {
        List<VMUser> res = new ArrayList<>();
        String getUri = String.format("https://peertube.cpy.re/api/v1/video-channels/%s/videos", channelHandle);
        String postUri = "http://localhost:8080/videominer/users";
        VideoList videoList = restTemplate.getForObject(getUri, VideoList.class);
        List<VMUser> users = videoList.getVideo().stream()
                .map(vid -> Transformer.createVMUser(vid.getUser()))
                .toList();
        for (VMUser u : users) {
            HttpEntity<VMUser> request = new HttpEntity<>(u, AuxiliarFunction.getApiKeyHeader(apiKey));
            ResponseEntity<VMUser> response = restTemplate.exchange(postUri, HttpMethod.POST, request, VMUser.class);
            res.add(response.getBody());
        }
        return res;
    }
}
