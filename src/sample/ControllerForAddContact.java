package sample;


import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerForAddContact {
    @FXML
    private TextField nameField;

    @FXML
    private TextField surnameField;

    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    void save(ActionEvent event) {
        if (!nameField.getText().isEmpty() && !surnameField.getText().isEmpty() && choiceBox.getValue()!=null) {
            DB.addContact(nameField.getText(), surnameField.getText(), choiceBox.getValue());
            Stage stage = (Stage) choiceBox.getScene().getWindow();
            stage.close();

            Controller.items.clear();
            Controller.items.addAll( FXCollections.observableArrayList(DB.getContacts()));
        }
    }

    @FXML
    public void initialize(){
        choiceBox.setItems(Controller.groups);
    }

}