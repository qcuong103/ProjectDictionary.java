/**
 * class Word có thuộc tính word_target (từ mới), word_explain (giải nghĩa)
 */
public class Word {
    private String word_target;
    private String word_explain;

    /**
     * Constructor
     */

    public Word() {
        this.word_target = "";
        this.word_explain = "";
    }

    public Word(String word_target) {
        this.word_target = word_target;
        this.word_explain = "";
    }

    public Word(String word_target, String word_explain) {
        this.word_target = word_target;
        this.word_explain = word_explain;
    }

    public Word(Word word) {
        this.word_target = word.word_target;
        this.word_explain = word.word_explain;
    }

    /**
     * Getter
     * @return .
     */
    public String getWord_target() {
        return word_target;
    }

    public String getWord_explain() {
        return word_explain;
    }

    /**
     * Setter
     * @param word_target .
     */
    public void setWord_target(String word_target) {
        this.word_target = word_target;
    }

    public void setWord_explain(String word_explain) {
        this.word_explain = word_explain;
    }

    /**
     * Get String
     * @return .
     */
    public String getString() {
        return "|" + this.getWord_target() + "\t\t|" + this.getWord_explain();
    }

}