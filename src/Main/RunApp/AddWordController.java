import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AddWordController {
    @FXML
    private TextField addTarget;
    @FXML
    private TextField addExplain;
    @FXML
    TextField oldTarget;
    @FXML
    TextField oldExplain;
    @FXML
    TextField newTarget;
    @FXML
    TextField newExplain;
    @FXML
    private TextField deleteTarget;
    @FXML
    private TextField deleteExplain;

    public DictionaryManagement dictionaryManagement = new DictionaryManagement();
    public void setWordEdit(String oTarget, String oExplain) {
        oldTarget.setText(oTarget);
        oldExplain.setText(oExplain);
    }

//    public void setWordDelete(String delTarget) {
//        deleteTarget.setText(delTarget);
//    }

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
        dictionaryManagement.addWord((addTarget.getText()), (addExplain.getText()));
        addTarget.clear();
        addExplain.clear();
    }

    public void editWordButton() {
        dictionaryManagement.fixWord(oldTarget.getText(), newTarget.getText(), newExplain.getText());
        oldTarget.clear();
        oldExplain.clear();
        newTarget.clear();
        newExplain.clear();
    }

    public void deleteWordButton() {
        dictionaryManagement.deleteWord(deleteTarget.getText());
        deleteTarget.clear();
        deleteExplain.clear();
    }

    public void searchWordDelete() {
        deleteExplain.setText(dictionaryManagement.dictionaryLookup(deleteTarget.getText()));
    }
}

