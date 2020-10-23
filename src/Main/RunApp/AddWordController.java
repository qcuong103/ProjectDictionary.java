import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class AddWordController {
    public TextField addTarget;
    @FXML
    TextField addExplain;
    @FXML
    TextField oldTarget;
    @FXML
    TextField oldExplain;
    @FXML
    TextField newTarget;
    @FXML
    TextField newExplain;
    @FXML
    TextField deleteTarget;
    @FXML
    TextField deleteExplain;
    @FXML
    Text alertADD;
    @FXML
    Text alertEDIT;
    @FXML
    Text alertDELETE;

    public DictionaryManagement dictionaryManagement = new DictionaryManagement();

    public void goBack(ActionEvent e) throws IOException {
        dictionaryManagement.dictionaryExportToFile();
        Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("GUI.fxml"));
        Parent GUIViewParent = loader.load();
        Scene scene = new Scene(GUIViewParent);
        stage.setScene(scene);
    }

    public void addWordButton() {
        if ((addTarget.getText().length() == 0) || (addExplain.getText().length() == 0)) {
            alertADD.setText("Target or/and Explain is NULL");
        } else {
            alertADD.setText("DONE!!!");
            dictionaryManagement.addWord((addTarget.getText()), (addExplain.getText()));
            addTarget.clear();
            addExplain.clear();
        }
    }

    public void editWordButton() {
        if (oldTarget.getText().length() == 0) {
            alertEDIT.setText("Target is NULL");
        } else if (dictionaryManagement.dictionaryLookup(oldTarget.getText()).length() == 0) {
            alertEDIT.setText("Can't find the word you are looking for");
        } else {
            alertEDIT.setText("DONE!!!");
            dictionaryManagement.editWord(oldTarget.getText(), newTarget.getText(), newExplain.getText());
            oldTarget.clear();
            oldExplain.clear();
            newTarget.clear();
            newExplain.clear();
        }
    }

    public void deleteWordButton() {
        if (deleteTarget.getText().length() == 0) {
            alertDELETE.setText("Target is NULL");
        } else if (dictionaryManagement.dictionaryLookup(deleteTarget.getText()).length() == 0) {
            alertDELETE.setText("Can't find the word you are looking for");
        } else {
            alertDELETE.setText("DONE!!!");
            dictionaryManagement.deleteWord(deleteTarget.getText());
            deleteTarget.clear();
            deleteExplain.clear();
        }
    }

    public void getTargetGUI(String target, int kindButton) {
        switch (kindButton) {
            case 1:
                addTarget.setText(target);
                break;
            case 2:
                oldTarget.setText(target);
                searchWordEdit();
                break;
            default:
                deleteTarget.setText(target);
                searchWordDelete();
        }
    }

    public void searchWordEdit() {
        oldExplain.setText(dictionaryManagement.dictionaryLookup(oldTarget.getText()));
    }
    public void searchWordDelete() {
        deleteExplain.setText(dictionaryManagement.dictionaryLookup(deleteTarget.getText()));
    }
}

