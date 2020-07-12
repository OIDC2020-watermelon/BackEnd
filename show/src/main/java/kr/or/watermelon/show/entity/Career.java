package kr.or.watermelon.show.entity;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Career {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime date;

    @ManyToOne
    private Artist artist;

    @Lob @Type(type = "org.hibernate.type.TextType")
    private String description;
}
