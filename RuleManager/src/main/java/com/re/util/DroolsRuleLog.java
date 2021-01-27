package com.re.util;

import lombok.Data;
import org.drools.core.spi.KnowledgeHelper;

import java.util.*;

@Data
public class DroolsRuleLog {


    private String nodeId;
    private String nodeName;

    private Long hitCount;

    private List<Map<String,Object>> logs= new LinkedList<>();

    private RuleHelper ruleHelper;

    public void addLogs(String type, String objName, KnowledgeHelper drools ,String... args){
        hitCount++;
        Map<String,Object> lhsMap = getLhs(type,drools,args);
         List<ExecuteVo> rhsList = getRhs(type,drools,args);
        String groupName = drools.getRule().getAgendaGroup();
        String ruleName = drools.getRule().getName();
        int salience = drools.getRule().getSalience().getValue();
        saveLog(type,objName,groupName,ruleName,lhsMap,rhsList,salience,nodeName);
    }

    private  List<ExecuteVo>  getRhs(String type, KnowledgeHelper drools, String[] args) {
        return ruleHelper.getResultList();
    }

    private Map<String, Object> getLhs(String type, KnowledgeHelper drools, String[] args) {
            Map<String,Object> lhsMap = new LinkedHashMap<>();
            lhsMap.putAll(ruleHelper.getConditionMap());
            return lhsMap;
    }

    private void saveLog(String type, String objName, String groupName, String ruleName, Map<String, Object> lhsMap, List<ExecuteVo> rhsList, int salience, String nodeName) {
     Map<String,Object> map = new LinkedHashMap<>();
    map.put("type",type);
    map.put("objName",objName);
    map.put("groupName",groupName);
    map.put("ruleName",ruleName);
    map.put("salience",salience);
    map.put("lhs",lhsMap);
    map.put("rhs",rhsList);
    map.put("nodeName",nodeName);
    this.logs.add(map);
    }

    @Override
    public String toString() {
        return "DroolsRuleLog{" +
                "nodeId='" + nodeId + '\'' +
                ", nodeName='" + nodeName + '\'' +
                ", hitCount=" + hitCount +
                ", logs=" + logs +
                '}';
    }
}
