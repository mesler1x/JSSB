package edu.mod4_skillbox.controller;

import edu.mod4_skillbox.aspect.RequiresNewsOwnership;
import edu.mod4_skillbox.dto.CommentDTO;
import edu.mod4_skillbox.model.Comment;
import edu.mod4_skillbox.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/news/{newsId}")
    public ResponseEntity<List<Comment>> getCommentsByNewsId(@PathVariable Long newsId) {
        return ResponseEntity.ok(commentService.findAllByNewsId(newsId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable Long id) {
        return commentService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment,
                                                 @RequestParam Long newsId,
                                                 @RequestParam Long authorId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.createComment(comment, newsId, authorId));
    }

    @RequiresNewsOwnership
    @PutMapping("/{userId}/{commentId}")
    public ResponseEntity<Comment> updateComment(@PathVariable Long userId,
                                                 @RequestBody CommentDTO comment,
                                                 @PathVariable Long commentId) {
        return ResponseEntity.ok(commentService.updateComment(commentId, comment));
    }

    @RequiresNewsOwnership
    @DeleteMapping("/{userId}/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long userId, @PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }
}
