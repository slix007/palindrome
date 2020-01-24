package org.intellectokids.palindrome.core.model;

import java.util.ArrayList;
import java.util.List;

public class User {

    public User(String name) {
        this.name = name;
        this.score = 0;
    }

    // unique
    private String name;

    private Integer score;
    private List<String> usedPhrases = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public List<String> getUsedPhrases() {
        return usedPhrases;
    }

    public void setUsedPhrases(List<String> usedPhrases) {
        this.usedPhrases = usedPhrases;
    }
}
