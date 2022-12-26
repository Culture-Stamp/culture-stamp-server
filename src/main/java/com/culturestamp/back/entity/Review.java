package com.culturestamp.back.entity;

import com.culturestamp.back.controller.request.ReviewEditorRequest;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@ToString(exclude = "categoryId,userId")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode( of = {"id","categoryId","userId"} )
public class Review extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    private String location;
    private String companion;
    private int rating;
    private int price;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;
    @Column(name = "performed_date", nullable = false)
    private LocalDateTime performedDate;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Builder
    public Review(Long id, Category category, User user, String title, LocalDateTime performedDate,
                  String location, String companion, int rating, String content, int price) {
        this.id = id;
        this.category = category;
        this.user = user;
        this.title = title;
        this.performedDate = performedDate;
        this.location = location;
        this.companion = companion;
        this.rating = rating;
        this.content = content;
        this.price = price;
    }

    public void edit(ReviewEditorRequest reviewEditorRequest) {
        this.category = reviewEditorRequest.getCategory();
        this.title = reviewEditorRequest.getTitle();
        this.performedDate = reviewEditorRequest.getPerformedDate();
        this.location = reviewEditorRequest.getLocation();
        this.companion = reviewEditorRequest.getCompanion();
        this.rating = reviewEditorRequest.getRating();
        this.content = reviewEditorRequest.getContent();
        this.price = reviewEditorRequest.getPrice();
    }

    public ReviewEditorRequest.ReviewEditorRequestBuilder toEditor(){
        return ReviewEditorRequest.builder()
                .category(category)
                .title(title)
                .performedDate(performedDate)
                .location(location)
                .companion(companion)
                .rating(rating)
                .content(content)
                .price(price);
    }
}