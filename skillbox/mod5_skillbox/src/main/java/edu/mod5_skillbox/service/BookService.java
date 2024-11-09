package edu.mod5_skillbox.service;

import edu.mod5_skillbox.dto.SearchRequest;
import edu.mod5_skillbox.model.Book;
import edu.mod5_skillbox.repo.BookRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    @Cacheable(value = "books", key = "#searchRequest.name + #searchRequest.author")
    public Book findByRequest(SearchRequest searchRequest) {
        return bookRepository.findByNameAndAuthor(searchRequest.name(), searchRequest.author());
    }

    @CacheEvict(value = {"books", "booksByCategory"}, allEntries = true)
    public Book create(Book book) {
        return bookRepository.save(book);
    }

    @CacheEvict(value = {"books", "booksByCategory"}, key = "#id", beforeInvocation = true)
    public void updateById(Long id, Book book) {
        Book repoBook = bookRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        book.setId(repoBook.getId());
        bookRepository.save(book);
    }

    @CacheEvict(value = {"books", "booksByCategory"}, key = "#id", beforeInvocation = true)
    public void deleteById(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new EntityNotFoundException();
        }
        bookRepository.deleteById(id);
    }
}
