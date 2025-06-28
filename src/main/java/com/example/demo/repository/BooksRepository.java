package com.example.demo.repository;

import com.example.demo.model.Author;
import com.example.demo.model.Book;
import org.hibernate.annotations.processing.SQL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BooksRepository extends JpaRepository<Book, Long> {
    @Query("SELECT DISTINCT b.authors FROM Book b")
    List<Author> getAllAuthor();

    @Query("SELECT DISTINCT a FROM Book b JOIN b.authors a WHERE (a.deathYear > :year OR a.deathYear IS NULL) AND a.birthYear <= :year")
    List<Author> getAuthorsByYearOfLife(@Param("year") int year);

    @Query("SELECT DISTINCT b FROM Book b JOIN b.languages l WHERE LOWER(l.language) = LOWER(:language)")
    List<Book> findByLanguageIgnoreCase(@Param("language") String language);
}