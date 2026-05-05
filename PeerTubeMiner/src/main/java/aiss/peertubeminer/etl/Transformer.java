package aiss.peertubeminer.etl;

import aiss.peertubeminer.model.peertube.*;
import aiss.peertubeminer.model.videominer.*;

import java.util.Comparator;
import java.util.UUID;

public class Transformer {

    public static VMChannel createVMChannel(Channel channel) {
        String id = UUID.randomUUID().toString();
        return new VMChannel(id,
                channel.getName(),
                channel.getDescription(),
                channel.getCreatedTime());
    }

    public static VMCaption createVMCaption(Caption caption) {
        return new VMCaption(UUID.randomUUID().toString(),
                caption.getLink(),//name en videoMiner
                caption.getLanguage().getLabel());
    }

    public static VMComment createVMComment(Comment comment) {
        return new VMComment(UUID.randomUUID().toString(),
                comment.getText(),
                comment.getCreatedOn());
    }

    public static VMUser createVMUser(User user){
        return new VMUser(user.getName(),
                user.getUserLink(),
                user.getPictureList().stream().max(Comparator.comparing(pic -> pic.getCreatedAt())).get().getPictureLink());
    }

    public static VMVideo createVMVideo(Video video) {
        return new VMVideo(UUID.randomUUID().toString(),
                video.getName(),
                video.getDescription(),
                video.getReleaseTime());
    }
}
