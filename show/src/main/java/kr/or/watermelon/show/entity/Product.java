package kr.or.watermelon.show.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Enumerated(EnumType.ORDINAL)
    private Category category;

    @ManyToOne
    private Place place;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private String description;

    @Length(max=8200)
    private String imgUrl;

    @OneToMany(mappedBy = "product")
    private List<Theme> theme;

    @Length(max=8200)
    private String thumbnailImgUrl;

    @CreatedDate
    private LocalDateTime createdDateTime;

    @LastModifiedDate
    private LocalDateTime modifiedDateTime;

    private LocalDateTime releaseStartTime;

    private LocalDateTime releaseEndTime;

    private LocalDateTime startShowTime;

    private LocalDateTime endShowTime;

}
