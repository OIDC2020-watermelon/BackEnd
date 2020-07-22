package kr.or.watermelon.show.entity;

public enum Occupation {
    SINGER,
    ACTOR;

    @Override
    public String toString() {
        switch (this.ordinal()) {
            case 0:
                return "가수";
            default:
                return "배우";
        }
    }
}
