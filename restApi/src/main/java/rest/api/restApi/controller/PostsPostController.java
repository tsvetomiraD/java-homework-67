package rest.api.restApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rest.api.restApi.controllerInterface.PostControllerInterface;
import rest.api.restApi.mapper.Mapper;
import rest.api.restApi.model.Comment;
import rest.api.restApi.model.Post;

import java.util.HashMap;
import java.util.Map;
@RestController
@RequestMapping("/posts")
public class PostsPostController implements PostControllerInterface {
    @Autowired
    private Mapper mapper;

    public Post[] getAllPosts() {
        Post post = new Post();
        post.id = 1;
        post.body = "vrtg";
        post.title = "vrknv";
        return new Post[] {post};
        //return mapper.getAllPosts();
    }

    public Post createPost(Post post)  {
        if (mapper.getPostById(post.id)==null) {
            int id = mapper.insertPost(post);
            return mapper.getPostById(post.id);
        } else {
            throw new RuntimeException(); //todo exception
        }
    }

    public ResponseEntity<Post> getPostById(Integer id) {
        Post post = mapper.getPostById(id);
        if(post==null) {
            throw new RuntimeException(); //todo
        }
        return ResponseEntity.ok(post);
    }

    public ResponseEntity<Post> updatePost(int id, Post post) {
        if (mapper.updatePost(post, id) == 0) {
            throw new RuntimeException(); //todo
        }

        return ResponseEntity.ok(mapper.getPostById(id));
    }

    public ResponseEntity<Map<String, Boolean>> deletePost( int id) {
        mapper.deletePost(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    public Comment[] getPostCommentsFromPostById(Integer id) {
        return mapper.getCommentsByPostId(id);
    }
}