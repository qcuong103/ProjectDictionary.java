import java.io.*;
import java.util.Scanner;


public class DictionaryManagement {
    Scanner input = new Scanner(System.in);

    /**
     * Hàm insertFromCommandline() có chức năng nhập liệu
     * Nhập vào bàn phím số lượng từ vựng (Word).
     */
    public void insertFromCommandline() {
        System.out.print("Nhap so tu can nhap: ");
        int n = input.nextInt();
        input.nextLine();
        for (int i = 0; i < n; i++) {
            System.out.print("Nhap tu tieng Anh: ");
            String word_Target = input.nextLine();
            System.out.print("Nhap nghia tieng Viet: ");
            String word_Explain = input.nextLine();
            Word word = new Word(word_Target, word_Explain);
            Dictionary.words.add(word);
        }
    }

    /**
     * Hàm insertFromFile() nhập dữ liệu từ điển từ tệp Dictionaries.txt
     */
    public void insertFromFile() {
        try {
            File dicFile = new File("./Data/Dictionaries.txt");
            InputStream inputStream = new FileInputStream(dicFile);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            String[] lineWord;
            while ((line = bufferedReader.readLine()) != null) {
                lineWord = line.split("\t", 2);
                Word wordStr = new Word(lineWord[0], lineWord[1]);
                Dictionary.words.add(wordStr);
            }
            inputStream.close();
            inputStreamReader.close();
            bufferedReader.close();
        } catch (Exception ex) {
            System.out.println("Loi doc file: "+ex);
        }
    }

    /**
     * hàm dictionaryLookup() có chức năng tra cứu từ điển bằng dòng lệnh
     */
    public String dictionaryLookup(String target) {
        Trie trie = new Trie();
        for (Word word : Dictionary.words) {
            trie.insertRecursive(word);
        }
        String result = trie.searchRecursive(target);
//        for (int i = 0; i < Dictionary.words.size(); i++){
//            if(target.equals(Dictionary.words.get(i).getWord_target())) {
//                result = Dictionary.words.get(i).getWord_explain();
//                result = result.replace(" /", "\n /");
//                result = result.replace("/ ", "/ \n");
//            }
//        }
//
        result = result.replace(" /", "\n /");
        result = result.replace("/ ", "/ \n");
//        return result;
        return result;
    }

    /**
     * các hàm có chức năng thêm sửa xóa dữ liệu từ điển bằng dòng lệnh
     */
    public void addWord(String target, String explain) {
        if (dictionaryLookup(target).length() != 0) {
            editWord(target, target, (dictionaryLookup(target) + ", " + explain));
        } else {
            Word word = new Word(target, explain);
            Dictionary.words.add(word);
        }
    }

    /**
     * edit Word.
     * @param oldTarget .
     * @param newTarget .
     * @param newExplain .
     */
    public void editWord(String oldTarget, String newTarget, String newExplain) {
        for (int i = 0; i < Dictionary.words.size(); i++) {
            if (oldTarget.equals(Dictionary.words.get(i).getWord_target())) {
                if (newTarget.length() != 0) Dictionary.words.get(i).setWord_target(newTarget);
                if (newExplain.length() != 0) Dictionary.words.get(i).setWord_explain(newExplain);
            }
        }
    }

    /**
     * Delete Word.
     * @param delTarget .
     */
    public void deleteWord(String delTarget) {
        int n = 0;
        for (int i = 0; i < Dictionary.words.size(); i++){
            if(delTarget.equals(Dictionary.words.get(i).getWord_target())){
                n = i;
            }
        }
        Dictionary.words.remove(n);
    }

    /**
     * hàm dictionaryExportToFile() có chức năng xuất dữ liệu từ điển hiện tại ra files
     */
    public void dictionaryExportToFile() {
        try {
            File dicFile = new File("./Data/Dictionaries.txt");
            OutputStream outputStream = new FileOutputStream(dicFile);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
            for (Word word : Dictionary.words) {
                outputStreamWriter.write(String.format("%s\t%s\n", word.getWord_target(), word.getWord_explain()));
//                outputStreamWriter.write("\t");
//                outputStreamWriter.write(word.getWord_explain());
//                outputStreamWriter.write("\n");
            }
            outputStreamWriter.flush();
            outputStream.close();
            outputStreamWriter.close();
            bufferedWriter.close();
        } catch (IOException ex) {
            System.out.println("Loi ghi file: "+ex);
        }
    }
}