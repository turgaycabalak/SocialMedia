package com.project.questapp.business;

import com.project.questapp.core.exception.UserNotFoundException;
import com.project.questapp.dataAccess.CommentRepository;
import com.project.questapp.dto.CommentRequest;
import com.project.questapp.dto.CommentUpdateRequest;
import com.project.questapp.entities.Comment;
import com.project.questapp.entities.Post;
import com.project.questapp.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserService userService;
    private final PostService postService;


    protected Comment isCommentExistsById(Long commentId){
        return this.commentRepository.findById(commentId)
                .orElseThrow(
                        () -> new UserNotFoundException("Bu id'ye ait Comment bulunamadi. CommentId: "+commentId));
    }



    public List<Comment> getAllComments(Optional<Long> userId, Optional<Long> postId) {
        if(userId.isPresent() && postId.isPresent()){
            return this.commentRepository.findByUserIdAndPostId(userId.get(), postId.get());
        }else if (userId.isPresent()){
            return this.commentRepository.findByUserId(userId.get());
        }else if (postId.isPresent()){
            return this.commentRepository.findByPostId(postId.get());
        }else{
            return this.commentRepository.findAll();
        }
    }


    public Comment getOneCommentById(Long commentId) {
        return this.isCommentExistsById(commentId);
    }


    public Comment createOneComment(CommentRequest commentRequest) {
        User user = this.userService.isUserExistsById(commentRequest.getUserId());
        Post post = this.postService.isPostExistsById(commentRequest.getPostId());

        return this.commentRepository.save(new Comment(post,user,commentRequest.getText()));
    }


    public Comment updateOneCommentById(Long commentId, CommentUpdateRequest commentUpdateRequest) {
        Optional<Comment> commentOptional = this.commentRepository.findById(commentId);
        if(commentOptional.isPresent()){
            Comment foundComment = commentOptional.get();

            foundComment.setText(commentUpdateRequest.getText());

            return this.commentRepository.save(foundComment);//updated and then saved
        }else{
            throw new IllegalStateException("Bu id'ye ait herhangi bir comment bulunamadi!");
        }
    }


    public String deleteOneCommentById(Long commentId) {
        this.isCommentExistsById(commentId);
        this.commentRepository.deleteById(commentId);
        return commentId+" id'li comment silindi.";
    }


}
