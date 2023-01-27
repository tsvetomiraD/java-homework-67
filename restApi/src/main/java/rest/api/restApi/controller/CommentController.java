package rest.api.restApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rest.api.restApi.controllerInterface.CommentControllerInterface;
import rest.api.restApi.mapper.Mapper;
import rest.api.restApi.model.Comment;

@RestController
@RequestMapping("/comments")
public class CommentController implements CommentControllerInterface {
    @Autowired
    private Mapper mapper;

    public Comment[] getPostComments() {
        return mapper.getAllComments();
    }
}
