package com.project.questapp.api.controller;

import com.project.questapp.business.LikeService;
import com.project.questapp.dto.LikeRequest;
import com.project.questapp.entities.Like;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/likes")
public class LikeController {

    private final LikeService likeService;


    @GetMapping
    public ResponseEntity<List<Like>> getAllLikes(@RequestParam Optional<Long> userId,
                                                  @RequestParam Optional<Long> postId){
        return ResponseEntity.ok(this.likeService.getAllLikes(userId, postId));
    }

    @GetMapping("/{likeId}")
    public ResponseEntity<Like> getOneLike(@PathVariable Long likeId){
        return ResponseEntity.ok(this.likeService.getOneLikeById(likeId));
    }

    @PostMapping
    public ResponseEntity<Like> createOneLike(@RequestBody LikeRequest likeRequest){
        return ResponseEntity.ok(this.likeService.createOneLike(likeRequest));
    }

    @DeleteMapping("/{likeId]}")
    public ResponseEntity<String> deleteOneLike(@PathVariable Long likeId){
        return ResponseEntity.ok(this.likeService.deleteOneLikeById(likeId));
    }



}
