package kr.or.watermelon.show.entity;


import lombok.Getter;

@Getter
public enum ThemeType {

    HOT_ISSUE("핫이슈"),
    NEW("신규"),
    COMMING_SOON("커밍순");

    private final String themeType;

    ThemeType(String themeType) {
        this.themeType = themeType;
    }
}
