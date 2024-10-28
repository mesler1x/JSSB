package edu.mod4_skillbox.service;

import edu.mod4_skillbox.dto.CommentDTO;
import edu.mod4_skillbox.model.Comment;
import edu.mod4_skillbox.model.News;
import edu.mod4_skillbox.model.User;
import edu.mod4_skillbox.repository.CommentRepository;
import edu.mod4_skillbox.repository.NewsRepository;
import edu.mod4_skillbox.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final NewsRepository newsRepository;
    private final UserRepository userRepository;

    public CommentService(CommentRepository commentRepository, NewsRepository newsRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.newsRepository = newsRepository;
        this.userRepository = userRepository;
    }

    public Comment createComment(Comment comment, Long newsId, Long authorId) {
        News news = newsRepository.findById(newsId).orElseThrow(() -> new RuntimeException("News not found"));
        User author = userRepository.findById(authorId).orElseThrow(() -> new RuntimeException("User not found"));
        comment.setNews(news);
        comment.setAuthor(author);
        return commentRepository.save(comment);
    }

    public Optional<Comment> findById(Long id) {
        return commentRepository.findById(id);
    }

    public List<Comment> findAllByNewsId(Long newsId) {
        return commentRepository.findAll().stream()
                .filter(comment -> comment.getNews().getId().equals(newsId))
                .collect(Collectors.toList());
    }

    public Comment updateComment(Long commentId, CommentDTO commentDTO) {
        var existingComment = commentRepository.findById(commentId).orElseThrow(() -> new RuntimeException("Comment not found"));
        existingComment.setContent(commentDTO.getContent());
        return commentRepository.save(existingComment);
    }

    public void deleteComment(Long id) {
        Comment existingComment = commentRepository.findById(id).orElseThrow(() -> new RuntimeException("Comment not found"));
        commentRepository.delete(existingComment);
    }
}
