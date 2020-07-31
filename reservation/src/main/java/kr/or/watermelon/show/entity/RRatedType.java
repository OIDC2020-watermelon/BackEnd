package kr.or.watermelon.show.entity;

import lombok.Getter;

@Getter
public enum RRatedType {
    INFANT,
    CHILD,
    TEEN,
    ADULT;

    @Override
    public String toString() {
        switch (this.ordinal()) {
            case 0:
                return "6";
            case 1:
                return "12";
            case 2:
                return "15";
            default:
                return "19";
        }
    }
}
