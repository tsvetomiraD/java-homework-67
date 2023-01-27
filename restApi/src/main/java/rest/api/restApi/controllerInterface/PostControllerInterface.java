package rest.api.restApi.controllerInterface;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rest.api.restApi.model.Comment;
import rest.api.restApi.model.Post;

import java.util.Map;

@RequestMapping("/default")
public interface PostControllerInterface {
    @GetMapping
    public Post[] getAllPosts();

    @PostMapping
    public Post createPost(@RequestBody Post post);

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Integer id);

    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable int id, @RequestBody Post post);

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deletePost(@PathVariable int id);

    @GetMapping("/{id}/comments")
    public Comment[] getPostCommentsFromPostById(@PathVariable Integer id);
}
