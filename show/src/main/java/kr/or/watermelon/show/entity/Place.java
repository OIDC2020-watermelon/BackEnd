package kr.or.watermelon.show.entity;

import kr.or.watermelon.show.config.UrlLength;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
@Data
public class Place {
    @Id @GeneratedValue
    private Long id;

    private String name;

    private String address;

    @Length(max=20)
    private String telephone;

    @Length(max= UrlLength.WEB_PAGE)
    private String homepage;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private String description;

    @Length(max= UrlLength.IMG)
    private String imgUrl;
}
