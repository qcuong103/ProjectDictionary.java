import java.util.ArrayList;
import java.util.Scanner;

public class MainCommandline {
    static DictionaryManagement dictionaryManagement = new DictionaryManagement();
    static DictionaryCommandline dictionaryCommandline = new DictionaryCommandline();
    static Scanner input = new Scanner(System.in);
    static boolean isFalse = true;

    /**
     * Main Commandline.
     * @param args .
     */
    public static void main(String[] args) {
        dictionaryManagement.insertFromCommandline();
        MainCommandline.Menu();
    }

    /**
     * Menu Test.
     */
    public static void Menu() {
        isFalse = true;
        System.out.println("\n\t\t Menu");
        System.out.println("1. Insert From Commandline\n2. Insert From File\n3. Dictionary Lookup\n4. Add Word\n5. Edit Word\n6. Delete Word");
        System.out.println("7. Dictionary Export To File\n8. Show All Words\n9. Dictionary Basic\n10. Dictionary Advanced\n11. Dictionary Searcher");
        System.out.print("Nhập thao tác: ");
        int index = input.nextInt();
        MenuSwitch(index);
    }

    /**
     * Swith Item Test.
     * @param index .
     */
    public static void MenuSwitch(int index) {
        String target, explain;
        if (isFalse) {
            input.nextLine();
            isFalse = false;
        }
        switch (index) {
            case 1:
                System.out.println("\n\t\t Insert From Commandline");
                dictionaryManagement.insertFromCommandline();
                System.out.println("\nXONG!!!\n");
                BacktoMenu(index);
                break;
            case 2:
                System.out.println("\n\t\t Insert From File");
                dictionaryManagement.insertFromFile();
                System.out.println("\nXONG!!\n");
                BacktoMenu(index);
                break;
            case 3:
                System.out.println("\n\t\t Dictionary Lookup");
                System.out.print("Nhập từ cần tra cứu: ");
                target = input.nextLine();
                if (dictionaryManagement.dictionaryLookup(target).length() != 0) {
                    System.out.println("Nghĩa của từ cần tra cứu là: \n" + (dictionaryManagement.dictionaryLookup(target)));
                    System.out.println("\nXONG!!!\n");
                } else {
                    System.out.println("Không tìm thấy từ cần tìm!!!");
                }
                BacktoMenu(index);
                break;
            case 4:
                System.out.println("\n\t\t Add Word");
                System.out.print("Nhập từ cần thêm: ");
                target = input.nextLine();
                System.out.print("Nhập nghĩa: ");
                explain = input.nextLine();
                while ((target.length() == 0) || (explain.length() == 0)) {
                    if (target.length() == 0) {
                        System.out.println("Từ cần thêm không thể trống!!");
                        System.out.print("Nhập lại: ");
                        target = input.nextLine();
                    } else {
                        System.out.println("Nghĩa cần thêm không thể trống!!");
                        System.out.print("Nhập lại: ");
                        explain = input.nextLine();
                    }
                }
                dictionaryManagement.addWord(target, explain);
                System.out.println("\nXONG!!\n");
                BacktoMenu(index);
                break;
            case 5:
                System.out.println("\n\t\t Edit Word");
                System.out.print("Nhập từ cần sửa: ");
                String oldTarget = input.nextLine();
                while ((oldTarget.length() == 0) || (dictionaryManagement.dictionaryLookup(oldTarget).length() == 0)) {
                    if (oldTarget.length() == 0) {
                        System.out.println("Từ cần sửa không thể trống!!");
                    } else {
                        System.out.println("Không thể tìm thấy từ");
                    }
                    System.out.print("Nhập lại: ");
                    oldTarget = input.nextLine();
                }
                System.out.print("Nhập từ mới (không thay đổi thì để trống): ");
                target = input.nextLine();
                System.out.print("Nhập nghĩa mới (không thay đổi thì để trống): ");
                explain = input.nextLine();
                dictionaryManagement.editWord(oldTarget, target, explain);
                System.out.println("\nXONG!!\n");
                BacktoMenu(index);
                break;
            case 6:
                System.out.println("\n\t\t Delete Word");
                System.out.print("Nhập từ cần xóa: ");
                target = input.nextLine();
                while ((target.length() == 0) || (dictionaryManagement.dictionaryLookup(target).length() == 0)) {
                    if (target.length() == 0) {
                        System.out.println("Từ cần xóa không thể trống!!");
                    } else {
                        System.out.println("Không thể tìm thấy");
                    }
                    System.out.print("Nhập lại: ");
                    target = input.nextLine();
                }
                dictionaryManagement.deleteWord(target);
                System.out.println("\nXONG!!\n");
                BacktoMenu(index);
                break;
            case 7:
                System.out.println("\n\t\t Dictionary Export To File");
                System.out.print("Bạn muốn in ra file??(Y/N): ");
                String yn = input.nextLine();
                if (yn.equals("Y") || yn.equals("n")) {
                    dictionaryManagement.dictionaryExportToFile();
                    System.out.println("\nXONG!!\n");
                }
                BacktoMenu(index);
                break;
            case 8:
                System.out.println("\n\t\t Show All Words");
                dictionaryCommandline.showAllWords();
                System.out.println("\nXOng!!\n");
                BacktoMenu(index);
                break;
            case 9:
                System.out.println("\n\t\t Dictionary Basic");
                dictionaryCommandline.dictionaryBasic();
                System.out.println("\nXONG!!\n");
                BacktoMenu(index);
                break;
            case 10:
                System.out.println("\n\t\t Dictionary Advanced");
                System.out.print("Nhập từ cần tra cứu: ");
                target = input.nextLine();
                dictionaryCommandline.dictionaryAdvanced(target);
                System.out.println("\nXONG!!\n");
                BacktoMenu(index);
                break;
            case 11:
                System.out.println("\n\t\t Dictionary Searcher");
                System.out.println("Nhập từ cần gợi ý: ");
                target = input.nextLine();
                ArrayList<String> listTarget = new ArrayList<>();
                for (Word word : Dictionary.words) {
                    listTarget.add(word.getWord_target());
                }
                ArrayList<String> listResult = DictionaryCommandline.dictionarySearcher(listTarget, target);
                for (String result : listResult) {
                    System.out.println(result);
                }
                System.out.println("\nXONG!!\n");
                BacktoMenu(index);
                break;
            default: break;
        }
    }

    /**
     * Bach to Menu.
     * @param index .
     */
    public static void BacktoMenu(int index) {
        if (isFalse) {
            input.nextLine();
            isFalse = false;
        }
        System.out.print("Tiếp tục??(Y/N): ");

        String yn = input.nextLine();
        if (yn.equals("Y") || yn.equals("y")) {
            MenuSwitch(index);
        } else {
            System.out.print("Trở lại menu?(Y/N): ");
            yn = input.nextLine();
            if (yn.equals("Y") || yn.equals("y")) {
                Menu();
            }
        }
    }
}