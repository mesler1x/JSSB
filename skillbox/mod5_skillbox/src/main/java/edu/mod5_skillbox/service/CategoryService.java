package edu.mod5_skillbox.service;

import edu.mod5_skillbox.model.Book;
import edu.mod5_skillbox.repo.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Cacheable(value = "booksByCategory", key = "#category")
    public List<Book> findAllBooksByCategoryName(String categoryName) {
        return categoryRepository.findAll()
                .stream().filter(it -> it.getName().equals(categoryName))
                .flatMap(e -> e.getBooks().stream())
                .toList();
    }
}
