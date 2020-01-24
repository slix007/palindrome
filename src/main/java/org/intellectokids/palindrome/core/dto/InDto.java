package org.intellectokids.palindrome.core.dto;

public class InDto {

    public InDto(String userName, String phrase) {
        this.userName = userName;
        this.phrase = phrase;
    }

    private String userName;
    private String phrase;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }
}
