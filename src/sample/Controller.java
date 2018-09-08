package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import sample.models.Contact;

import java.io.IOException;

public class Controller {

    public static ObservableList<String> groups = FXCollections.observableArrayList("Friend","Family","Work");
    public static ObservableList<Contact> items = FXCollections.observableArrayList();
    public static Contact itemSelected;
    @FXML
    public ListView<Contact> list;

    @FXML
    void onClickOk(ActionEvent event) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("addContact.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("New Window");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println("bla");
        }
    }

    @FXML
    public void initialize() {
        items = FXCollections.observableArrayList(DB.getContacts());
        list.setItems(items);

        list.getSelectionModel().selectedItemProperty().addListener((prop, old, newItem) -> {
            try {
                itemSelected = newItem;
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("fullInformation.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
                stage.setTitle("New Window");
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                System.out.println("bla");
            }
        });
    }
}
