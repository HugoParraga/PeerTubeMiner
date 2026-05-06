package aiss.peertubeminer.service;

import aiss.peertubeminer.etl.Transformer;
import aiss.peertubeminer.model.peertube.Comment;
import aiss.peertubeminer.model.peertube.CommentList;
import aiss.peertubeminer.model.videominer.VMComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    //https://peertube.cpy.re/api/v1/videos/{videoId}/comment-threads
    //http://localhost:8080/videominer/comments/videos/{videoId}/comments

    @Autowired
    RestTemplate restTemplate;

    public List<VMComment> getComment(String videoId, Integer maxComments){
        String uri = String.format("https://peertube.cpy.re/api/v1/videos/%s/comment-threads?count=%d", videoId, maxComments);
        CommentList commentList = restTemplate.getForObject(uri, CommentList.class);
        return commentList.getComment().stream()
                .map(com -> Transformer.createVMComment(com))
                .toList();
    }

    public List<VMComment> postComment(String videoId, String vmVideoId, Integer maxComments){
        List<VMComment> res = new ArrayList<>();
        String getUri = String.format("https://peertube.cpy.re/api/v1/videos/%s/comment-threads?count=%d", videoId, maxComments);
        String postUri = String.format("//http://localhost:8080/videominer/comments/videos/%s/comments", vmVideoId);
        CommentList commentList = restTemplate.getForObject(getUri, CommentList.class);
        List<VMComment> comments = commentList.getComment().stream()
                .map(com -> Transformer.createVMComment(com))
                .toList();
        for (VMComment com: comments){
            ResponseEntity<VMComment> response = restTemplate.postForEntity(postUri, com, VMComment.class);
            res.add(response.getBody());
        }
        return res;
    }
}
