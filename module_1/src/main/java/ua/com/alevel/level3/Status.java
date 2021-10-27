package ua.com.alevel.level3;

public enum Status {
    NONE,   //1
    BORN,   //2
    LIVE,   //1
    DIED;   //2

    public Status step1(int around){
        return switch (this) {
            case NONE -> (around == 3) ? BORN : NONE;
            case LIVE -> (around <= 1 || around >= 4) ? DIED : LIVE;
            default -> this;
        };
    }

    public Status step2(){
        return switch (this) {
            case BORN -> LIVE;
            case DIED -> NONE;
            default -> this;
        };
    }

    public boolean isCell(){
        return this == LIVE || this == DIED;
    }
}
