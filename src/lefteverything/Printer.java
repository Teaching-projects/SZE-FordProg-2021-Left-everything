package lefteverything;

import java.util.List;
import java.util.Map;

public class Printer {

    public void printResult(Map<String, List<String>> result){
        for (String key : result.keySet()){
            StringBuilder sb = new StringBuilder();
            List<String> rules = result.get(key);
            sb.append(key + " ::= ");
            for (int i = 0; i < rules.size()-1; i++){
                sb.append(rules.get(i) + " | ");
            }
            sb.append(rules.get(rules.size()-1));
            System.out.println(sb.toString());
        }
    }
}
