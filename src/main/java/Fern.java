import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Fern {
    public static void main(String[] args) {
        String name = ",------.  \n"
                + "|  .---',---. ,--.--.,--,--,  \n"
                + "|  `--,| .-. :|  .--'|      \\ \n"
                + "|  |`  \\   --.|  |   |  ||  | \n"
                + "`--'    `----'`--'   `--''--' \n";

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh.mm a");
        String line = "--------------------"
                + LocalDateTime.now().format(timeFormatter)
                + "---------------------";

        String[] storage = new String[100];
        int counter = 0;

        System.out.println(line);
        System.out.println("Hii, i am\n" + name + "Whats up?\n" );
        Scanner scanner = new Scanner(System.in);
        String userInput = "";
        while(true) {
            System.out.print("You: ");
            userInput = scanner.nextLine();
            if (userInput.equalsIgnoreCase("bye")){
                break;
            } else if (userInput.equalsIgnoreCase("list")) {
                printList(storage, counter);
            } else {
                storage[counter] = userInput;
                System.out.println("Fern: Added item (" + userInput + ")");
                if (++counter == 100) {
                    counter = 0;
                }
            }
        }
        System.out.println("Fern: Byebye~~");
        System.out.println(line);
        scanner.close();
    }
    private static void printList(String[] list,int count) {
        for(int i = 0; i < count; i++){
            System.out.println(i + 1 + ". " + list[i]);
        }
    }
}
