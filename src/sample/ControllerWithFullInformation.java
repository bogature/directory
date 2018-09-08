package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.models.ContactNumber;

import java.io.IOException;

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
    private Button deleteButton;

    @FXML
    void deleteNumber(ActionEvent event) {
        DB.deleteNumber(list.getSelectionModel().getSelectedItem().getId());
        listNumbers = FXCollections.observableArrayList(DB.getNumbers(Controller.itemSelected));
        list.setItems(listNumbers);
        deleteButton.setDisable(true);
    }

    @FXML
    void deleteContact(ActionEvent event) {
        DB.deleteContact(Controller.itemSelected.getId());
        Stage stage = (Stage) choiceBox.getScene().getWindow();
        stage.close();
        Controller.items.clear();
        Controller.items.addAll( FXCollections.observableArrayList(DB.getContacts()));
    }


    @FXML
    void onClickOk(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("addNumber.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("New Window");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner((Stage) list.getScene().getWindow());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println("bla");
        }
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

        name.textProperty().addListener((obs,oldTExt,newText)->{
            DB.uppdateContact(Controller.itemSelected.getId(),newText,surname.getText(),choiceBox.getValue());
            Controller.items.clear();
            Controller.items.addAll( FXCollections.observableArrayList(DB.getContacts()));
        });

        surname.textProperty().addListener((obs,oldTExt,newText)->{
            DB.uppdateContact(Controller.itemSelected.getId(),name.getText(),newText,choiceBox.getValue());
            Controller.items.clear();
            Controller.items.addAll( FXCollections.observableArrayList(DB.getContacts()));
        });

        choiceBox.getSelectionModel().selectedItemProperty().addListener((obs,oldTExt,newText)->{
            DB.uppdateContact(Controller.itemSelected.getId(),name.getText(),surname.getText(),newText);
            Controller.items.clear();
            Controller.items.addAll( FXCollections.observableArrayList(DB.getContacts()));
        });
        list.getSelectionModel().selectedItemProperty().addListener((prop, old, newItem) -> {
            deleteButton.setDisable(false);
        });
    }
}
