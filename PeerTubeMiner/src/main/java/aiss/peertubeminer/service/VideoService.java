package aiss.peertubeminer.service;

import aiss.peertubeminer.etl.Transformer;
import aiss.peertubeminer.model.peertube.Video;
import aiss.peertubeminer.model.peertube.VideoList;
import aiss.peertubeminer.model.videominer.VMVideo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class VideoService {

    //https://peertube.cpy.re/api/v1/video-channels/{channelHandle}/videos
    //http://localhost:8080/videominer/videos/channels/{channelId}/videos

    @Autowired
    RestTemplate restTemplate;

    public List<VMVideo> getVideo(String channelHandle, Integer maxVideo){
        String uri = String.format("https://peertube.cpy.re/api/v1/video-channels/%s/videos?count=%d", channelHandle, maxVideo);
        VideoList videoList = restTemplate.getForObject(uri, VideoList.class);
        return videoList.getVideo().stream()
                .map(vid -> Transformer.createVMVideo(vid))
                .toList();
    }

    public List<Video> getVideoPeerTube(String channelHandle, Integer maxVideo){
        String uri = String.format("https://peertube.cpy.re/api/v1/video-channels/%s/videos?count=%d", channelHandle, maxVideo);
        VideoList videoList = restTemplate.getForObject(uri, VideoList.class);
        return videoList.getVideo();
    }

    public List<VMVideo> postVideo(String channelHandle, String vmChannelId, Integer maxVideo){
        List<VMVideo> res = new ArrayList<>();
        String getUri = String.format("https://peertube.cpy.re/api/v1/video-channels/%s/videos/%d", channelHandle, maxVideo, maxComments);
        String postUri = String.format("http://localhost:8080/videominer/videos/channels/%s/videos", vmChannelId);
        VideoList videoList = restTemplate.getForObject(getUri, VideoList.class);
        List<VMVideo> videos = videoList.getVideo().stream()
                .map(vid -> Transformer.createVMVideo(vid))
                .toList();
        for (VMVideo vid: videos){
            ResponseEntity<VMVideo> response = restTemplate.postForEntity(postUri, vid, VMVideo.class);
            res.add(response.getBody());
        }
        return res;
    }
}
