package kr.or.watermelon.show.entity;

import kr.or.watermelon.show.config.UrlLength;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Length(max = 50)
    private String name;

    @Enumerated(EnumType.ORDINAL)
    private Occupation occupation;

    private LocalDateTime birthDate;

    private LocalDateTime debutDate;

    @ManyToMany
    @JoinTable(name = "product_artist", joinColumns = @JoinColumn(name = "artist_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    @Builder.Default
    private List<Product> products = new ArrayList<>();

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private String description;

    private Integer height;

    private Integer weight;

    @Length(max = UrlLength.IMG)
    private String thumbnailImgUrl;

    @Length(max = UrlLength.IMG)
    private String imgUrl;

    @Length(max = UrlLength.WEB_PAGE)
    private String instagramUrl;

    @Length(max = UrlLength.WEB_PAGE)
    private String twitterUrl;

    @Length(max = UrlLength.WEB_PAGE)
    private String facebookUrl;

    @OneToMany(mappedBy = "artist")
    @Builder.Default
    private List<Career> careers = new ArrayList<>();

    public List<String> getProductTitles() {
        return products.stream()
                .map(p -> p.getTitle())
                .collect(Collectors.toList());
    }
}
