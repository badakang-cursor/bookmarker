package io.bada.bookmaker.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.bada.bookmaker.domain.BookmarkDto;
import io.bada.bookmaker.domain.BookmarkService;
import io.bada.bookmaker.domain.BookmarksDto;
import io.bada.bookmaker.domain.CreateBookmarkRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/bookmarks")
@RequiredArgsConstructor
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @GetMapping
    public BookmarksDto getBookmark(
        @RequestParam(name="page", defaultValue = "1") Integer page
        , @RequestParam(name="query", defaultValue = "") String query
    
    ) {
        if(query == null || query.trim().length() == 0){
            return bookmarkService.getBookmarks(page);
        }
        return bookmarkService.searchBookmarks(query, page);
    }
    
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)  // CODE:201
    public BookmarkDto createBookmark(@RequestBody @Valid CreateBookmarkRequest request) {
        return bookmarkService.createBookmark(request);
    }
}
