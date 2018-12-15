package com.peer.gal.asksomething;

import java.util.List;

/**
 * Created by Gal on 12/15/2018.
 */

class Question {
    String que,ans1,ans2,ans3,ans4;
    List<String> votersForAns1,votersForAns2,votersForAns3,votersForAns4;

    public String getQue() {
        return que;
    }

    public void setQue(String que) {
        this.que = que;
    }

    public String getAns1() {
        return ans1;
    }

    public void setAns1(String ans1) {
        this.ans1 = ans1;
    }

    public String getAns2() {
        return ans2;
    }

    public void setAns2(String ans2) {
        this.ans2 = ans2;
    }

    public String getAns3() {
        return ans3;
    }

    public void setAns3(String ans3) {
        this.ans3 = ans3;
    }

    public String getAns4() {
        return ans4;
    }

    public void setAns4(String ans4) {
        this.ans4 = ans4;
    }

    public List<String> getVotersForAns1() {
        return votersForAns1;
    }

    public void setVotersForAns1(List<String> votersForAns1) {
        this.votersForAns1 = votersForAns1;
    }

    public List<String> getVotersForAns2() {
        return votersForAns2;
    }

    public void setVotersForAns2(List<String> votersForAns2) {
        this.votersForAns2 = votersForAns2;
    }

    public List<String> getVotersForAns3() {
        return votersForAns3;
    }

    public void setVotersForAns3(List<String> votersForAns3) {
        this.votersForAns3 = votersForAns3;
    }

    public List<String> getVotersForAns4() {
        return votersForAns4;
    }

    public void setVotersForAns4(List<String> votersForAns4) {
        this.votersForAns4 = votersForAns4;
    }

    public Question(String que, String ans1, String ans2, String ans3, String ans4, List<String> votersForAns1, List<String> votersForAns2, List<String> votersForAns3, List<String> votersForAns4) {
        this.que = que;
        this.ans1 = ans1;
        this.ans2 = ans2;
        this.ans3 = ans3;
        this.ans4 = ans4;
        this.votersForAns1 = votersForAns1;
        this.votersForAns2 = votersForAns2;
        this.votersForAns3 = votersForAns3;
        this.votersForAns4 = votersForAns4;
    }
}
