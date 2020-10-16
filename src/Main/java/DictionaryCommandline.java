import java.util.ArrayList;

/**
 * class DictionaryCommandline có hàm showAllWords()
 */
public class DictionaryCommandline extends DictionaryManagement{
    /**
     * Hàm showAllWords() có chức năng hiển thị kết quả danh sách dữ liệu từ điểntrên màn hình
     */
    public static void showAllWords() {
        System.out.println("No\t" + "|English\t" + "|Vietnamese");
        for (Word word : Dictionary.words) {
            System.out.print((Dictionary.words.indexOf(word) + 1) + "\t");
            System.out.println(word.getString());
        }
    }

    /**
     * Hàm dictionaryBasic() có chức năng gọi hàm insertFromCommandline() và showAllWords()
     */
    public void dictionaryBasic() {
        insertFromCommandline();
        showAllWords();
    }

    /**
     * hàm dictionaryAdvanced() có chức năng gọi hàm insertFromFile(), showAllWords() và dictionaryLookup()
     */
    public void dictionaryAdvanced(String word){
        this.insertFromFile();
        showAllWords();
        this.dictionaryLookup(word);
    }

    /**
     * hàm dictionarySearcher() có chức năng tìm kiếm các từ
     */

    public static ArrayList<String> dictionarySearcher(ArrayList<Word> list, String key) {
        ArrayList<String> result = new ArrayList<>();
        String pattern = key.toLowerCase();
        for (Word word : list) {
            if (word.getWord_target().toLowerCase().matches(pattern)) {
                result.add(word.getWord_target());
            }
        }
        return result;
    }
}