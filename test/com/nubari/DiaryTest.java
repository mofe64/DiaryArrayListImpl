package com.nubari;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DiaryTest {
    Diary diary;

    @BeforeEach
    void setUp() {
        diary = new Diary("test1234", 10);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testDiaryShouldBeInitializedWithAPasswordValueAndLimitValueWhenCreated() {
        Diary diary = new Diary("test", 10);
        assertNotNull(diary.getPassword());
        assertNotNull(diary.getDiaryLimit());
        assertNotNull(diary.getEntries());
        assertEquals("test", diary.getPassword());
        assertEquals(10, diary.getDiaryLimit());
    }

    @Test
    void testDiaryCanBeInitializedWithADiaryName(){
        Diary diary = new Diary("Lovelife", "12345", 10);
        assertEquals("Lovelife", diary.getDiaryName());
    }

    @Test
    void testDiaryShouldBeSetToDefaultNameWhenDiaryNameNotProvidedInConstructor(){
        Diary diary = new Diary("12345", 10);
        assertEquals("Diary Number 1", diary.getDiaryName());
    }

    @Test
    void testDiaryEntriesShouldBeInitializedToSizeZeroWhenCreated() {
        assertEquals(0, diary.getEntries().size());
    }

    @Test
    void testDiaryShouldBeAbleToAddNewEntries() {
        Entry entry = new Entry("Test", "Test Title");
        Entry entry1 = new Entry("Test1", "Test Title2");
        diary.addEntry(entry);
        assertEquals(1, diary.getEntries().size());
        assertEquals(entry, diary.getEntries().get(0));
        diary.addEntry(entry1);
        assertEquals(2, diary.getEntries().size());
        assertEquals(entry1, diary.getEntries().get(1));
    }

    @Test
    void testDiaryShouldNotBeAbleToAddNewEntriesOnceDiaryLimitHasBeenReached() {
        Entry entry = new Entry("Test");
        Entry entry1 = new Entry("Test2");
        Entry entry2 = new Entry("Test3");
        Diary diary = new Diary("1234", 2);
        diary.addEntry(entry);
        diary.addEntry(entry1);
        diary.addEntry(entry2);
        assertEquals(2, diary.getEntries().size());
    }

    @Test
    void testDiaryShouldBeAbleToGetAnEntryViaItsIndex() {
        Entry entry = new Entry("Test");
        Entry entry1 = new Entry("Test2");
        diary.addEntry(entry);
        diary.addEntry(entry1);
        Entry searchedEntry = diary.getEntry(0);
        assertEquals(entry.getTitle(), searchedEntry.getTitle());
        assertEquals(entry, searchedEntry);
        searchedEntry = diary.getEntry(1);
        assertEquals(entry1.getTitle(), searchedEntry.getTitle());
        assertEquals(entry1, searchedEntry);
    }

    @Test
    void testDiaryShouldBeAbleToGetAnEntryViaItsExactTitle() {
        Entry entry = new Entry("Test");
        Entry entry1 = new Entry("Test2");
        Entry entry2 = new Entry("Test3");
        diary.addEntry(entry);
        diary.addEntry(entry1);
        diary.addEntry(entry2);
        Entry searchedEntry = diary.getEntry("Test");
        assertEquals(entry.getTitle(), searchedEntry.getTitle());
        assertEquals(entry, searchedEntry);
        searchedEntry = diary.getEntry("Test2");
        assertEquals(entry1.getTitle(), searchedEntry.getTitle());
        assertEquals(entry1, searchedEntry);
    }

    @Test
    void testDiaryOverloadedGetEntryMethodShouldIgnoreCase(){
        Entry entry = new Entry("Test");
        Entry entry1 = new Entry("Test2");
        Entry entry2 = new Entry("Test3");
        diary.addEntry(entry);
        diary.addEntry(entry1);
        diary.addEntry(entry2);
        Entry searchedEntry = diary.getEntry("test", true);
        assertEquals(entry.getTitle(), searchedEntry.getTitle());
        assertEquals(entry, searchedEntry);
        Entry newSearchedEntry = diary.getEntry("test2", true);
        assertEquals(entry1.getTitle(), newSearchedEntry.getTitle());
        assertEquals(entry1, newSearchedEntry);
    }

    @Test
    void testDiaryShouldBeAbleToGetAllAddedEntries() {
        Entry entry = new Entry("Test1");
        Entry entry1 = new Entry("Test2");
        Entry entry2 = new Entry("Test3");
        ArrayList<Entry> testEntries = new ArrayList<>();
        testEntries.add(entry);
        testEntries.add(entry1);
        testEntries.add(entry2);
        diary.addEntry(entry);
        diary.addEntry(entry1);
        diary.addEntry(entry2);
        ArrayList<Entry> listOfEntries = diary.getEntries();
        assertEquals(testEntries.size(), listOfEntries.size());
        assertEquals(testEntries.get(0), listOfEntries.get(0));
        assertEquals(testEntries.get(testEntries.size() - 1), listOfEntries.get(listOfEntries.size() - 1));
        assertEquals(testEntries, listOfEntries);
    }

    @Test
    void testDiaryShouldBeAbleToDeleteAnEntryViaItsIndex() {
        Entry entry = new Entry("Test");
        Entry entry1 = new Entry("Test2");
        diary.addEntry(entry);
        diary.addEntry(entry1);
        assertEquals(2, diary.getEntries().size());
        diary.deleteEntry(1);
        assertEquals(1, diary.getEntries().size());
        diary.deleteEntry(0);
        assertEquals(0, diary.getEntries().size());
    }

    @Test
    void testDiaryShouldNotDeleteAnEntryViaItsIndexWhenWrongIndexPassedIn() {
        Entry entry = new Entry("Test");
        Entry entry1 = new Entry("Test2");
        diary.addEntry(entry);
        diary.addEntry(entry1);
        assertEquals(2, diary.getEntries().size());
        diary.deleteEntry(5);
        assertEquals(2, diary.getEntries().size());
    }

    @Test
    void testDiaryShouldBeAbleToDeleteAnEntryViaItsTitle() {
        Entry entry = new Entry("Test");
        Entry entry1 = new Entry("Test2");
        Entry entry2 = new Entry("Test3");
        diary.addEntry(entry);
        diary.addEntry(entry1);
        diary.addEntry(entry2);
        assertEquals(3, diary.getEntries().size());
        diary.deleteEntry("Test");
        assertEquals(2, diary.getEntries().size());
        diary.deleteEntry("Test2");
        assertEquals(1, diary.getEntries().size());
        diary.deleteEntry("Test3");
        assertEquals(0, diary.getEntries().size());
    }

    @Test
    void testDiaryShouldNotDeleteAnEntryWhenWrongEntryTitlePassedIn() {
        Entry entry = new Entry("Test");
        Entry entry1 = new Entry("Test2");
        Entry entry2 = new Entry("Test3");
        diary.addEntry(entry);
        diary.addEntry(entry1);
        diary.addEntry(entry2);
        assertEquals(3, diary.getEntries().size());
        diary.deleteEntry("testingAnEntryDeleteWithWrongTitle");
        assertEquals(3, diary.getEntries().size());
    }

    @Test
    void testDiaryShouldBeAbleToMatchingEntriesWhenKeywordEntryEntered() {
        Entry entry = new Entry("Test", "i dislike Test");
        Entry entry1 = new Entry("Test2", "i wrote a Test today");
        Entry entry2 = new Entry("Test3", "today's Testing sessions were gruelling ");
        Entry entry3 = new Entry("Test4", "love is a wonderful thing innit ");
        Entry entry4 = new Entry("Test5", "i think she might be here and that's lovely ");
        diary.addEntry(entry);
        diary.addEntry(entry1);
        diary.addEntry(entry2);
        diary.addEntry(entry3);
        diary.addEntry(entry4);
        ArrayList<Entry> matchingEntriesForTest = new ArrayList<>();
        matchingEntriesForTest.add(entry);
        matchingEntriesForTest.add(entry1);
        matchingEntriesForTest.add(entry2);
        ArrayList<Entry> diaryMatchingEntries = diary.entryKeywordSearch("Test");
        assertEquals(matchingEntriesForTest.size(), diaryMatchingEntries.size());
        assertEquals(matchingEntriesForTest, diaryMatchingEntries);
        ArrayList<Entry>  newResults = diary.entryKeywordSearch("love");
        ArrayList<Entry> newTestEntries = new ArrayList<>();
        newTestEntries.add(entry3);
        newTestEntries.add(entry4);
        assertEquals(newTestEntries.size(), newResults.size());
        assertEquals(newTestEntries, newResults);
    }

    @Test
    void testDiaryMatchingEntriesOverloadedVersionShouldIgnoreCaseWhenMatchingEntries() {
        Entry entry = new Entry("Test", "i dislike tesTS");
        Entry entry1 = new Entry("Test2", "i wrote a TEsT today");
        Entry entry2 = new Entry("Test3", "today's TesTing sessions were gruelling ");
        Entry entry3 = new Entry("Test4", "LoVe is a wonderful thing innit ");
        Entry entry4 = new Entry("Test5", "i think she might be here and that's Lovely ");
        diary.addEntry(entry);
        diary.addEntry(entry1);
        diary.addEntry(entry2);
        diary.addEntry(entry3);
        diary.addEntry(entry4);
        ArrayList<Entry> matchingEntriesForTest = new ArrayList<>();
        matchingEntriesForTest.add(entry);
        matchingEntriesForTest.add(entry1);
        matchingEntriesForTest.add(entry2);
        ArrayList<Entry> diaryMatchingEntries = diary.entryKeywordSearch("test", true);
        assertEquals(matchingEntriesForTest.size(), diaryMatchingEntries.size());
        assertEquals(matchingEntriesForTest, diaryMatchingEntries);
        ArrayList<Entry>  newResults = diary.entryKeywordSearch("love", true);
        ArrayList<Entry> newTestEntries = new ArrayList<>();
        newTestEntries.add(entry3);
        newTestEntries.add(entry4);
        assertEquals(newTestEntries.size(), newResults.size());
        assertEquals(newTestEntries, newResults);
    }

    @Test
    void testDiaryStringRep(){
        System.out.println(diary.toString());
    }


}