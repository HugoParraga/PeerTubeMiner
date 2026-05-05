package aiss.peertubeminer.service;

import aiss.peertubeminer.etl.Transformer;
import aiss.peertubeminer.model.peertube.Channel;
import aiss.peertubeminer.model.videominer.VMCaption;
import aiss.peertubeminer.model.videominer.VMChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class ChannelService {

    @Autowired
    RestTemplate restTemplate;

    //http://localhost:8080/videominer/channels
    //https://peertube.cpy.re/api/v1/video-channels/{channelHandle}

    public VMChannel getChannel(String channelHandle){ //channelHandle is a concatenation of name@host
        String uri = String.format("https://peertube.cpy.re/api/v1/video-channels/%s", channelHandle);
        Channel channel = restTemplate.getForObject(uri,Channel.class);
        VMChannel postChannel = Transformer.createVMChannel(channel);
        return postChannel;
    }

    public VMChannel postChannel(String channelHandle){
        String getUri = String.format("https://peertube.cpy.re/api/v1/video-channels/%s", channelHandle);
        String postUri = "http://localhost:8080/videominer/channels";
        Channel channel = restTemplate.getForObject(getUri,Channel.class);
        VMChannel postChannel = Transformer.createVMChannel(channel);
        ResponseEntity<VMChannel> response = restTemplate.postForEntity(postUri, postChannel, VMChannel.class);
        return response.getBody();
    }
}
