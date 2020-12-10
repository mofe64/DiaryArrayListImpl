package com.nubari;

import java.util.ArrayList;

public class Diary {
    private int diaryNumber = 0;
    private String diaryName;
    private ArrayList<Entry> entries = new ArrayList<>();
    private String password;
    int diaryLimit;
    int numberOfEntries = 0;

    public Diary(String password, int diaryLimit) {
        this.diaryName = "Diary Number " + (diaryNumber + 1);
        this.password = password;
        this.diaryLimit = diaryLimit;
        diaryNumber++;
    }

    public Diary(String diaryName, String password, int diaryLimit) {
        this.diaryName = diaryName;
        this.password = password;
        this.diaryLimit = diaryLimit;
    }

    public String getDiaryName() {
        return diaryName;
    }

    public String getPassword() {
        return password;
    }

    public int getDiaryLimit() {
        return diaryLimit;
    }

    public ArrayList<Entry> getEntries() {
        return entries;
    }

    public void addEntry(Entry entry) {
        if (entries.size() < diaryLimit) {
            System.out.println("Successfully added " + entry.getTitle());
            entries.add(entry);
            numberOfEntries++;
        } else {
            System.out.println("Diary full");
        }
    }

    public Entry getEntry(int index) {
        if (index > -1 && index < entries.size()) {
            return entries.get(index);
        }
        System.out.println("Invalid Index range");
        return null;
    }

    public Entry getEntry(String title) {
        for (Entry entry : entries) {
            if (entry.getTitle().equals(title)) {
                return entry;
            }
        }
        System.out.println("Entry not found");
        return null;
    }

    public Entry getEntry(String title, boolean ignoreCase) {
        for (Entry entry : entries) {
//            System.out.println("Entry title is " + entry.getTitle());
            if (entry.getTitle().toLowerCase().equals(title.toLowerCase())) {
                return entry;
            }
        }
        System.out.println("No entry found with that title");
        return null;
    }

    public void deleteEntry(int index) {
        if (index > -1 && index < entries.size()) {
            entries.remove(index);
            return;
        }
        System.out.println("Invalid index ");
    }

    public void deleteEntry(String title) {
        for (Entry entry : entries) {
            if (entry.getTitle().equals(title)) {
                entries.remove(entry);
                return;
            }
        }
        System.out.println("No entry found with that title");
    }

    public ArrayList<Entry> entryKeywordSearch(String keyword) {
        ArrayList<Entry> matchingEntries = new ArrayList<>();
        for (Entry entry : entries) {
//            System.out.println("Entry title is " + entry.getTitle());
            if (entry.getEntry().contains(keyword)) {
                matchingEntries.add(entry);
            }
        }
        return matchingEntries;
    }

    public ArrayList<Entry> entryKeywordSearch(String keyword, boolean ignoreCase) {
        ArrayList<Entry> matchingEntries = new ArrayList<>();
        for (Entry entry : entries) {
            if (entry.getEntry().toLowerCase().contains(keyword.toLowerCase())) {
                matchingEntries.add(entry);
            }
        }
        return matchingEntries;
    }

    @Override
    public String toString() {
        return "Diary: " + getDiaryName() + "\n" +
                "Limit is " + diaryLimit + "\n" +
                "Current capacity is " + numberOfEntries + "/" + diaryLimit;
    }
}
