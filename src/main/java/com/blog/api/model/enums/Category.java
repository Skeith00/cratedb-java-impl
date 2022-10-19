package com.blog.api.model.enums;

public enum Category {
    FOOD,
    TRAVEL;

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
