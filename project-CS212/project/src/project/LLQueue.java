package project;

public class LLQueue<E> {

    static class Node<E> {

        private E element;
        private Node<E> next;

        public Node(E e, Node<E> n) {
            element = e;
            next = n;
        }

        public E getElement() {
            return element;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> n) {
            next = n;
        }

        public void setelement(E element) {
            this.element = element;
        }
    }

    protected Node<E> front;
    protected Node<E> rear;
    protected int size;

    public LLQueue() {
        front = null;
        rear = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public E first() {
        if (isEmpty()) {
            return null;
        }
        return front.getElement();
    }

    public E last() {
        if (isEmpty()) {
            return null;
        }
        return rear.getElement();
    }

    public void enqueue(E elem) {
        Node<E> newest = new Node<>(elem, null);
        if (isEmpty()) {
            front = newest;
        } else {
            rear.setNext(newest);
        }
        rear = newest;
        size++;
    }

    public E dequeue() {
        if (isEmpty()) {
            return null;
        }
        E answer = front.getElement();
        front = front.getNext();
        size--;
        if (size == 0) {
            rear = null;
        }
        return answer;
    }
//------------------------------------------------------------
    
    // Method 1: Update task priority
    public boolean updatePriority(String title, int newPriority) {
        if (title == null) {
            return false;
        }

        if (newPriority < 1) {
            newPriority = 1;
        }
        if (newPriority > 3) {
            newPriority = 3;
        }

        boolean updated = false;
        int s = size();

        for (int i = 0; i < s; i++) {
            E e = dequeue();
            if (e instanceof Task) {
                Task t = (Task) e;
                if (t.getTitle().equals(title)) {
                    t.setPriority(newPriority);
                    updated = true;
                }
            }
            enqueue(e);
        }

        return updated;
    }

    // Method 2: Update task completion status
    public boolean updateCompletion(String title, boolean completed) {
        if (title == null) {
            return false;
        }

        boolean updated = false;
        int s = size();

        for (int i = 0; i < s; i++) {
            E e = dequeue();
            if (e instanceof Task) {
                Task t = (Task) e;
                if (t.getTitle().equals(title)) {
                    t.setCompleted(completed);
                    updated = true;
                }
            }
            enqueue(e);
        }

        return updated;
    }

    // Method 3: Print tasks sorted by priority
    public void printByPriority() {
        if (isEmpty()) {
            System.out.println("No tasks in the queue.");
            return;
        }

        System.out.println("=== Tasks Sorted by Priority ===");
        int s = size();

        for (int p = 1; p <= 3; p++) { // 1=High, 2=Medium, 3=Low
            
            for (int i = 0; i < s; i++) {
                E e = dequeue();
                if (e instanceof Task) {
                    Task t = (Task) e;
                    if (t.getPriority() == p) {
                        System.out.println(t);
                    }
                }
                enqueue(e);
            }
        }
    }

    // Method 4: Remove all completed tasks while preserving order
    public void clearCompleted() {
        int s = size();
        for (int i = 0; i < s; i++) {
            E e = dequeue();
            boolean keep = true;
            if (e instanceof Task) {
                Task t = (Task) e;
                if (t.isCompleted()) {
                    keep = false;   
                }
            }
            if (keep) {
                enqueue(e);
            }
        }
    }

}
