package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import sample.models.ContactNumber;

public class ControllerWithFullInformation {

    public static ObservableList<ContactNumber> listNumbers = FXCollections.observableArrayList();
    @FXML
    private ListView<ContactNumber> list;

    @FXML
    private TextField name;

    @FXML
    private TextField surname;

    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    void onClickOk(ActionEvent event) {

    }

    @FXML
    void initialize() {
        choiceBox.setItems(Controller.groups);
        name.setText(Controller.itemSelected.getName());
        surname.setText(Controller.itemSelected.getSurName());

        for (int i = 0;i< Controller.groups.size();i++){
            if (Controller.itemSelected.getGroup().equals(Controller.groups.get(i))){
                choiceBox.getSelectionModel().select(i);
            }
        }

        listNumbers = FXCollections.observableArrayList(DB.getNumbers(Controller.itemSelected));
        list.setItems(listNumbers);
    }
}
