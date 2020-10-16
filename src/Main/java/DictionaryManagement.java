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
            System.out.println("Nhap tu tieng Anh: ");
            String word_Target = input.nextLine();
            System.out.println("Nhap nghia tieng Viet: ");
            String word_Explain = input.nextLine();
            Word word = new Word(word_Target, word_Explain);
            Dictionary.words.add(word);
        }
    }

    /**
     * Hàm insertFromFile() nhập dữ liệu từ điển từ tệp dictionaries.txt
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
    public String dictionaryLookup(String word) {
        String result = null;
        for (int i = 0; i < Dictionary.words.size(); i++){
            if(word.equals(Dictionary.words.get(i).getWord_target())) {
                result = Dictionary.words.get(i).getWord_explain();
            }
            if (result == null) {
                result = "Can't find the word you are looking for";
            }
        }
        return result;
    }

    /**
     * các hàm có chức năng thêm sửa xóa dữ liệu từ điển bằng dòng lệnh
     */
    public void addWord(String target, String explain) {
        Word word = new Word(target, explain);
        Dictionary.words.add(word);
    }

    public void deleteWord(String delTarget) {
        int n = 0;
        for (int i = 0; i < Dictionary.words.size(); i++){
            if(delTarget.equals(Dictionary.words.get(i).getWord_target())){
                n = i;
            }
        }
        Dictionary.words.remove(n);
    }

    public void fixWord(String oldTarget, String newTarget, String newExplain) {
        for (int i = 0; i < Dictionary.words.size(); i++) {
            if (oldTarget.equals(Dictionary.words.get(i).getWord_target())) {
                Dictionary.words.get(i).setWord_target(newTarget);
                Dictionary.words.get(i).setWord_explain(newExplain);
            }
        }
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
                outputStreamWriter.write(word.getWord_target());
                outputStreamWriter.write("\t");
                outputStreamWriter.write(word.getWord_explain());
                outputStreamWriter.write("\n");
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