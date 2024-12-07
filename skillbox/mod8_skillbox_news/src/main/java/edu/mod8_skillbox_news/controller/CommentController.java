package edu.mod8_skillbox_news.controller;

import edu.mod8_skillbox_news.aspect.RequiresNewsOwnership;
import edu.mod8_skillbox_news.dto.CommentDTO;
import edu.mod8_skillbox_news.model.Comment;
import edu.mod8_skillbox_news.model.User;
import edu.mod8_skillbox_news.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER', 'ROLE_MODERATOR')")
    @GetMapping("/news/{newsId}")
    public ResponseEntity<List<Comment>> getCommentsByNewsId(@PathVariable Long newsId) {
        return ResponseEntity.ok(commentService.findAllByNewsId(newsId));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER', 'ROLE_MODERATOR')")
    @GetMapping("/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable Long id) {
        return commentService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER', 'ROLE_MODERATOR')")
    @PostMapping
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment,
                                                 @RequestParam Long newsId,
                                                 @AuthenticationPrincipal User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.createComment(comment, newsId, user.getId()));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER', 'ROLE_MODERATOR')")
    @RequiresNewsOwnership
    @PutMapping("/{userId}/{commentId}")
    public ResponseEntity<Comment> updateComment(@PathVariable Long userId,
                                                 @RequestBody CommentDTO comment,
                                                 @PathVariable Long commentId) {
        return ResponseEntity.ok(commentService.updateComment(commentId, comment));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER', 'ROLE_MODERATOR')")
    @RequiresNewsOwnership
    @DeleteMapping("/{userId}/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long userId, @PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }
}
