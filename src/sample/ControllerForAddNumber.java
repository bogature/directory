package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerForAddNumber {

    @FXML
    private Button saveButton;

    @FXML
    private TextField numberField;

    @FXML
    void save(ActionEvent event) {
        if (!numberField.getText().isEmpty()) {
            DB.addNumber(numberField.getText(),Controller.itemSelected.getId());
            Stage stage = (Stage) numberField.getScene().getWindow();
            stage.close();

            ControllerWithFullInformation.listNumbers.clear();
            ControllerWithFullInformation.listNumbers.addAll( FXCollections.observableArrayList(DB.getNumbers(Controller.itemSelected)));
        }
    }

    public void initialize(){
        numberField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    numberField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

}
