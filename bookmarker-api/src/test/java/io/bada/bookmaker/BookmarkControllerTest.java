package io.bada.bookmaker;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.is;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import io.bada.bookmaker.domain.Bookmark;
import io.bada.bookmaker.domain.BookmarkRepository;

// Test 를 위해 무작위 포트 사용
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)

@AutoConfigureMockMvc

@TestPropertySource(properties = {
    "spring.datasource.url=jdbc:tc:postgresql:17-alpine:///demo"
})
public class BookmarkControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private BookmarkRepository bookmarkRepository;

    @BeforeEach
    void setUp(){
        bookmarkRepository.deleteAllInBatch();

        List<Bookmark> bookmarkList = new ArrayList<>();

        bookmarkList.add(new Bookmark(null, "1구글 검색", "https://www.google.com", Instant.now()));
        bookmarkList.add(new Bookmark(null, "1네이버 메인", "https://www.naver.com", Instant.now()));
        bookmarkList.add(new Bookmark(null, "1유튜브", "https://www.youtube.com", Instant.now()));
        bookmarkList.add(new Bookmark(null, "1깃허브", "https://github.com", Instant.now()));
        bookmarkList.add(new Bookmark(null, "1스택오버플로우", "https://stackoverflow.com", Instant.now()));
        bookmarkList.add(new Bookmark(null, "1카카오", "https://www.kakao.com", Instant.now()));
        bookmarkList.add(new Bookmark(null, "1쿠팡 쇼핑", "https://www.coupang.com", Instant.now()));
        bookmarkList.add(new Bookmark(null, "1배달의민족", "https://www.baemin.com", Instant.now()));
        bookmarkList.add(new Bookmark(null, "1토스", "https://toss.im", Instant.now()));
        bookmarkList.add(new Bookmark(null, "1인프런 강의", "https://www.inflearn.com", Instant.now()));
        bookmarkList.add(new Bookmark(null, "1생활코딩", "https://opentutorials.org", Instant.now()));
        bookmarkList.add(new Bookmark(null, "1리디북스", "https://ridibooks.com", Instant.now()));
        bookmarkList.add(new Bookmark(null, "1넷플릭스", "https://www.netflix.com", Instant.now()));
        bookmarkList.add(new Bookmark(null, "1디스코드", "https://discord.com", Instant.now()));
        bookmarkList.add(new Bookmark(null, "1줌 회의", "https://zoom.us", Instant.now()));

        bookmarkRepository.saveAll(bookmarkList);
    }

    //@Test
    void shouldBookmarks() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/api/bookmarks"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.totalElements", CoreMatchers.equalTo(15)))
            .andExpect(jsonPath("$.totalPage", CoreMatchers.equalTo(2)))
            .andExpect(jsonPath("$.currentPage", CoreMatchers.equalTo(1)))
            .andExpect(jsonPath("$.isFirst", CoreMatchers.equalTo(true)))
            .andExpect(jsonPath("$.isLast", CoreMatchers.equalTo(false)))
            .andExpect(jsonPath("$.hasNext", CoreMatchers.equalTo(true)))
            .andExpect(jsonPath("$.hasPrevious", CoreMatchers.equalTo(false)))
            ;
    }

    //@ParameterizedTest
    @CsvSource({
        "1,15,2,1,true,false,true,false",
        "2,15,2,2,false,true,false,true"
    })
    void shouldBookmarks_param(int pageNo, int totalElements, int totalPage, int currentPage, boolean isFirst, boolean isLast, boolean hasNext, boolean hasPrevious) throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/api/bookmarks?page="+ pageNo))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.totalElements", CoreMatchers.equalTo(totalElements)))
            .andExpect(jsonPath("$.totalPage", CoreMatchers.equalTo(totalPage)))
            .andExpect(jsonPath("$.currentPage", CoreMatchers.equalTo(currentPage)))
            .andExpect(jsonPath("$.isFirst", CoreMatchers.equalTo(isFirst)))
            .andExpect(jsonPath("$.isLast", CoreMatchers.equalTo(isLast)))
            .andExpect(jsonPath("$.hasNext", CoreMatchers.equalTo(hasNext)))
            .andExpect(jsonPath("$.hasPrevious", CoreMatchers.equalTo(hasPrevious)))
            ;
    }

    //@Test
    void createBookmarkErrorCheck() throws Exception{
        MvcResult result = this.mvc.perform(
            MockMvcRequestBuilders.post("/api/bookmarks")
                                    .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                                    .content("""
                                            {
                                                "url":"http://daum.net"
                                            }
                                            """)
                                
                                )
                                .andExpect(status().is4xxClientError())
                                .andExpect(jsonPath("$.field", is("title")))
                                .andExpect(jsonPath("$.message", is("제목은 필수 입력 값입니다.")))
                                .andExpect(jsonPath("$.status", is(400)))
                                .andReturn();
        String contentType = result.getResponse().getContentType();
        String responseBody = result.getResponse().getContentAsString();

        System.out.println("반환된 ContentTYpe : "+ contentType);
        System.out.println("반환된 내용 : "+ responseBody);
    }
}
