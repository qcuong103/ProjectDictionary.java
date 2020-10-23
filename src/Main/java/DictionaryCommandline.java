import java.util.ArrayList;

/**
 * class DictionaryCommandline có hàm showAllWords()
 */
public class DictionaryCommandline extends DictionaryManagement{
    /**
     * Hàm showAllWords() có chức năng hiển thị kết quả danh sách dữ liệu từ điển trên màn hình
     */
    public void showAllWords() {
        System.out.println("No\t" + "|English\t\t\t" + "|Vietnamese");
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
    public void dictionaryAdvanced(String target){
        this.insertFromFile();
        showAllWords();
        this.dictionaryLookup(target);
    }

    /**
     * hàm dictionarySearcher() có chức năng tìm kiếm các từ gợi ý
     */
    public static ArrayList<String> dictionarySearcher(ArrayList<String> list, String key) {
        ArrayList<String> subentries = new ArrayList<>();
        for (String target : list) {
            if (target.toUpperCase().contains(key)) {
                subentries.add(target);
            }
        }
        return subentries;
    }
}