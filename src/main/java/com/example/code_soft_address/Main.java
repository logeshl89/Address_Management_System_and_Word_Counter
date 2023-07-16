package com.example.code_soft_address;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {
    private AddressBook addressBook;
    private TableView<Contact> tableView;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        addressBook = new AddressBook();


        GridPane gridPane = createGridPane();
        TextField nameField = new TextField();
        TextField phoneNumberField = new TextField();
        TextField emailField = new TextField();
        Button addButton = new Button("Add Contact");
        Button removeButton = new Button("Remove Contact");
        Button searchButton = new Button("Search Contact");
        tableView = new TableView<>();


        TableColumn<Contact, String> nameColumn = new TableColumn<>("Name");
        TableColumn<Contact, String> phoneNumberColumn = new TableColumn<>("Phone Number");
        TableColumn<Contact, String> emailColumn = new TableColumn<>("Email");

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        nameColumn.setPrefWidth(200);
        phoneNumberColumn.setPrefWidth(150);
        emailColumn.setPrefWidth(250);
        tableView.getColumns().addAll(nameColumn, phoneNumberColumn, emailColumn);


        ObservableList<Contact> allContacts = FXCollections.observableArrayList(addressBook.getAllContacts());


        tableView.setItems(allContacts);

        // Add event handlers
        addButton.setOnAction(e -> {
            String name = nameField.getText();
            String phoneNumber = phoneNumberField.getText();
            String email = emailField.getText();

            Contact contact = new Contact(name, phoneNumber, email);
            addressBook.addContact(contact);
            allContacts.add(contact);
            clearFields(nameField, phoneNumberField, emailField);
        });

        removeButton.setOnAction(e -> {
            Contact selectedContact = tableView.getSelectionModel().getSelectedItem();

            if (selectedContact != null) {
                addressBook.removeContact(selectedContact);
                allContacts.remove(selectedContact);
                clearFields(nameField, phoneNumberField, emailField);
            }
        });
        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                nameField.setText(newSelection.getName());
                phoneNumberField.setText(newSelection.getPhoneNumber());
                emailField.setText(newSelection.getEmail());
            } else {
                clearFields(nameField, phoneNumberField, emailField);
            }
        });
        removeButton.setOnAction(e -> {
            Contact selectedContact = tableView.getSelectionModel().getSelectedItem();

            if (selectedContact != null) {
                addressBook.removeContact(selectedContact);
                allContacts.remove(selectedContact);
                clearFields(nameField, phoneNumberField, emailField);
            }
        });
        searchButton.setOnAction(e -> {
            String name = nameField.getText();
            Contact contact = addressBook.searchContact(name);

            if (contact != null) {
                tableView.getSelectionModel().select(contact);
                tableView.scrollTo(contact);
            } else {
                tableView.getSelectionModel().clearSelection();
            }
        });


        gridPane.add(new Label("Name:"), 0, 0);
        gridPane.add(nameField, 1, 0);
        gridPane.add(new Label("Phone Number:"), 0, 1);
        gridPane.add(phoneNumberField, 1, 1);
        gridPane.add(new Label("Email:"), 0, 2);
        gridPane.add(emailField, 1, 2);
        gridPane.add(addButton, 0, 3);
        gridPane.add(removeButton, 1, 3);
        gridPane.add(searchButton, 0, 4);
        gridPane.add(tableView, 0, 5, 2, 1);


        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);


        Scene scene = new Scene(gridPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Address Book");
        primaryStage.show();
    }

    private GridPane createGridPane() {
        GridPane gridPane = new GridPane();
        gridPane.setPrefSize(720, 640);
        return gridPane;
    }

    private void clearFields(TextField... fields) {
        for (TextField field : fields) {
            field.clear();
        }
    }
}


