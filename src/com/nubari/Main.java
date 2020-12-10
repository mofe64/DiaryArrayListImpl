package com.nubari;

import javax.swing.*;
import java.util.ArrayList;

public class Main {
    private static int sentinel = 1;

    public static void main(String[] args) {
        User user;

        JOptionPane.showMessageDialog(null, "Welcome to your Diary Management Service");
        String firstName = JOptionPane.showInputDialog("Please Enter your FirstName ");
        String lastName = JOptionPane.showInputDialog("Please Enter your LastName");
        String password = JOptionPane.showInputDialog("Please Enter your Password");
        user = registerUser(firstName, lastName, password);
        JOptionPane.showMessageDialog(null, "Account created Successfully");
        boolean isAuthenticated;
        String inputPassword = JOptionPane.showInputDialog("Please enter your password to continue");
        isAuthenticated = user.authenticate(inputPassword);
        if (isAuthenticated) {
            while (sentinel != -1) {
                int userInput = Integer.parseInt(JOptionPane.showInputDialog("Welcome to your dashboard\n " +
                        "1. Enter 1 to View All Diaries\n" +
                        "2. Enter 2 to Create a New Diary\n" +
                        "3. Enter 3 to Switch the Active Diary\n" +
                        "4. Enter 4 to Show the Active Diary\n" +
                        "5. Enter 5 to Create a New Entry \n" +
                        "6. Enter 6 to View All Entries in Active Diary\n" +
                        "7. Enter 7 to View to Specific Entry in Active Diary\n" +
                        "8. Enter 8 to Perform a Keyword Search in the Active Diary\n" +
                        "9. Enter 9 to Delete an Entry in the Active Diary\n" +
                        "10. Enter 10 to Exit"));

                evaluateMenuInput(userInput, user);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Wrong password entered exiting .....");
        }

    }

    private static User registerUser(String firstName, String lastName, String password) {
        User user = new User(firstName, lastName, password);
        return user;
    }

    private static void evaluateMenuInput(int userInput, User user) {
        switch (userInput) {
            case 1: {
                viewAllUserDiaries(user);
                break;
            }
            case 2: {
                createANewDiary(user);
                break;
            }
            case 3: {
                switchActiveDiary(user);
                break;
            }
            case 4: {
                showActiveDiary(user);
                break;
            }
            case 5: {
            }
            case 6: {
            }
            case 7: {
            }
            case 8: {
            }
            case 9: {
            }
        }
    }

    private static void viewAllUserDiaries(User user) {
        StringBuilder stringBuilder = new StringBuilder();
        ArrayList<Diary> userDiaries = user.listDiaries();
        for (Diary diary : userDiaries) {
            stringBuilder.append("\n");
            stringBuilder.append(diary);
            stringBuilder.append("\n");
        }
        JOptionPane.showMessageDialog(null, stringBuilder);
    }

    private static void createANewDiary(User user) {
        Object[] options = {"Yes", "No"};
        int returnValue = JOptionPane.showOptionDialog(null, "Would you like to set a diary name or " +
                        "use the default name"
                , "Test", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if (returnValue == 0) {
            String diaryName = JOptionPane.showInputDialog("Enter the diary name");
            int diaryLimit = Integer.parseInt(JOptionPane.showInputDialog("Enter Diary Limit"));
            user.addDiary(diaryLimit, diaryName);
            JOptionPane.showMessageDialog(null, "Diary created successfully");
        } else if (returnValue == 1) {
            int diaryLimit = Integer.parseInt(JOptionPane.showInputDialog("Enter Diary Limit"));
            user.addDiary(diaryLimit);
            JOptionPane.showMessageDialog(null, "Diary created successfully");
        } else {
            JOptionPane.showMessageDialog(null, "GoodBye ...");
            sentinel = -1;
        }
    }

    private static void switchActiveDiary(User user) {
        Diary currentActiveDiary = user.getActiveDiary();
        int activeDiaryIndex = user.listDiaries().indexOf(currentActiveDiary);
        int diariesSize = user.listDiaries().size();
        int lastDiaryIndex = diariesSize - 1;
        if (diariesSize > 1) {
            if (activeDiaryIndex == lastDiaryIndex) {
                user.setActiveDiary(user.listDiaries().get(lastDiaryIndex - 1));
            } else if (activeDiaryIndex < lastDiaryIndex) {
                user.setActiveDiary(user.listDiaries().get(activeDiaryIndex + 1));
            }
            JOptionPane.showMessageDialog(null, "Active diary successfully changed to " + user.getActiveDiary().getDiaryName());
        } else {
            JOptionPane.showMessageDialog(null, "You only have one current diary ");
        }
    }

    private static void showActiveDiary(User user) {
        JOptionPane.showMessageDialog(null, "Active dairy is currently " + user.getActiveDiary());
    }
}
