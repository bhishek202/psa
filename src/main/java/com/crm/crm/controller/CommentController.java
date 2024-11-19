package com.crm.crm.controller;

import com.crm.crm.entity.Comment;
import com.crm.crm.entity.Post;
import com.crm.crm.repository.CommentRepository;
import com.crm.crm.repository.PostRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {
    private PostController postController;
    private CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentController(PostController postController, CommentRepository commentRepository,
                             PostRepository postRepository) {
        this.postController = postController;
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }
    @PostMapping
    public String createComment(
            @RequestBody Comment comment,
            @RequestParam long postId
    ){
        Post post = postRepository.findById(postId).get();
        comment.setPost(post);
        commentRepository.save(comment);
        return "Comment created successfully";
    }
}
