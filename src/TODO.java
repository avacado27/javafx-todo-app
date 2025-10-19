import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.ArrayList;
//import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
import java.lang.reflect.Type;


public class TODO {

    private List<Task> todos;
    private static final String FILE_PATH = "todos.json";
    private Gson gson = new Gson();

    public TODO() {
        this.todos = new ArrayList<>();
    }

    public int getSize() {
        return todos.size();
    }

    public void add(String title) {
        todos.add(new Task(title));
    }

    public void remove(String title) {

        for (int i = todos.size() - 1; i >= 0; i--) {
            if (todos.get(i).getTitle().equalsIgnoreCase(title)) {
                todos.remove(i);
            }
        }
    }

    public void changePriority(Task task, int newPriority) {
        if (todos.contains(task) && newPriority >= 0 && newPriority < todos.size()) {
            todos.remove(task);
            todos.add(newPriority, task);
        }
    }

    public String displayTODO() {
        return todos.toString();
    }

    public List<Task> getTasks() {
        return todos;
    }

    public void saveTODOS() {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(todos, writer);
            System.out.println("✅ Saved tasks to " + FILE_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadTasks() {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type taskListType = new TypeToken<ArrayList<Task>>() {}.getType();
            todos = gson.fromJson(reader, taskListType);
            if (todos == null) {
                todos = new ArrayList<>();
            }
        } catch (IOException e) {
            System.out.println("No saved tasks, starting fresh.");
            todos = new ArrayList<>();
        }
    }
}
