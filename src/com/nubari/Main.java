package com.nubari;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        User user;
        int sentinel = 1;
        JOptionPane.showMessageDialog(null, "Welcome to your Diary Management Service");
        String firstName = JOptionPane.showInputDialog("Please Enter your FirstName ");
        String lastName = JOptionPane.showInputDialog("Please Enter your LastName");
        String password = JOptionPane.showInputDialog("Please Enter your Password");
        user = registerUser(firstName, lastName, password);
        while (sentinel != -1) {

        }
    }

    private static User registerUser(String firstName, String lastName, String password) {
        User user = new User(firstName, lastName, password);
        return user;
    }
}
