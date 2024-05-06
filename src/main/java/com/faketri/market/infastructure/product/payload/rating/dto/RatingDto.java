package com.faketri.market.infastructure.product.payload.rating.dto;

public class RatingDto {

    private String description;
    private Short grade;

    public RatingDto() {
    }

    public RatingDto(String description, Short grade) {
        this.description = description;
        this.grade = grade;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Short getGrade() {
        return grade;
    }

    public void setGrade(Short grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "RatingDto{" +
                "description='" + description + '\'' +
                ", grade=" + grade +
                '}';
    }
}
