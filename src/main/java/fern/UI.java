package fern;

/**
 * Class for handling all the UI elements like greetings
 */
public class UI {
    private final static String LINE = "-----------------------------------------";
    private final static String NAME = ",------.  \n"
            + "|  .---',---. ,--.--.,--,--,  \n"
            + "|  `--,| .-. :|  .--'|      \\ \n"
            + "|  |`  \\   --.|  |   |  ||  | \n"
            + "`--'    `----'`--'   `--''--' \n";
    /**
     * Print the list of tasks
     **/
    public static void printList(TaskList tasks) {
        say("");
        for(int i = 0; i < tasks.size(); i++) {
            int idx = i + 1;
            System.out.println((idx < 10 ? ("0" + idx) : idx) + ". "
                    + tasks.get(i).toString());
        }
    }

    /**
     * Make fern speak input
     **/
    public static void say(String msg) {
        System.out.println("Fern: " + msg);
    }

    /**
     * Make fern say greetings
     **/
    public static void start() {
        System.out.println(LINE);
        say("Hii, i am\n" + NAME + "Whats up?");
    }

    /**
     * Make fern say goodbye
     **/
    protected static void end() {
        say("Byebye~~");
        System.out.println(LINE);
    }
}
