package edu.mod5_skillbox.controller;

import edu.mod5_skillbox.dto.SearchRequest;
import edu.mod5_skillbox.model.Book;
import edu.mod5_skillbox.service.BookService;
import edu.mod5_skillbox.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/book")
public class BookController {
    private final BookService bookService;
    private final CategoryService categoryService;

    @GetMapping
    public Book getBookByRequest(@RequestBody SearchRequest searchRequest) {
        return bookService.findByRequest(searchRequest);
    }

    @GetMapping("/{category_name}")
    public List<Book> getAllByCategoryName(@PathVariable("category_name") String categoryName) {
        return categoryService.findAllBooksByCategoryName(categoryName);
    }

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookService.create(book);
    }

    @PutMapping("{id}")
    public void updateBook(@PathVariable("id") Long id, @RequestBody Book book) {
        bookService.updateById(id, book);
    }
}
