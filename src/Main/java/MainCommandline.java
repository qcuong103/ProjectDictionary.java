import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Scanner;

public class MainCommandline {
    static DictionaryManagement dictionaryManagement = new DictionaryManagement();
    static DictionaryCommandline dictionaryCommandline = new DictionaryCommandline();
    static Scanner input = new Scanner(System.in);
//    private static Object ObservableList;
//    private static Object String;

    public static void main(String[] args) {
//        Scanner input = new Scanner(System.in);
//        System.out.print("Nhap so luong tu vung: ");
//        int numWord = input.nextInt();
//        System.out.print("Nhap tu ");
        TestCommandline();
    }

    public static void TestCommandline() {


        System.out.println("1. Insert From Commandline\n2. Insert From File\n3. Dictionary Lookup\n4. Add Word\n5. Edit Word\n6.Delete Word\n7. Dictionary Export To File");
        System.out.println("8. Show All Words\n9. Dictionary Basic\n10. Dictionary Advanced\n11. Dictionary Searcher");
        System.out.print("Nhap thao tac: ");
        int index = input.nextInt();
        Menu(index);
    }

    public static void Menu(int index) {
        String target, explain;
        switch (index) {
            case 1:
                dictionaryManagement.insertFromCommandline();
                System.out.println("\nDONE!!!\n");
                BacktoMenu(index);
                break;
            case 2:
                dictionaryManagement.insertFromFile();
                System.out.println("\nDONE!!\n");
                BacktoMenu(index);
                break;
            case 3:
                System.out.print("Nhap tu can Tra: ");
                target = input.nextLine();
                if (dictionaryManagement.dictionaryLookup(target) != null) {
                    System.out.println("Nghia cua tu can tra la: " + (dictionaryManagement.dictionaryLookup(target)));
                    System.out.println("\nDone!!!\n");
                } else {
                    System.out.println("Can't find the word you are looking for");
                }
                BacktoMenu(index);
                break;
            case 4:
                System.out.print("Nhap tu can them: ");
                target = input.nextLine();
                System.out.print("Nhap nghia cua tu tren: ");
                explain = input.nextLine();
                while ((target.length() == 0) || (explain.length() == 0)) {
                    if (target.length() == 0) {
                        System.out.println("Tu can them khong the trong!!");
                        System.out.print("Nhap lai: ");
                        target = input.nextLine();
                    } else {
                        System.out.println("Nghia can them khgon the trong!!");
                        System.out.print("Nhap lai: ");
                        explain = input.nextLine();
                    }
                }
                dictionaryManagement.addWord(target, explain);
                System.out.println("\nDONE!!\n");
                BacktoMenu(index);
                break;
            case 5:
                System.out.print("Nhap tu can sua: ");
                String oldTarget = input.nextLine();
                while ((oldTarget.length() == 0) || (dictionaryManagement.dictionaryLookup(oldTarget) == null)) {
                    if (oldTarget.length() == 0) {
                        System.out.println("Tu can sua khong the trong!!");
                    } else {
                        System.out.println("Can't find the word you are looking for");
                    }
                    System.out.print("Nhap lai: ");
                    oldTarget = input.nextLine();
                }
                System.out.print("Nhap tu moi: ");
                target = input.nextLine();
                System.out.print("Nhap nghia moi: ");
                explain = input.nextLine();
                dictionaryManagement.editWord(oldTarget, target, explain);
                System.out.println("\nDONE!!\n");
                BacktoMenu(index);
                break;
            case 6:
                System.out.print("Nhap tu can xoa: ");
                target = input.nextLine();
                while ((target.length() == 0) || (dictionaryManagement.dictionaryLookup(target) == null)) {
                    if (target.length() == 0) {
                        System.out.println("Tu can sua khong the trong!!");
                    } else {
                        System.out.println("Can't find the word you are looking for");
                    }
                    System.out.print("Nhap lai: ");
                    target = input.nextLine();
                }
                dictionaryManagement.deleteWord(target);
                System.out.println("\nDONE!!\n");
                BacktoMenu(index);
                break;
            case 7:
                System.out.print("Ban muon in ra file??(Y/N): ");
                String yn = input.nextLine();
                if (yn.equals("Y") || yn.equals("n")) {
                    dictionaryManagement.dictionaryExportToFile();
                    System.out.println("\nDONE!!\n");
                }
                BacktoMenu(index);
                break;
            case 8:
                dictionaryCommandline.showAllWords();
                System.out.println("\nDONE!!\n");
                BacktoMenu(index);
                break;
            case 9:
                dictionaryCommandline.dictionaryBasic();
                System.out.println("\nDONE!!\n");
                BacktoMenu(index);
                break;
            case 10:
                System.out.print("Nhap tu can tra: ");
                target = input.nextLine();
                dictionaryCommandline.dictionaryAdvanced(target);
                System.out.println("\nDONE!!\n");
                BacktoMenu(index);
                break;
            case 11:
//                System.out.print("Nhap tu can tra: ");
//                target = input.nextLine();
//                ArrayList<String > listTarget = new ArrayList<>();
//
//                for (Word word : Dictionary.words) {
//                    listTarget.add(word.getWord_target());
//                }
//                ArrayList<String> listResult = (ArrayList<String>)DictionaryCommandline.dictionarySearcher((ObservableList) listTarget, target);
//                for (String result : listResult) {
//                    System.out.println(result);
//                }
            default: break;
        }
    }
    public static void BacktoMenu(int index) {
        System.out.print("Tiep Tuc??(Y/N): ");
        String yn = input.nextLine();
        if (yn.equals("Y") || yn.equals("y")) {
            Menu(index);
        }
        System.out.print("Back to menu?(Y/N): ");
        if (yn.equals("Y") || yn.equals("y")) {
            TestCommandline();
        }
    }
}

