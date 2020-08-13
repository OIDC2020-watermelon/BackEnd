package kr.or.watermelon.show.dto;

public enum TrafficTypeDto {
    RESERVATION,
    ACCESS;

    @Override
    public String toString() {
        switch (this.ordinal()) {
            case 0:
                return "예매";
            default:
                return "접근";
        }
    }
}
