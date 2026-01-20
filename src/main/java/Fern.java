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
        System.out.println(line);
        System.out.println("Hii, i am\n" + name + "Whats up?\n" );
        Scanner scanner = new Scanner(System.in);
        String userInput = "";
        while(!userInput.equals("bye")) {
            System.out.print("You: ");
            userInput = scanner.nextLine();
            System.out.println("Fern: " + userInput);
        }
        System.out.println("Byebye");
        System.out.println(line);
        scanner.close();
    }
}
