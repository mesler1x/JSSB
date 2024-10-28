package edu.mod4_skillbox.dto;

import lombok.Getter;

@Getter
public class CommentDTO {
    private Long id;
    private String content;
    private Long authorId;
    private Long newsId;
}
