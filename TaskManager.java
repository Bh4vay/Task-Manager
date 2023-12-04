import java.io.*;
import java.util.*;

public class TaskManager {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        while (true) {
            System.out.println("----------Task Manager Menu----------");
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Mark any Task as Completed");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = s.nextInt();
            s.nextLine();

            switch (choice) {
                case 1:
                    addTask();
                    break;
                case 2:
                    viewTasks();
                    break;
                case 3:
                    markCompleted();
                    break;
                case 4:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void addTask() {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("tasks.txt", true))) {
        int num;
        Scanner s = new Scanner(System.in);
        System.out.print("Enter the number of task(s) you want to add? ");
        num = s.nextInt();
        s.nextLine();  

        for(int i = 0; i < num; i++) {
            System.out.printf("Enter the task description for task %d: ", i + 1);
            String taskinfo = s.nextLine();

            writer.write(taskinfo);
            writer.newLine();
        }
        System.out.println("Task(s) added successfully.");
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    public static void viewTasks() {
        try (BufferedReader br = new BufferedReader(new FileReader("tasks.txt"))) {
            String line;
            int taskNum = 1;

            while ((line = br.readLine()) != null) {
                System.out.println("Task " + taskNum + ": " + line);
                taskNum++;
            }

            if (taskNum == 1) {
                System.out.println("No tasks available.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void markCompleted() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("tasks.txt"));
            StringBuilder content = new StringBuilder();

            Scanner s = new Scanner(System.in);

            System.out.print("Enter the task number to be marked as completed: ");
            int taskNum = s.nextInt();
            s.nextLine();

            String line;
            int currentTaskNum = 1;
            boolean taskFound = false;

            while ((line = br.readLine()) != null) {
                if (currentTaskNum == taskNum) {
                    System.out.println("Task marked as completed: " + line);
                    taskFound = true;
                } else {
                    content.append(line).append("\n");
                }

                currentTaskNum++;
            }

            if (!taskFound) {
                System.out.println("Task is not there in the list.");
            }

            br.close();

            BufferedWriter writer = new BufferedWriter(new FileWriter("tasks.txt"));
            writer.write(content.toString());
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
