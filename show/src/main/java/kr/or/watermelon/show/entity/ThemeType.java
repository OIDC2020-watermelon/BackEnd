package kr.or.watermelon.show.entity;


import lombok.Getter;

@Getter
public enum ThemeType {

    HOT_ISSUE,
    NEW,
    COMMING_SOON;

    @Override
    public String toString() {
        switch (this.ordinal()) {
            case 0:
                return "핫이슈";
            case 1:
                return "신규";
            default:
                return "커밍순";
        }
    }
}
