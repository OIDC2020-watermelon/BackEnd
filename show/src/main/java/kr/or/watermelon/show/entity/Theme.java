package kr.or.watermelon.show.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Theme {
    @Id @GeneratedValue
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    private ThemeType themeType;

    @ManyToOne
    private Product product;

}
