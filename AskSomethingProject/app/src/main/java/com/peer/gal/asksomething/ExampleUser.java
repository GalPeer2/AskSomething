package com.peer.gal.asksomething;

import android.support.annotation.NonNull;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ExampleUser {

    public ExampleUser()
    {

    }
    public ArrayList<Question> getListQuestions() {

        ArrayList<Question> theQuestionsList = new ArrayList<Question>();


        Question question = new Question("what do you like to eat?", "hamburger", "pizza", "pasta", "shnitzel");
        String [] arr1 ={"gal","nave","mani","ido"};
        String [] arr2 ={"yaniv","areil","aviv","yoav"};
        String [] arr3 ={"yuval","guy"};
        String [] arr4 ={"mikel","haron","supermen","sason","mika","adam"};

        question.setVotersForAns1(fromArrToList(arr1));
        question.setVotersForAns1(fromArrToList(arr2));
        question.setVotersForAns1(fromArrToList(arr3));
        question.setVotersForAns1(fromArrToList(arr4));

        theQuestionsList.add(question);

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

         question = new Question("what is your hobbie?", "eating", "sport", "sleeping", "studying");
         String[] brr1 ={"gal","daniel","mani","ido","tom"};
         String[] brr2 ={"yaniv","areil","yoav"};
        String[] brr3 ={"yuval","guy","haron","supermen","sason","mika","eyal"};
        String[] brr4 ={"mikel"};

        question.setVotersForAns1(fromArrToList(brr1));
        question.setVotersForAns1(fromArrToList(brr2));
        question.setVotersForAns1(fromArrToList(brr3));
        question.setVotersForAns1(fromArrToList(brr4));

        theQuestionsList.add(question);
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        question = new Question("how much time do yo spend on computer a day?", "hour or less", "2 hours", "3 hours", "4 hours or more");
        String[] crr1 ={"gal"};
        String[] crr2 ={"yaniv","areil","yoav","daniel","mani","ido","tom"};
        String[] crr3 ={"haron","supermen","sason","mika","eyal"};
        String[] crr4 ={"mikel","yuval","guy"};

        question.setVotersForAns1(fromArrToList(crr1));
        question.setVotersForAns1(fromArrToList(crr2));
        question.setVotersForAns1(fromArrToList(crr3));
        question.setVotersForAns1(fromArrToList(crr4));

        theQuestionsList.add(question);

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        question = new Question("what do you prefer? movie or book", "movie", "book", "", "");
        String[] drr1 ={"mikel","yuval","yaniv","areil","guy","haron","supermen","gal","sason","mika","eyal"};
        String[] drr2 ={"yoav","daniel","mani","ido","tom"};
        String[] drr3 ={};
        String[] drr4 ={};

        question.setVotersForAns1(fromArrToList(drr1));
        question.setVotersForAns1(fromArrToList(drr2));
       // question.setVotersForAns1(fromArrToList(drr3));
       // question.setVotersForAns1(fromArrToList(drr4));

        theQuestionsList.add(question);

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        question = new Question("which store do you like the most?", "Fox", "American Eagle", "renuar", "H&M");
        String[] err1 ={"gal","yaniv","areil"};
        String[] err2 ={"yoav","mani","ido","tom"};
        String[] err3 ={"sason","mika","eyal"};
        String[] err4 ={"mikel","haron","supermen","yuval","guy","daniel"};

        question.setVotersForAns1(fromArrToList(err1));
        question.setVotersForAns1(fromArrToList(err2));
        question.setVotersForAns1(fromArrToList(err3));
        question.setVotersForAns1(fromArrToList(err4));

        theQuestionsList.add(question);
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~



        return theQuestionsList;
    }




    public ArrayList<String> fromArrToList(String[]a)
    {
        ArrayList <String> arr =new ArrayList<String>();
        for (String s: a)
            arr.add(s);
        return arr;
    }
}
