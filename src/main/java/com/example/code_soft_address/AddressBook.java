package com.example.code_soft_address;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddressBook {
    private Connection connection;

    public AddressBook() {
        try {

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/addressbook", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addContact(Contact contact) {
        try {

            String query = "INSERT INTO contacts (name, phone_number, email) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, contact.getName());
            statement.setString(2, contact.getPhoneNumber());
            statement.setString(3, contact.getEmail());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeContact(Contact contact) {
        try {

            String query = "DELETE FROM contacts WHERE name = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, contact.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Contact searchContact(String name) {
        try {

            String query = "SELECT * FROM contacts WHERE name = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String phoneNumber = resultSet.getString("phone_number");
                String email = resultSet.getString("email");
                return new Contact(name, phoneNumber, email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Contact> getAllContacts() {
        List<Contact> contacts = new ArrayList<>();

        try {

            String query = "SELECT * FROM contacts";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String phoneNumber = resultSet.getString("phone_number");
                String email = resultSet.getString("email");
                contacts.add(new Contact(name, phoneNumber, email));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return contacts;
    }

    public void closeConnection() {
        try {

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


