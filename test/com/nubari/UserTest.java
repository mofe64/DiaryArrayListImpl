package com.nubari;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    User user;
    @BeforeEach
    void setUp() {
      user = new User("Eyimofe", "Ogunbiyi", "test1234");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testUserObjectShouldBeInitializedWithFirstNameLastNameAndPassWordFields(){
        assertNotNull(user.getFirstName());
        assertNotNull(user.getLastName());
        assertNotNull(user.getUserPassword());
    }

    @Test
    void testUserHasAtLeastOneDiaryObjectInitializedAtCreation(){
        assertNotNull(user.getActiveDiary());
        assertEquals(1, user.listDiaries().size());
    }

    @Test
    void testUserInstanceVariablesAreInitializedToValuesPassedIntoConstructors(){
       User user = new User("Eyimofe", "Ogunbiyi", "test1234");
       assertEquals("Eyimofe",user.getFirstName());
       assertEquals("Ogunbiyi", user.getLastName());
       assertEquals("test1234", user.getUserPassword());
    }

    @Test
    void testUsersStartingDiaryPasswordShouldBeSetToUserPassword(){
        Diary userDefaultDiary = user.getActiveDiary();
        assertEquals(user.getUserPassword(), userDefaultDiary.getPassword());
    }

    @Test
    void testUserCanCreateAdditionalDiariesViaAddDiaryMethodJustPassingDiaryLimit(){
        user.addDiary(50);
        assertEquals(2, user.listDiaries().size());
        user.addDiary(50);
        assertEquals(3, user.listDiaries().size());
    }
    @Test
    void testUserAuthenticateMethodProperlyChecksUserPassword(){
        String wrongPassword = "Test12345678";
        assertFalse(user.authenticate(wrongPassword));
        String rightPassword = "test1234";
        assertTrue(user.authenticate(rightPassword));
    }

    @Test
    void testUserCanCreateAdditionalDiariesViaAddDiaryMethodPassingDiaryLimitDiaryName(){
        user.addDiary(50, "My love life");
        assertEquals(2, user.listDiaries().size());
        Diary latestDiary = user.getActiveDiary();
        assertEquals("My love life", latestDiary.getDiaryName());
        user.addDiary(50, "My sex life");
        assertEquals(3, user.listDiaries().size());
        Diary latestDiary2 = user.getActiveDiary();
        assertEquals("My sex life", latestDiary2.getDiaryName());
    }

    @Test
    void testUserCabCreateAdditionalDiariesViaAddDiaryMethodPassingDiaryLimitDiaryNameAndDiaryPassword(){
        user.addDiary(50, "My love life", "test1234");
        assertEquals(2, user.listDiaries().size());
        assertEquals("My love life", user.getActiveDiary().getDiaryName());
        user.addDiary(50, "My sex life", "test1234");
        assertEquals(3, user.listDiaries().size());
        assertEquals("My sex life", user.getActiveDiary().getDiaryName());
    }

    @Test
    void testUserActiveDiarySwitchesToNewlyCreatedDiaryWhenUserAddsANewDiary(){
        user.addDiary(50, "test", "test1234");
        assertTrue(user.getActiveDiary().getDiaryName().equals("test"));
        assertTrue(user.getActiveDiary().getDiaryLimit() == 50);
        assertTrue(user.getActiveDiary().getPassword().equals("test1234"));
    }

    @Test
    void testWhenUserCreatesNewDiaryWithoutPassingInPasswordDiaryPasswordIsDefaultedToUserPassword(){
        user.addDiary(50, "test");
        assertTrue(user.getUserPassword().equals(user.getActiveDiary().getPassword()));
    }

    @Test
    void testWhenUserCreatesNewDiaryAndPassedInAPasswordDiaryPasswordIsSameAsPassedInPassedWord(){
        user.addDiary(50, "test", "test");
        assertTrue("test".equals(user.getActiveDiary().getPassword()));
    }

    @Test
    void testUserCanRemoveDiary(){
        user.addDiary("TestName" );
        assertTrue("TestName".equals(user.getActiveDiary().getDiaryName()));
        assertEquals(2, user.listDiaries().size());
        user.addDiary("TestName2");
        user.removeDiary("TestName");
        assertEquals(2, user.listDiaries().size());
    }

    @Test
    void testIfActiveDiaryIsRemovedFromDairiesActiveDiaryIsChangedToPreviousDiaryInDairiesList(){
        user.addDiary("TestDiary1");
        user.addDiary("TestDiary2");
        assertEquals(3, user.listDiaries().size());
        assertEquals(user.getActiveDiary(), user.listDiaries().get(2));
        user.removeDiary("TestDiary2");
        assertEquals("TestDiary1",user.getActiveDiary().getDiaryName());
        assertEquals(2, user.listDiaries().size());
    }

}