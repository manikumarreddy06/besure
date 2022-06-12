package com.bsure.FAQ;

import androidx.annotation.NonNull;

public class FAQ_items { // model class
    private String  question, answer;
    private boolean expandable;

    public FAQ_items(String question, String answer) {
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
