package project;

public class DoubleLinkList<E> {

    public class DNode<E> {

        protected E element;
        protected DNode<E> next, prev;

        public DNode(E e, DNode next, DNode prev) {
            element = e;
            this.next = next;
            this.prev = prev;
        }

        public E getElement() {
            return element;
        }

        public DNode<E> getNext() {
            return next;
        }

        public DNode<E> getPrev() {
            return prev;
        }

        public void setElement(E newElement) {
            element = newElement;
        }

        public void setNext(DNode newNext) {
            next = newNext;
        }

        public void setPrev(DNode newPrev) {
            prev = newPrev;
        }
    }

    protected DNode<E> header;
    protected DNode<E> trailer;
    protected int size;

    // constructs a new Empty list..
    public DoubleLinkList() {
        header = new DNode<E>(null, null, null);
        trailer = new DNode<E>(null, null, null);
        header.setNext(trailer);
        trailer.setPrev(header);
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public E First() {
        if (isEmpty()) {
            return null;
        }
        return header.getNext().getElement();
    }

    public E Last() {
        if (isEmpty()) {
            return null;
        }
        return trailer.getPrev().getElement();
    }

    public void addFirst(E e) {
        addBetween(e, header, header.getNext());
    }

    public void addLast(E e) {
        addBetween(e, trailer.getPrev(), trailer);
    }

    public void addBetween(E e, DNode<E> predecessor, DNode<E> successor) {
        DNode<E> newNode = new DNode<>(e, successor, predecessor);
        predecessor.setNext(newNode);
        successor.setPrev(newNode);
        size++;
    }

    public E remove(DNode<E> node) {
        DNode<E> pre = node.getPrev();
        DNode<E> post = node.getNext();
        pre.setNext(post);
        post.setPrev(pre);
        size--;
        return node.getElement();
    }

    public E removeFirst() {
        if (this.isEmpty()) {
            return null;
        }
        return this.remove(header.getNext());
    }

    public E removeLast() {
        if (this.isEmpty()) {
            return null;
        }
        return this.remove(trailer.getPrev());
    }

    //------------------------------------------------------------

    public void printList() {
        DNode<E> current = header.getNext();
        while (current != trailer) {
            System.out.print(current.getElement() + " ");
            current = current.getNext();
        }
        System.out.println();
    }

    public boolean addTask(Task task) {
        if (task == null) {
            return false;
        }

        E e = (E) task;
        addLast(e);
        return true;
    }

    public void deleteTask(String title) {
        if (title == null) {
            return;
        }
        DNode<E> current = header.getNext();
        while (current != trailer) {
            E elem = current.getElement();
            if (elem instanceof Task) {
                Task t = (Task) elem;
                if (title.equals(t.getTitle())) {
                    remove(current);
                    return;
                }
            }
            current = current.getNext();
        }
    }

    //Method 1: search for a specific task 
    public void findTaskStatus(String title) {
        if (title == null) {
            System.out.println("Invalid title.");
            return;
        }
        DNode<E> current = header.getNext();
        while (current != trailer) {
            E elem = current.getElement();
            if (elem instanceof Task) {
                Task t = (Task) elem;
                if (title.equals(t.getTitle())) {
                    System.out.print("Task \"" + title + "\" isCompleted = " + t.isCompleted());
                    return;
                }
            }
            current = current.getNext();
        }
        System.out.println("Task \"" + title + "\" not found.");
    }

    //Method 2: calculate the percentage of complated task
    public double getCompletionRate() {
        if (isEmpty()) {
            return 0.0;
        }

        DNode<Task> current = (DNode<Task>) header.getNext();
        int total = 0;
        int completed = 0;

        while (current != trailer) {
            Task t = current.getElement();
            total++;
            if (t.isCompleted()) {
                completed++;
            }
            current = current.getNext();
        }

        return (completed * 100.0) / total;
    }

    //  Method 3: print & remineder of high priority tasks
    public void displayHighPriorityTasksReminder() {
        if (isEmpty()) {
            System.out.println("No high priority tasks.");
            return;
        }

        System.out.println("=== High Priority Tasks Reminder ===");
        boolean any = false;
        DNode<E> current = header.getNext();
        while (current != trailer) {
            E el = current.getElement();
            if (el instanceof Task) {
                Task t = (Task) el;

                if (t.getPriority() == 1 && !t.isCompleted()) {
                    any = true;
                    String due = t.getDueDate();
                    if (due == null || due.isEmpty()) {
                        due = "-";
                    }
                    System.out.println("The Task:️  " + t.getTitle() + " | Due: " + due);
                }
            }
            current = current.getNext();
        }
    }
}
