package io.bada.bookmaker.domain;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    
    @Query("""
            select new io.bada.bookmaker.domain.BookmarkDto(b.id, b.title, b.url, b.createAt) from Bookmark b
            """)
    Page<BookmarkDto> findByBookmarks(Pageable pageable);

    @Query("""
        select new io.bada.bookmaker.domain.BookmarkDto(b.id, b.title, b.url, b.createAt) from Bookmark b
        where lower(b.title) like lower(concat('%', :query, '%'))
        """)
    Page<BookmarkDto> searchBookmarks(String query, Pageable pageable);

    //JPA Query Method(searchBookmarks 와 동일한 결과)
    Page<BookmarkDto> findByTitleContainsIgnoreCase(String query, Pageable pageable);
}
