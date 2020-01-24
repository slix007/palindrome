package org.intellectokids.palindrome.core.dto;

public class OutDto {
    private Boolean result;
    private Integer addedScore;
    private Integer sumScore;
    private String reason;

    public OutDto(Boolean result, Integer addedScore, Integer sumScore, String reason) {
        this.result = result;
        this.addedScore = addedScore;
        this.sumScore = sumScore;
        this.reason = reason;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public Integer getAddedScore() {
        return addedScore;
    }

    public void setAddedScore(Integer addedScore) {
        this.addedScore = addedScore;
    }

    public Integer getSumScore() {
        return sumScore;
    }

    public void setSumScore(Integer sumScore) {
        this.sumScore = sumScore;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
