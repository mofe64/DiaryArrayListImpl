package com.nubari;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

class EntryTest {
    Entry entry;

    @BeforeEach
    void setUp() {
        entry = new Entry("Test Title", "Test Entry");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testEntryObjectCanBeInitializedWithJustTitleField() {
        Entry entry = new Entry("TEST");
        assertNotNull(entry);
    }

    @Test
    void testEntryObjectEntryFieldShouldBeNullWhenEntryIsInitializedWithJustTheTitle() {
        Entry entry = new Entry("Test");
        assertNotNull(entry);
        assertNull(entry.getEntry());
    }

    @Test
    void testEntryTitleEntryShouldBeInitializedWhenEntryObjectCreated() {
        Entry entry = new Entry("Test title", "Test Entry");
        assertEquals("Test title", entry.getTitle());
        assertEquals("Test Entry", entry.getEntry());
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
        Calendar calendar = Calendar.getInstance();
        assertEquals(dateFormat.format(calendar.getTime()), entry.getDate());
    }

    @Test
    void testEntryDateShouldBeSetToDateOfEntryCreationWhenInitialized() {
        Entry entry = new Entry("Test title", "Test entry");
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
        Calendar calendar = Calendar.getInstance();
        assertNotNull(entry.getDate());
        assertEquals(dateFormat.format(calendar.getTime()), entry.getDate());
    }

    @Test
    void testUserShouldBeAbleToRetrieveTitle() {
        assertEquals("Test Title", entry.getTitle());
    }

    @Test
    void testUserShouldBeAbleToRetrieveEntryDetails() {
        assertEquals("Test Entry", entry.getEntry());
    }

    @Test
    void testUserShouldBeAbleToUpdateEntryTitle() {
        entry.setTitle("test new title");
        assertEquals("test new title", entry.getTitle());
    }

    @Test
    void testUserShouldBeAbleToUpdateEntry() {
        entry.setEntry("Test New Entry");
        assertEquals("Test New Entry", entry.getEntry());
    }

    @Test
    void testUserShouldBeAbleToRetrieveDateOfEntry() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
        Calendar calendar = Calendar.getInstance();
        assertEquals(dateFormat.format(calendar.getTime()), entry.getDate());
    }

    @Test
    void testEntryStringRepresentationShouldContainCorrectTitleEntryAndDate() {
        Entry entry = new Entry("Test title", "Test Entry");
        String entryTitle = entry.getTitle();
        String entryEntry = entry.getEntry();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
        Calendar calendar = Calendar.getInstance();
        String entryDate = dateFormat.format(calendar.getTime());
        String stringRepresentation = "Entry Details: " + "\n" +
                "Title: " + entryTitle + "\n" +
                "Entry: " + entryEntry + "\n" +
                "Date: " + entryDate;
        assertEquals(stringRepresentation, entry.toString());

    }
}