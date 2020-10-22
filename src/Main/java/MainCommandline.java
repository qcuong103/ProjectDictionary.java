import java.util.Scanner;

public class MainCommandline {
    static DictionaryManagement dictionaryManagement = new DictionaryManagement();
    static DictionaryCommandline dictionaryCommandline = new DictionaryCommandline();
    static Scanner input = new Scanner(System.in);
    static boolean isFalse = true;

    public static void main(String[] args) {
        dictionaryManagement.insertFromCommandline();
        MainCommandline.TestCommandline();
    }

    public static void TestCommandline() {
        isFalse = true;
        System.out.println("\n\t\t Menu");
        System.out.println("1. Insert From Commandline\n2. Insert From File\n3. Dictionary Lookup\n4. Add Word\n5. Edit Word\n6. Delete Word");
        System.out.println("7. Dictionary Export To File\n8. Show All Words\n9. Dictionary Basic\n10. Dictionary Advanced\n");
        System.out.print("Nhap thao tac: ");
        int index = input.nextInt();
        Menu(index);
    }

    public static void Menu(int index) {
        String target, explain;
        if (isFalse) {
            input.nextLine();
            isFalse = false;
        }
        switch (index) {
            case 1:
                System.out.println("\n\t\t Insert From Commandline");
                dictionaryManagement.insertFromCommandline();
                System.out.println("\nDONE!!!\n");
                BacktoMenu(index);
                break;
            case 2:
                System.out.println("\n\t\t Insert From File");
                dictionaryManagement.insertFromFile();
                System.out.println("\nDONE!!\n");
                BacktoMenu(index);
                break;
            case 3:
                System.out.println("\n\t\t Dictionary Lookup");
                System.out.print("Nhap tu can Tra: ");
                target = input.nextLine();
                if (dictionaryManagement.dictionaryLookup(target) != null) {
                    System.out.println("Nghia cua tu can tra la: \n" + (dictionaryManagement.dictionaryLookup(target)));
                    System.out.println("\nDone!!!\n");
                } else {
                    System.out.println("Can't find the word you are looking for");
                }
                BacktoMenu(index);
                break;
            case 4:
                System.out.println("\n\t\t Add Word");
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
                System.out.println("\n\t\t Edit Word");
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
                System.out.println("\n\t\t Delete Word");
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
                System.out.println("\n\t\t Dictionary Export To File");
                System.out.print("Ban muon in ra file??(Y/N): ");
                String yn = input.nextLine();
                if (yn.equals("Y") || yn.equals("n")) {
                    dictionaryManagement.dictionaryExportToFile();
                    System.out.println("\nDONE!!\n");
                }
                BacktoMenu(index);
                break;
            case 8:
                System.out.println("\n\t\t Show All Words");
                dictionaryCommandline.showAllWords();
                System.out.println("\nDONE!!\n");
                BacktoMenu(index);
                break;
            case 9:
                System.out.println("\n\t\t Dictionary Basic");
                dictionaryCommandline.dictionaryBasic();
                System.out.println("\nDONE!!\n");
                BacktoMenu(index);
                break;
            case 10:
                System.out.println("\n\t\t Dictionary Advanced");
                System.out.print("Nhap tu can tra: ");
                target = input.nextLine();
                dictionaryCommandline.dictionaryAdvanced(target);
                System.out.println("\nDONE!!\n");
                BacktoMenu(index);
                break;
            default: break;
        }
    }
    public static void BacktoMenu(int index) {
        if (isFalse) {
            input.nextLine();
            isFalse = false;
        }
        System.out.print("Tiep Tuc??(Y/N): ");

        String yn = input.nextLine();
        if (yn.equals("Y") || yn.equals("y")) {
            Menu(index);
        } else {
            System.out.print("Back to menu?(Y/N): ");
            yn = input.nextLine();
            if (yn.equals("Y") || yn.equals("y")) {
                TestCommandline();
            }
        }
    }
}

