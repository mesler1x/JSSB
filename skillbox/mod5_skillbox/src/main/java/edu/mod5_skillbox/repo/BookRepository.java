package edu.mod5_skillbox.repo;

import edu.mod5_skillbox.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByNameAndAuthor(String name, String author);
}
