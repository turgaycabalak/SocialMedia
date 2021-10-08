package com.project.questapp.business;

import com.project.questapp.core.exception.PostNotFoundException;
import com.project.questapp.core.exception.UserNotFoundException;
import com.project.questapp.dataAccess.PostRepository;
import com.project.questapp.dto.PostRequest;
import com.project.questapp.dto.PostUpdateRequest;
import com.project.questapp.entities.Post;
import com.project.questapp.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserService userService;


    protected Post isPostExistsById(Long postId){
        return this.postRepository.findById(postId)
                .orElseThrow(
                        () -> new UserNotFoundException("Bu id'ye ait Post bulunamadi. PostId: "+postId));
    }



    public List<Post> getAllPosts(Optional<Long> userId) {
        if(userId.isPresent()){
            return this.postRepository.findByUserId(userId.get());
        }
        return this.postRepository.findAll();
    }


    public Post getOnePostById(Long postId) {
        return this.isPostExistsById(postId);
    }


    public Post createOnePost(PostRequest postRequest) {
        User user = this.userService.isUserExistsById(postRequest.getUserId());

        return this.postRepository.save(new Post(user,postRequest.getTitle(),postRequest.getText()));
    }


    public Post updateOnePostById(Long postId, PostUpdateRequest postUpdateRequest) {
        Optional<Post> postOptional = this.postRepository.findById(postId);
        if(postOptional.isPresent()){
            Post foundPost = postOptional.get();

            foundPost.setTitle(postUpdateRequest.getTitle());
            foundPost.setText(postUpdateRequest.getText());

            return this.postRepository.save(foundPost);//updated and then saved
        }else{
            throw new IllegalStateException("Bu id'ye ait herhangi bir post bulunamadi!");
        }
    }


    public String deleteOnePostById(Long postId) {
        this.isPostExistsById(postId);
        this.postRepository.deleteById(postId);
        return postId+" id'li post silindi.";
    }

}
