package lefteverything;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeftRecursionAnalyzer {

    public Map<String, List<String>> replaceEverythingEverywhere(Map<String, List<String>> rules) {
        Map<String, List<String>> fullRuleMap = new HashMap<>();
        for (String key : rules.keySet()) {
            List<String> values = rules.get(key);
            List<String> allRules = new ArrayList<>();
            for (String rule : values) {
                for (String keys : rules.keySet()) {
                    if (!keys.equals(key) && rule.startsWith(keys)) {
                        for (String otherValues : rules.get(keys)) {
                            allRules.add(rule.replace(keys, otherValues));
                        }
                    }
                }
                allRules.add(rule);
            }
            fullRuleMap.put(key, allRules);
        }
        return fullRuleMap;
    }

    public Map<String, List<String>> findLeftRecursion(Map<String, List<String>> rules) {
        Map<String, List<String>> leftRecursiveRulesSigned = new HashMap<>();
        for (String key : rules.keySet()) {
            List<String> values = rules.get(key);
            List<String> recursiveRules = new ArrayList<>();
            List<String> nonRecursiveRules = new ArrayList<>();
            String recursiveKey = "";
            for (String rule : values) {
                if (rule.startsWith(key)) {
                    recursiveRules.add(rule.replace(key,"")+key+"'");
                    recursiveKey = key+"'";
                } else {
                        nonRecursiveRules.add(rule+key+"'");
                }
            }
            recursiveRules.add("$");
            leftRecursiveRulesSigned.put(recursiveKey,recursiveRules);
            leftRecursiveRulesSigned.put(key, nonRecursiveRules);
        }
        return leftRecursiveRulesSigned;
    }
}
