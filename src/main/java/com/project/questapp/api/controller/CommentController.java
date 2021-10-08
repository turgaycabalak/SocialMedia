package com.project.questapp.api.controller;

import com.project.questapp.business.CommentService;
import com.project.questapp.dto.CommentRequest;
import com.project.questapp.dto.CommentUpdateRequest;
import com.project.questapp.entities.Comment;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/comments")
public class CommentController {

    private final CommentService commentService;


    @GetMapping
    public ResponseEntity<List<Comment>> getAllComments(@RequestParam Optional<Long> userId,
                                                        @RequestParam Optional<Long> postId){
        return ResponseEntity.ok(this.commentService.getAllComments(userId, postId));
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<Comment> getOneComment(@PathVariable Long commentId){
        return ResponseEntity.ok(this.commentService.getOneCommentById(commentId));
    }

    @PostMapping
    public ResponseEntity<Comment> createOneComment(@RequestBody CommentRequest commentRequest){
        return ResponseEntity.ok(this.commentService.createOneComment(commentRequest));
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<Comment> updateOneComment(@PathVariable Long commentId,
                                                    @RequestBody CommentUpdateRequest commentUpdateRequest){
        return ResponseEntity.ok(this.commentService.updateOneCommentById(commentId,commentUpdateRequest));
    }

    @DeleteMapping("/{commentId]}")
    public ResponseEntity<String> deleteOneComment(@PathVariable Long commentId){
        return ResponseEntity.ok(this.commentService.deleteOneCommentById(commentId));
    }


}
