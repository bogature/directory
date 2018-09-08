package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.models.Contact;

import java.io.IOException;
import java.util.ArrayList;

public class Controller {

    public static ObservableList<String> groups = FXCollections.observableArrayList("Friend","Family","Work");
    public static ObservableList<Contact> items = FXCollections.observableArrayList();
    public static Contact itemSelected;
    @FXML
    public ListView<Contact> list;

    @FXML
    private TextField search;

    @FXML
    void onClickOk(ActionEvent event) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("addContact.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner((Stage) list.getScene().getWindow());
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
                if (newItem != null) {
                    itemSelected = newItem;
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("fullInformation.fxml"));
                    Scene scene = new Scene(fxmlLoader.load());
                    Stage stage = new Stage();
                    stage.setTitle("New Window");
                    stage.initModality(Modality.WINDOW_MODAL);
                    stage.initOwner((Stage) list.getScene().getWindow());
                    stage.setScene(scene);
                    stage.show();
                }
            } catch (IOException e) {
                System.out.println("bla");
            }
        });

        search.textProperty().addListener((abc,old,newText)->{
            ArrayList<Contact> myList = new ArrayList<>();
            if (!newText.isEmpty()) {
                for (Contact temp :
                        DB.getContacts()) {

                    if (temp.getName().contains(newText)) {
                        myList.add(temp);
                        continue;
                    }
                    if (temp.getSurName().contains(newText)) {
                        myList.add(temp);
                        continue;
                    }
                    if (temp.getGroup().contains(newText)) {
                        myList.add(temp);
                        continue;
                    }
                    if ((temp.getName() + " " + temp.getSurName()).contains(newText)) {
                        myList.add(temp);
                        continue;
                    }
                    if ((temp.getSurName() + " " + temp.getName()).contains(newText)) {
                        myList.add(temp);
                    }
                }
                items.clear();
                items.addAll( FXCollections.observableArrayList(myList));
            }else {

                items.clear();
                items.addAll( FXCollections.observableArrayList(DB.getContacts()));
            }
        });
    }
}
