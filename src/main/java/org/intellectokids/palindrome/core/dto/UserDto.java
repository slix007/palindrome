package org.intellectokids.palindrome.core.dto;

public class UserDto {

    public UserDto(String name, Integer score) {
        this.name = name;
        this.score = score;
    }

    private String name;
    private Integer score;

    public String getName() {
        return name;
    }

    public Integer getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}
