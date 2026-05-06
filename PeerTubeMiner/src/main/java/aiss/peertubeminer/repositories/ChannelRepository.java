package aiss.peertubeminer.repositories;

import aiss.peertubeminer.etl.Transformer;
import aiss.peertubeminer.model.peertube.CompleteChannel;
import aiss.peertubeminer.model.peertube.CompleteVideo;
import aiss.peertubeminer.model.peertube.Video;
import aiss.peertubeminer.model.videominer.*;
import aiss.peertubeminer.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ChannelRepository {
    @Autowired
    ChannelService channelService;
    @Autowired
    VideoService videoService;
    @Autowired
    CommentService commentService;
    @Autowired
    CaptionService captionService;
    @Autowired
    UserService userService;

    public VMChannel findByChannelHandle(String channelHandle, Integer maxVideos, Integer maxComments){
        List<VMVideo> completeVideos = new ArrayList<>();
        VMChannel channel = channelService.getChannel(channelHandle);
        List<Video> videos = videoService.getVideoPeerTube(channelHandle, maxVideos);
        for(Video video: videos){
            VMUser author = Transformer.createVMUser(video.getUser());
            List<VMComment> comments = commentService.getComment(video.getId().toString(), maxComments);
            List<VMCaption> captions = captionService.getCaption(video.getId().toString());
            VMVideo createdVideo = Transformer.createVMVideo(video);
            createdVideo.setAuthor(author);
            createdVideo.setComments(comments);
            createdVideo.setCaptions(captions);
            completeVideos.add(createdVideo);
        }
        channel.setVideos(completeVideos);
        return channel;
    }

}
