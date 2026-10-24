package project;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        DoubleLinkList<Task> tasks = new DoubleLinkList<>();
        LLQueue<Task> priorityQueue = new LLQueue<>();

        Scanner input = new Scanner(System.in);
        System.out.println("==== Task Management System ====");

        while (true) {
            System.out.println("------------------------------------------");
            System.out.println("\n1. add a new task");
            System.out.println("2. Update task priority ");
            System.out.println("3. Update task completion status ");
            System.out.println("4. Delete task ");
            System.out.println("5. Find task status ");
            System.out.println("6. Display completion rate ");
            System.out.println("7. Display high priority tasks reminder ");
            System.out.println("8. Display tasks sorted by priority ");
            System.out.println("9. Clear comleted tasks");
            System.out.println("0. Exit");
            
            System.out.print("\nEnter your choice:");
            int choice = input.nextInt();
            System.out.println("------------------------------------------");

            switch (choice) {
                case 0:
                    System.out.println("program finished.");
                    return;
                case 1:
                    input.nextLine();
                    addTask(input, tasks, priorityQueue);
                    break;
                case 2:
                    input.nextLine();
                    updateTaskPriority(input, priorityQueue);
                    break;
                case 3:
                    input.nextLine();
                    updateCompletionStatus(input, priorityQueue);
                    break;
                case 4:
                    input.nextLine();
                    System.out.print("which task do you want to delete? Enter the title: ");
                    String deletedTask = input.nextLine();
                    tasks.deleteTask(deletedTask);
                    break;

                case 5:
                    input.nextLine();
                    System.out.print("Enter task title to check its status:");
                    String findT = input.nextLine();
                    tasks.findTaskStatus(findT);
                    break;
                case 6:
                    input.nextLine();
                    displayCompletionRate(input, tasks);
                    break;
                case 7:
                    input.nextLine();
                    tasks.displayHighPriorityTasksReminder();
                    break;
                case 8:
                    input.nextLine();
                    priorityQueue.printByPriority();
                    break;
                case 9:
                    input.nextLine();
                    priorityQueue.clearCompleted();
                    System.out.println("Cleared completed tasks seccessfully!");
                    break;
                default:
                    System.out.println("Invalid Choice. Try again.");
                    continue;
            }

        }
    }

    //used in case1
    public static void addTask(Scanner input, DoubleLinkList<Task> tasks, LLQueue<Task> priorityQueue) {

        System.out.print("\nEnter task title: ");
        String title = input.nextLine();

        System.out.print("Enter task priority (1=High, 2=Medium, 3= low): ");
        int priority = input.nextInt();

        System.out.print("Is the task completed? (true/ false): ");
        boolean isCompleted = input.nextBoolean();

        input.nextLine();
        System.out.print("Enter task due date (dd-MM ) or leave it empty: ");
        String dueDate = input.nextLine();

        Task newTask = new Task(title, priority, isCompleted, dueDate);
        tasks.addTask(newTask);
        priorityQueue.enqueue(newTask);

        System.out.println("\nTask added seccessfully!");
    }

    //used in case2
    public static void updateTaskPriority(Scanner input, LLQueue<Task> priorityQueue) {
        if (priorityQueue.isEmpty()) {
            System.out.println("Queue is empty. No tasks to update.");
            return;
        }

        System.out.print("Enter task title to update pririty: ");
        String title = input.nextLine();

        System.out.print("Enter new priority (1=High, 2=Medium, 3= low): ");
        int priority = input.nextInt();

        boolean P = priorityQueue.updatePriority(title, priority);

        System.out.println(P ? "Updated" : "Task is not found in queue.");
    }

    //used in case3
    public static void updateCompletionStatus(Scanner input, LLQueue<Task> Queue) {
        if (Queue.isEmpty()) {
            System.out.println("Queue is empty. No tasks to update.");
            return;
        }

        System.out.print("Enter task title to update completion: ");
        String title = input.nextLine();

        System.out.print("Is the task completed? (1=Yes, 0=No) ");
        boolean completed = input.nextInt() == 1;

        boolean updatedC = Queue.updateCompletion(title, completed);

        if (updatedC) {
            System.out.println("Task Completion Status is updated!");
        } else {
            System.out.println("Task is not founded.");
        }
    }

    //used in case6
    public static void displayCompletionRate(Scanner input, DoubleLinkList<Task> tasks) {

        System.out.print("\nEnter your current prograss (0-100): ");
        double progress = input.nextDouble();

        double rate = tasks.getCompletionRate();
        System.out.printf("Your entered progress: %.2f%%%n", progress);
        System.out.printf("Overall your tasks completion rate: %.2f%%%n", rate);

        if (progress > rate) {
            System.out.println("You're ahead of the system average!");
        } else if (progress < rate) {
            System.out.println("System completion is ahead of your progress. Keep going!");
        } else {
            System.out.println("You're perfectly in sync with the system average!");
        }
    }

}
