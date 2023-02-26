package yamlparser;

//Give below astring, which is similar to json, with multiple layers. Each next level will have two more empty spaces than the previous level.
//
// K1:V1
// K2:V2
// K3:
//   K31:V31
//   K32:
//     K321:V321
//      K322:V322
//    K33:V33
// K4:
//   K41:V41
//   K42:V42"
//we need to build a data structure.
//get(k1) returns v1
//get(k2) returns v2
//get(k3)(k31) returns v31.

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Main {
    @Test
    public void test1() {
        Parser parser = new Parser();
        String test =   "K1:V1\n" +
                "K2:V2\n" +
                "K3:\n" +
                "  K31:V31\n" +
                "  K32:\n" +
                "    K321:V321\n" +
                "    K322:V322\n" +
                "    K333:V333\n" +
                "K4:\n" +
                "  K41:V41\n" +
                "  K42:V42";
        Map<String, Object> res = parser.parse(test);
        Assert.assertEquals("V1", res.get("K1"));
        Assert.assertEquals("V31", ((Map<String, Object>)res.get("K3")).get("K31"));
    }
}

class Parser {
    public Map<String, Object> parse(String yaml) {
        Stack<Map<String, Object>> stack = new Stack<>();
        Map<String, Object> root = new HashMap<>();
        stack.push(root);
        int prevIdent = 0;
        for (String line : yaml.split("\n")) {
            int currIdent = line.lastIndexOf(" ")+1;
            while (prevIdent - currIdent >= 2) {
                stack.pop();
                prevIdent -= 2;
            }
            String[] lineSplit = line.trim().split(":");
            if (lineSplit.length == 1) {
                Map<String, Object> child = new HashMap<>();
                stack.peek().put(lineSplit[0], child);
                stack.push(child);
            } else {
                stack.peek().put(lineSplit[0], lineSplit[1]);
            }
            prevIdent = currIdent;
        }
        return root;
    }
}