package kr.or.watermelon.show.entity;

import lombok.Getter;

@Getter
public enum Category {

    CONCERT("콘서트"),
    PLAY("연극"),
    CLASSIC_DANCE("클래식/무용"),
    EXHIBITION_EVENT("전시/행사");

    private final String type;

    Category(String type) {
        this.type=type;
    }
}
