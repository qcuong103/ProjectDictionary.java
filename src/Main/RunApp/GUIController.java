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

import com.sun.speech.freetts.VoiceManager;

public class GUIController {
    public DictionaryManagement dictionaryManagement = new DictionaryManagement();
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
            textArea.setText(dictionaryManagement.dictionaryLookup(word_Target));
        }
    }

    public void makeSound() {
//        File soundNameFile = new File("./src/Main/resources/sound/SoundName.txt");
//        InputStream inputStream = new FileInputStream(soundNameFile);
//        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
//        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//        String line;
//        String soundName = "notFound";
//        while ((line = bufferedReader.readLine()) != null) {
//            if (line.equals(searchBar.getText())) {
//                soundName = line;
//            }
//        }
//        inputStream.close();
//        inputStreamReader.close();
//        bufferedReader.close();
//        String path = "./src/Main/resources/sound/" + soundName + ".mp3";
//        Media media = new Media(new File(path).toURI().toString());
//        MediaPlayer mediaPlayer = new MediaPlayer(media);
//        mediaPlayer.setAutoPlay(true);
        speech(searchBar.getText());
    }

    public void addWord (ActionEvent e) throws IOException {
        Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AddWord.fxml"));
        Parent addWordViewParent = loader.load();
        Scene scene = new Scene(addWordViewParent);
        stage.setScene(scene);
    }

    public void editWord (ActionEvent e) throws IOException {
        Stage stage1 = (Stage)((Node) e.getSource()).getScene().getWindow();
        FXMLLoader loader1 = new FXMLLoader();
        loader1.setLocation(getClass().getResource("EditWord.fxml"));
        Parent editWordViewParent = loader1.load();
        Scene scene1 = new Scene(editWordViewParent);
        AddWordController controller = loader1.getController();
        controller.setWordEdit(searchBar.getText(), textArea.getText());
        stage1.setScene(scene1);
    }

    public void deleteWord (ActionEvent e) throws IOException {
        Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
        FXMLLoader loader2 = new FXMLLoader();
        loader2.setLocation(getClass().getResource("DelWord.fxml"));
        Parent deleteWordViewParent = loader2.load();
        Scene scene2 = new Scene(deleteWordViewParent);
        stage.setScene(scene2);
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

        ObservableList<String> subentries;
        subentries = FXCollections.observableArrayList();
        for ( Object entry: sameWord.getItems() ) {
            String entryText = (String)entry;
            if ( entryText.toUpperCase().contains(newVal) ) {
                subentries.add(entryText);
            }
        }
        sameWord.setItems(subentries);
    }

    private void speech(String text) {
        VoiceManager voiceManager = VoiceManager.getInstance();
// gọi đến giọng của thằng có tên là kevin 16, có thằng khác tên là kevin và một thằng tên
// là anna hay gì đó mình không nhớ!
        com.sun.speech.freetts.Voice syntheticVoice = voiceManager.getVoice("kevin16");
        syntheticVoice.allocate();
        syntheticVoice.speak(text);
        syntheticVoice.deallocate();
    }
}
