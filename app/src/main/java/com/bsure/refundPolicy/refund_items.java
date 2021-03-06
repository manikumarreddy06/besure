package com.bsure.refundPolicy;

import androidx.annotation.NonNull;

public class refund_items {
    private String  question, answer;
    private boolean expandable;

    public refund_items(String question, String answer) {
        this.question = question;
        this.answer = answer;
        this.expandable = false;
    }

    public boolean isExpandable() {
        return expandable; }

    public void setExpandable(boolean expandable) {  this.expandable = expandable;}

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }


    @NonNull
    @Override
    public String toString() {
        return "FAQ_Qs_Ans{" +
                "question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}
