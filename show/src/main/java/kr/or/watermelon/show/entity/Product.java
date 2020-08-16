package kr.or.watermelon.show.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private String description;

    private Integer runningTime;

    @Length(max = 8200)
    private String imgUrl = "http://ticketimage.interpark.com/Play/image/large/19/19018229_p.gif";

    @Length(max = 8200)
    private String thumbnailImgUrl = "http://ticketimage.interpark.com/TCMS3.0/PL/TOP_2/2001/200120034842_15012361.gif";

    @CreationTimestamp
    private LocalDateTime createdDateTime;

    @UpdateTimestamp
    private LocalDateTime modifiedDateTime;

    private LocalDateTime releaseStartTime;

    private LocalDateTime releaseEndTime;

    @Enumerated(EnumType.ORDINAL)
    private Category category;

    @Enumerated(EnumType.ORDINAL)
    private RRatedType rRated;

    @ManyToOne
    private Place place;

    @ManyToMany(mappedBy = "products")
    @Builder.Default
    private List<Artist> artists = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    @Builder.Default
    private List<Comment> comments = new ArrayList<>();

    private Integer pod;

    public List<String> getArtistNames() {
        return artists.stream()
                .map(a -> a.getName())
                .limit(3)
                .collect(Collectors.toList());
    }

    public static class ProductBuilder implements CustomBuilder {
    }
}
