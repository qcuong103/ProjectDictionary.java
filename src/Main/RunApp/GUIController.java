import com.sun.speech.freetts.Voice;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.util.Collections;

import com.sun.speech.freetts.VoiceManager;

public class GUIController {
    public DictionaryManagement dictionaryManagement = new DictionaryManagement();
    public AddWordController controller = new AddWordController();
    @FXML
    TextField searchBar;
    @FXML
    private TextArea textArea;
    @FXML
    private ListView<String> sameWord;
    @FXML
    private void searchTheWord() {
        String word_Target = searchBar.getText();
        if (word_Target.length() == 0) {
            textArea.setText("Please write down a word!");
        } else if (word_Target.matches(".*\\d+.*")) {
            textArea.setText("Please don't include numbers! \n Only one word that contains only letters!");
        } else {
            String result = dictionaryManagement.dictionaryLookup(word_Target);
            if (result == null) {
                textArea.setText("Can't find the word you are looking for");
            } else {
                textArea.setText(result);
            }
        }
    }

    public void makeSound() {
        speech(searchBar.getText());
    }

    public void addWord (ActionEvent e) throws IOException {
        newScene("AddWord.fxml", e, 1);
    }

    public void editWord (ActionEvent e) throws IOException {
        newScene("EditWord.fxml", e, 2);
    }

    public void deleteWord (ActionEvent e) throws IOException {
        newScene("DelWord.fxml", e, 3);
    }

    ObservableList<String> entries = FXCollections.observableArrayList();

    public void showSameWord() {
        entries.clear();
        searchBar.textProperty().addListener(
                (observable, oldVal, newVal) -> handleSearchByKey(oldVal, newVal));
        sameWord.setMaxHeight(372);
        for (Word word : Dictionary.words) {
            entries.add(word.getWord_target());
        }
        sameWord.setItems(entries);
    }

    public void handleSearchByKey(String oldVal, String newVal) {
        if ( oldVal != null && (newVal.length() < oldVal.length()) ) {
            sameWord.setItems( entries );
        }

        newVal = newVal.toUpperCase();

        ObservableList<String> subentries = FXCollections.observableArrayList();
        for ( Object entry: Collections.unmodifiableList(sameWord.getItems())) {
            String entryText = (String)entry;
            if ( entryText.toUpperCase().contains(newVal) ) {
                subentries.add(entryText);
            }
        }
        sameWord.setItems(subentries);
    }

    private void speech(String text) {
        VoiceManager voiceManager = VoiceManager.getInstance();
        // gọi đến giọng của thằng có tên là kevin16, có thằng khác tên là kevin
        Voice syntheticVoice = voiceManager.getVoice("kevin16");
        syntheticVoice.allocate();
        syntheticVoice.speak(text);
        syntheticVoice.deallocate();
    }

    public void newScene(String nameFXML, ActionEvent e, int kindButton) throws IOException {
        Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(nameFXML));
        Parent deleteWordViewParent = loader.load();
        Scene scene = new Scene(deleteWordViewParent);
        controller = loader.getController();
        controller.getTargetGUI(searchBar.getText(), kindButton);
        stage.setScene(scene);
    }
}
