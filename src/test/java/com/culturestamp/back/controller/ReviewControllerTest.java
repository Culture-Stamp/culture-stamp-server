// package com.culturestamp.back.controller;
//
// import com.culturestamp.back.controller.request.ReviewEditorRequest;
// import com.culturestamp.back.controller.request.ReviewRequest;
// import com.culturestamp.back.entity.Category;
// import com.culturestamp.back.entity.Review;
// import com.culturestamp.back.entity.Role;
// import com.culturestamp.back.entity.User;
// import com.culturestamp.back.repository.ReviewRepository;
// import com.fasterxml.jackson.databind.ObjectMapper;
// import org.hamcrest.Matchers;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.junit.jupiter.MockitoExtension;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.data.domain.PageRequest;
// import org.springframework.data.domain.Pageable;
// import org.springframework.data.domain.Sort;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
// import java.text.ParseException;
// import java.text.SimpleDateFormat;
// import java.time.LocalDateTime;
// import java.util.List;
// import java.util.stream.Collectors;
// import java.util.stream.IntStream;
//
// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.springframework.http.MediaType.APPLICATION_JSON;
// import static org.hamcrest.Matchers.is;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
// import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
// @AutoConfigureMockMvc
// @SpringBootTest
// @ExtendWith(MockitoExtension.class)
// public class ReviewControllerTest {
//     @Autowired
//     private ReviewRepository repository;
//
//     @Autowired
//     private ObjectMapper objectMapper;
//
//     @Autowired
//     private MockMvc mockMvc;
//
//     private User user;
//     private Review review;
//     private Category category;
//
//     @BeforeEach
//     @DisplayName("user, category, review ????????? ??????")
//     void setup() throws ParseException {
//         user = User.builder()
//                 .userId(1L)
//                 .nickname("??????")
//                 .loginId("testID")
//                 .email("?????????@naver.com")
//                 .password("wtefsfd")
//                 .role(Role.USER)
//                 .lastLoginAt(new SimpleDateFormat("yyyyMMdd").parse("20221028"))
//                 .failCount(0)
//                 .build();
//
//         category = new Category(1L,"Movie",1L,user);
//
//         review = Review.builder()
//                 .category(category)
//                 .price(500)
//                 .title("???????????????")
//                 .content("?????? ??? ?????? ?????? ????????? ??? ?????????.")
//                 .companion("")
//                 .location("")
//                 .rating(5)
//                 .performedDate(LocalDateTime.now())
//                 .user(user)
//                 .build();
//     }
//
//     @Test
//     void test??????_??????_??????_????????????() throws Exception {
//         // given
//         ReviewRequest request = ReviewRequest.builder()
//                 .category(review.getCategory())
//                 .user(review.getUser())
//                 .title(review.getTitle())
//                 .performedDate(review.getPerformedDate())
//                 .location(review.getLocation())
//                 .companion(review.getLocation())
//                 .rating(review.getRating())
//                 .content(review.getContent())
//                 .price(review.getPrice())
//                 .build();
//
//         String json = objectMapper.writeValueAsString(request);
//
//         // when
//         mockMvc.perform(MockMvcRequestBuilders.post("/review")
//                         .contentType(APPLICATION_JSON)
//                         .content(json)
//                 )
//                 .andExpect(status().isOk())
//                 .andDo(print());
//
//         // then
//         // assertEquals(5, repository.count());
//
//         Review actual = repository.findAll().get(0);
//         assertEquals(1L, actual.getId());
//     }
//
//     @Test
//     @DisplayName("??? 10????????? ???????????? 1 ????????? ??????")
//     void test??????_??????_??????() throws Exception {
//         /*
//             // given
//             List<Review> requestReviews = (List<Review>) IntStream.range(1,10)
//                                                                     .mapToObj( i -> Review.builder()
//                                                                             .title("?????? = "+ i )
//                                                                             .content("?????? = " + i )
//                                                                             .category(review.getCategory())
//                                                                             .user(review.getUser())
//                                                                             .performedDate(review.getPerformedDate())
//                                                                             .build()
//                                                                     ).collect( Collectors.toList() );
//
//             repository.saveAll(requestReviews);
//         */
//
//         // expected
//         mockMvc.perform( MockMvcRequestBuilders.get("/review?page=0&sort=id")
//                                                 .contentType(APPLICATION_JSON)
//                 )
//                 .andExpect( status().isOk() )
//                 /*.andExpect( jsonPath("$[0].reviewId").value(1 ) )
//                 .andExpect( jsonPath("$[0].title").value("?????? = 9" ) )
//                 .andExpect( jsonPath("$[0].content").value("?????? = 9" ) )*/
//                 .andDo( print() );
//     }
//
//     @Test
//     void test??????_??????() throws Exception {
//         // given
//         repository.save(review);
//
//         ReviewEditorRequest reviewEditorRequest = ReviewEditorRequest.builder()
//                                                                     .category(category)
//                                                                     .price(1000)
//                                                                     .title("?????? ????????? ?????? ??????")
//                                                                     .content("?????? ????????? ?????? ??????")
//                                                                     .companion("??????")
//                                                                     .location("?????? CGV")
//                                                                     .rating(3)
//                                                                     .performedDate(LocalDateTime.now())
//                                                                     .build();
//
//         String json = objectMapper.writeValueAsString(reviewEditorRequest);
//
//         // expected
//         mockMvc.perform( MockMvcRequestBuilders.patch("/review/{id}", review.getId() )
//                                                 .contentType(APPLICATION_JSON)
//                                                 .content(json)
//                 )
//                 .andExpect( status().isOk() )
//                 .andDo( print() );
//     }
//
//     @Test
//     void test??????_??????() throws Exception {
//         // given
//         repository.save(review);
//
//         // expected
//         mockMvc.perform( MockMvcRequestBuilders.delete("/review/{id}", review.getId() )
//                                                 .contentType(APPLICATION_JSON)
//                 )
//                 .andExpect( status().isOk() )
//                 .andDo( print() );
//     }
// }
