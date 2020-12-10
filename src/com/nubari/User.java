package com.nubari;

import java.util.ArrayList;
import java.util.Collection;

public class User {
    private String firstName;
    private String lastName;
    private String userPassword;
    private ArrayList<Diary> diaries = new ArrayList<>();
    private Diary activeDiary;
    private int numberOfDiaries = 0;

    public User(String firstName, String lastName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userPassword = password;
        numberOfDiaries++;
        String diaryName;
        Diary diary;
        if (numberOfDiaries != 0) {
            diaryName = firstName + numberOfDiaries + "'s diary";
            diary = new Diary(diaryName, password, 10);
        } else {
            diary = new Diary(password, 10);
        }
        diaries.add(diary);
        activeDiary = diary;
    }

    public boolean authenticate(String password) {
        if (this.userPassword.equals(password)) {
            return true;
        }
        return false;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public Diary getActiveDiary() {
        return activeDiary;
    }

    public ArrayList<Diary> listDiaries() {
//        for (Diary diary : diaries){
//            System.out.println(diary);
//        }
        return diaries;

    }

    public void addDiary(int diaryLimit) {
        Diary diary = new Diary(userPassword, diaryLimit);
        diaries.add(diary);
        activeDiary = diary;

    }

    public void addDiary(String diaryName) {
        Diary diary = new Diary(diaryName, userPassword, 50);
        diaries.add(diary);
        activeDiary = diary;
    }

    public void addDiary(int diaryLimit, String diaryName) {
        Diary diary = new Diary(diaryName, userPassword, diaryLimit);
        diaries.add(diary);
        activeDiary = diary;
    }

    public void addDiary(int diaryLimit, String diaryName, String diaryPassword) {
        Diary diary = new Diary(diaryName, diaryPassword, diaryLimit);
        diaries.add(diary);
        activeDiary = diary;
    }

    public void removeDiary(String diaryName) {
        int diaryPosition = 0;
        boolean diaryToRemoveFound = false;
        int indexOfRemovedDiary = 0;
        Diary diaryRemoved = null;
        for (Diary diary : diaries) {
            if (diary.getDiaryName().toLowerCase().equals(diaryName.toLowerCase())) {
                diaryRemoved = diary;
                diaries.remove(diary);
                diaryToRemoveFound = true;
                indexOfRemovedDiary = diaryPosition;
                break;
            }
            diaryPosition++;
        }
        if (diaryToRemoveFound && activeDiary.equals(diaryRemoved)) {
            if (indexOfRemovedDiary > 0) {
                activeDiary = diaries.get(indexOfRemovedDiary - 1);
            } else if (indexOfRemovedDiary == 0) {
                activeDiary = null;
            }
        }

    }

}
