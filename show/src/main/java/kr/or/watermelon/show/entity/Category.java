package kr.or.watermelon.show.entity;

import lombok.Getter;

@Getter
public enum Category {
    CONCERT,
    PLAY,
    CLASSIC_DANCE,
    EXHIBITION_EVENT;

    @Override
    public String toString() {
        switch (this.ordinal()) {
            case 0:
                return "콘서트";
            case 1:
                return "공연";
            case 2:
                return "클래식/댄스";
            default:
                return "전시/이벤트";
        }
    }
}
