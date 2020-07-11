package kr.or.watermelon.show.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Entity
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class Promotion {
    @Id @GeneratedValue
    private Long id;

    @OneToOne
    private Product product;

    @Length(max=8200)
    private String promotionImgUrl;

}
