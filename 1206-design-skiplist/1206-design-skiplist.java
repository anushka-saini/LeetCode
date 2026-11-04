
import java.util.*;

class Skiplist {

    class Node {
        int val;
        Node[] next;

        Node(int val, int level) {
            this.val = val;
            next = new Node[level + 1];
        }
    }

    private static final int MAX_LEVEL = 16;
    private Node head;
    private int currentLevel;
    private Random random;

    public Skiplist() {
        head = new Node(-1, MAX_LEVEL);
        currentLevel = 0;
        random = new Random();
    }

    private int randomLevel() {
        int level = 0;

        while (level < MAX_LEVEL && random.nextBoolean()) {
            level++;
        }

        return level;
    }

    public boolean search(int target) {
        Node curr = head;

        for (int i = currentLevel; i >= 0; i--) {
            while (curr.next[i] != null &&
                   curr.next[i].val < target) {
                curr = curr.next[i];
            }
        }

        curr = curr.next[0];

        return curr != null && curr.val == target;
    }

    public void add(int num) {
        Node[] update = new Node[MAX_LEVEL + 1];
        Node curr = head;

        for (int i = currentLevel; i >= 0; i--) {
            while (curr.next[i] != null &&
                   curr.next[i].val < num) {
                curr = curr.next[i];
            }

            update[i] = curr;
        }

        int level = randomLevel();

        if (level > currentLevel) {
            for (int i = currentLevel + 1; i <= level; i++) {
                update[i] = head;
            }

            currentLevel = level;
        }

        Node newNode = new Node(num, level);

        for (int i = 0; i <= level; i++) {
            newNode.next[i] = update[i].next[i];
            update[i].next[i] = newNode;
        }
    }

    public boolean erase(int num) {
        Node[] update = new Node[MAX_LEVEL + 1];
        Node curr = head;

        for (int i = currentLevel; i >= 0; i--) {
            while (curr.next[i] != null &&
                   curr.next[i].val < num) {
                curr = curr.next[i];
            }

            update[i] = curr;
        }

        curr = curr.next[0];

        if (curr == null || curr.val != num) {
            return false;
        }

        for (int i = 0; i <= currentLevel; i++) {
            if (update[i].next[i] != curr) {
                break;
            }

            update[i].next[i] = curr.next[i];
        }

        while (currentLevel > 0 && head.next[currentLevel] == null) {
            currentLevel--;
        }

        return true;
    }
}
