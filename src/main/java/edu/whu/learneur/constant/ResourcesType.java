package edu.whu.learneur.constant;

public enum ResourcesType {
    LESSON(1),
    BOOK(2),
    PROJECT(3),
    TUTORIAL(4),
    VIDEO(5);


    private int type;
    ResourcesType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
