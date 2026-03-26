package io.bada.bookmaker.domain;

import org.springframework.stereotype.Component;

@Component
public class BookmarkMapper {
    public BookmarkDto toDto(Bookmark bookmark){
        // BookmarkDto dto = new BookmarkDto();
        // dto.setId(bookmark.getId());
        // dto.setTitle(bookmark.getTitle());
        // dto.setUrl(bookmark.getUrl());
        // dto.setCreateAt(bookmark.getCreateAt());

        // BookmarkDto의 기본생성자(@NoArgsConstructor) 만들지 않을 경우
        BookmarkDto dto = new BookmarkDto(bookmark.getId(), bookmark.getTitle(), bookmark.getUrl(), bookmark.getCreateAt());
        return dto;
    }
}
