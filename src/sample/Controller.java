package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class Controller {

    @FXML
    public ListView<String> list;

    @FXML
    void onKlickOk(ActionEvent event) {

        list.getItems().add("Item 1");
        list.getItems().add("Item 2");
        list.getItems().add("Item 3");
        list.getItems().add("Item 4");

    }

}
