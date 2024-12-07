package edu.mod8_skillbox_news.dto;

import lombok.Getter;

@Getter
public class CommentDTO {
    private Long id;
    private String content;
    private Long authorId;
    private Long newsId;
}
