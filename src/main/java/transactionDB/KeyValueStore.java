package transactionDB;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class KeyValueStore {
    Map<Integer, String> globalStorage;
    Stack<Transaction> stack;

    public KeyValueStore() {
        globalStorage = new HashMap<>();
        stack = new Stack<>();
    }

    public void begin(){
        if(!stack.isEmpty())
            stack.push(new Transaction(new HashMap<>(stack.peek().localStorage)));
        else
            stack.push(new Transaction(new HashMap<>(globalStorage)));
    }

    private void end(){
        if(!stack.isEmpty()) {
            stack.pop();
            return;
        }
        throw new RuntimeException("No active Transaction");
    }

    public void commit(){
        if (!stack.isEmpty()) {
            globalStorage = stack.pop().localStorage;
            return;
        }
        throw new RuntimeException("No active Transaction");
    }

    public void rollback() {
        if (!stack.isEmpty()) {
            Transaction transaction = stack.peek();
            transaction.localStorage.clear();
            end();
            return;
        }
        throw new RuntimeException("No active Transaction");
    }

    public void set(int key, String value) {
        if (!stack.isEmpty()) {
            Transaction transaction = stack.peek();
            transaction.localStorage.put(key, value);
            return;
        }
        globalStorage.put(key, value);
    }

    public String get(int key) {
        if (!stack.isEmpty()) {
            Transaction transaction = stack.peek();
            return transaction.localStorage.get(key);
        }
        return globalStorage.get(key);
    }

    public void unSet(int key) {
        if (!stack.isEmpty()) {
            Transaction transaction = stack.peek();
            transaction.localStorage.remove(key);
            return;
        }
        globalStorage.remove(key);
    }
}
