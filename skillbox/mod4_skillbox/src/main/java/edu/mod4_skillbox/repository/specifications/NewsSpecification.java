package edu.mod4_skillbox.repository.specifications;

import edu.mod4_skillbox.model.News;
import org.springframework.data.jpa.domain.Specification;

public class NewsSpecification {

    public static Specification<News> hasCategory(Long categoryId) {
        return (root, query, criteriaBuilder) -> categoryId != null
                ? criteriaBuilder.equal(root.get("category").get("id"), categoryId) : criteriaBuilder.conjunction();
    }

    public static Specification<News> hasAuthor(Long authorId) {
        return (root, query, criteriaBuilder) -> authorId != null ?
                criteriaBuilder.equal(root.get("author").get("id"), authorId) : criteriaBuilder.conjunction();
    }

    public static Specification<News> hasId(Long id) {
        return ((root, query, criteriaBuilder) -> id != null ? criteriaBuilder.equal(root.get("id"), id) : criteriaBuilder.conjunction());
    }
}

