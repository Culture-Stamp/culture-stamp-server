package com.culturestamp.back.controller.request;

import com.culturestamp.back.entity.Category;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PUBLIC) // 테스트를 위해 Public
public class ReviewEditorRequest {
    private Category category;
    private String title;
    private LocalDateTime performedDate;
    private String location;
    private String companion;
    private int rating;
    private String content;
    private int price;
}
