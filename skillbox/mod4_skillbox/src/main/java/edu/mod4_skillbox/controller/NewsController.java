package edu.mod4_skillbox.controller;

import edu.mod4_skillbox.aspect.RequiresNewsOwnership;
import edu.mod4_skillbox.dto.NewsDTO;
import edu.mod4_skillbox.service.NewsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/news")
public class NewsController {

    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping
    public ResponseEntity<Page<NewsDTO>> getAllNews(Pageable pageable) {
        return ResponseEntity.ok(newsService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsDTO> getNewsById(@PathVariable Long id,
                                               @RequestParam(required = false, name = "category") Long categoryId,
                                               @RequestParam(required = false, name = "author") Long author) {
        return ResponseEntity.ok(newsService.findById(id, categoryId, author));
    }

    @PostMapping
    public ResponseEntity<NewsDTO> createNews(@RequestBody NewsDTO dto) {
        return ResponseEntity.ok(newsService.createNews(dto));
    }

    @RequiresNewsOwnership
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{authorId}/{newsId}")
    public void updateNews(@PathVariable Long authorId, @PathVariable Long newsId, @RequestBody NewsDTO updateRequest) {
        newsService.updateById(newsId, updateRequest);
    }

    @RequiresNewsOwnership
    @DeleteMapping("/{authorId}/{newsId}")
    public ResponseEntity<Void> deleteNews(@PathVariable Long authorId, @PathVariable Long newsId) {
        newsService.deleteNews(newsId);
        return ResponseEntity.noContent().build();
    }
}
