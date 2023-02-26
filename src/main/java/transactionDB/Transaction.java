package transactionDB;

import java.util.HashMap;
import java.util.Map;

public class Transaction {
    Map<Integer, String> localStorage;

    public Transaction(Map<Integer, String> gs) {
        localStorage = gs;
    }
}
