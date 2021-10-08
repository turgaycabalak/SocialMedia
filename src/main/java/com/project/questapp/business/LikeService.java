package com.project.questapp.business;

import com.project.questapp.core.exception.UserNotFoundException;
import com.project.questapp.dataAccess.LikeRepository;
import com.project.questapp.dto.LikeRequest;
import com.project.questapp.entities.Like;
import com.project.questapp.entities.Post;
import com.project.questapp.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final UserService userService;
    private final PostService postService;


    protected Like isLikeExistsById(Long likeId){
        return this.likeRepository.findById(likeId)
                .orElseThrow(
                        () -> new UserNotFoundException("Bu id'ye ait Like bulunamadi. LikeId: "+likeId));
    }



    public List<Like> getAllLikes(Optional<Long> userId, Optional<Long> postId) {
        if(userId.isPresent() && postId.isPresent()){
            return this.likeRepository.findByUserIdAndPostId(userId.get(), postId.get()).get();
        }else if (userId.isPresent()){
            return this.likeRepository.findByUserId(userId.get());
        }else if (postId.isPresent()){
            return this.likeRepository.findByPostId(postId.get());
        }else{
            return this.likeRepository.findAll();
        }
    }


    public Like getOneLikeById(Long likeId) {
        return this.isLikeExistsById(likeId);
    }


    public Like createOneLike(LikeRequest likeRequest) {
        User user = this.userService.isUserExistsById(likeRequest.getUserId());
        Post post = this.postService.isPostExistsById(likeRequest.getPostId());

        return this.likeRepository.save(new Like(post,user));
    }


    public String deleteOneLikeById(Long likeId) {
        this.isLikeExistsById(likeId);
        this.likeRepository.deleteById(likeId);
        return likeId+" id'li like silindi.";
    }


}
