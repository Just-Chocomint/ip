package fern;

import java.util.ArrayList;

/**
 * Handles all UI elements like greetings and task display formatting.
 */
public class UI {
    /**
     * Prints all items in TaskList with numbered formatting.
     *
     * @param tasks the TaskList to display
     * @return formatted string of all tasks with numbers
     */
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
            return "You have nothing in your list";
        }
        return ret.toString();
    }

    /**
     * Prints all items in ArrayList with numbered formatting.
     *
     * @param tasks the ArrayList of tasks to display
     * @return formatted string of all tasks with numbers
     */
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
     * Returns Fern's goodbye message.
     *
     * @return goodbye message string
     */
    protected static String end() {
        return "Bye bye~~";
    }
}
