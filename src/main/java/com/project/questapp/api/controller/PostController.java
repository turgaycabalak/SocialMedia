package com.project.questapp.api.controller;

import com.project.questapp.business.PostService;
import com.project.questapp.dto.PostRequest;
import com.project.questapp.dto.PostUpdateRequest;
import com.project.questapp.entities.Post;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/posts")
public class PostController {

    private final PostService postService;


    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts(@RequestParam Optional<Long> userId){
        return ResponseEntity.ok(this.postService.getAllPosts(userId));
    }

    @GetMapping("/{postId}")
    public ResponseEntity<Post> getOnePost(@PathVariable Long postId){
        return ResponseEntity.ok(this.postService.getOnePostById(postId));
    }

    @PostMapping
    public ResponseEntity<Post> createOnePost(@RequestBody PostRequest postRequest){
        return ResponseEntity.ok(this.postService.createOnePost(postRequest));
    }

    @PutMapping("/{postId}")
    public ResponseEntity<Post> updateOnePost(@PathVariable Long postId,
                                              @RequestBody PostUpdateRequest postUpdateRequest){
        return ResponseEntity.ok(this.postService.updateOnePostById(postId,postUpdateRequest));
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deleteOnePost(@PathVariable Long postId){
        return ResponseEntity.ok(this.postService.deleteOnePostById(postId));
    }

}
