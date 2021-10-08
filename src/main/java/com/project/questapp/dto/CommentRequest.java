package com.project.questapp.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;


@Getter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class CommentRequest {

    private Long postId;
    private Long userId;
    private String text;

}
