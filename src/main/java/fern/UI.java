package fern;

import java.util.ArrayList;

/**
 * Class for handling all the UI elements like greetings
 */
public class UI {
    /**
     * Print all items in TaskList
     **/
    public static String printList(TaskList tasks) {
        StringBuilder ret = new StringBuilder();
        for(int i = 0; i < tasks.size(); i++) {
            int idx = i + 1;
            ret.append((idx < 10 ? "0" + idx : idx))
                    .append(". ")
                    .append(tasks.get(i).toString())
                    .append("\n");
        }
        if (ret.isEmpty()) {
            return "You got nothing in list";
        }
        return ret.toString();
    }

    /**
     * Print all items in ArrayList
     **/
    public static String printList(ArrayList<Task> tasks) {
        StringBuilder ret = new StringBuilder();
        for(int i = 0; i < tasks.size(); i++) {
            int idx = i + 1;
            ret.append((idx < 10 ? "0" + idx : idx))
                    .append(". ")
                    .append(tasks.get(i).toString())
                    .append("\n");
        }
        return ret.toString();
    }

    /**
     * Make fern say goodbye
     **/
    protected static String end() {
        return "Bye bye~~";
    }
}
