package rest.api.restApi.controllerInterface;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import rest.api.restApi.model.Comment;

@RequestMapping("/default")
public interface CommentControllerInterface {
    @GetMapping("/comments")
    public Comment[] getPostComments();
}
