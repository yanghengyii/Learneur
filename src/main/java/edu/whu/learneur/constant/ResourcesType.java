package edu.whu.learneur.constant;

public enum ResourcesType {
    BOOK(1),
    LESSON(2),
    PROJECT(3);

    private int type;
    ResourcesType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
