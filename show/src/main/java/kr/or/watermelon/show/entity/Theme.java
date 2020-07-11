package kr.or.watermelon.show.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class Theme {
    @Id @GeneratedValue
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    private ThemeType themeType;

    @ManyToOne
    private Product product;

}
