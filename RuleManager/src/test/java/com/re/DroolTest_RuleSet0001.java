package com.re;

import com.re.dm.DataModel;
import com.re.dm.MetaMap;
import com.re.dm.VarType;
import com.re.dm.Variable;
import com.re.dm.vo.VariableVo;
import com.re.util.DroolUtil;
import com.re.util.DroolsRuleLog;
import com.re.util.ExecuteVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.crypto.Data;
import java.util.*;

/*@RunWith(SpringRunner.class)
@SpringBootTest*/
public class DroolTest_RuleSet0001 {

    @Test
    public void testRuleSet0001_1(){
        String custAge = "1";
        String cashStrgyRslt = "";
        String expect_cashStrgyRslt = "t01";
        String agendaGroup = "ruleSet_1_v1.00";
        String drlPath = "src/test/resources/rule/ruleSet_001.drl";
        Map<String, Object> dataMap = getDataMap(custAge,cashStrgyRslt);
        Optional<DroolsRuleLog> droolsRuleLog = DroolUtil.executeDrl(dataMap,getMetaMap(),drlPath,null,agendaGroup);
        System.out.println(droolsRuleLog.get());
        assert droolsRuleLog.get().getHitCount() == 1;
        List<ExecuteVo> executeVos = (List<ExecuteVo>) droolsRuleLog.get().getLogs().get(0).get("rhs");
        assert executeVos.size()>0 && executeVos.get(0).getValue().equals(expect_cashStrgyRslt);
    }
    @Test
    public void testRuleSet0001_2(){
        String custAge = "3";
        String cashStrgyRslt = "";
        String expect_cashStrgyRslt = "t02";
        String agendaGroup = "ruleSet_1_v1.00";
        String drlPath = "src/test/resources/rule/ruleSet_001.drl";
        Map<String, Object> dataMap = getDataMap(custAge,cashStrgyRslt);
        Optional<DroolsRuleLog> droolsRuleLog = DroolUtil.executeDrl(dataMap,getMetaMap(),drlPath,null,agendaGroup);
        System.out.println(droolsRuleLog.get());
        assert droolsRuleLog.get().getHitCount() == 1;
        List<ExecuteVo> executeVos = (List<ExecuteVo>) droolsRuleLog.get().getLogs().get(0).get("rhs");
        assert executeVos.size()>0 && executeVos.get(0).getValue().equals(expect_cashStrgyRslt);
    }

    @Test
    public void testRuleSet0001_3(){
        String custAge = "6";
        String cashStrgyRslt = "";
        String expect_cashStrgyRslt = "t03";
        String agendaGroup = "ruleSet_1_v1.00";
        String drlPath = "src/test/resources/rule/ruleSet_001.drl";
        Map<String, Object> dataMap = getDataMap(custAge,cashStrgyRslt);
        Optional<DroolsRuleLog> droolsRuleLog = DroolUtil.executeDrl(dataMap,getMetaMap(),drlPath,null,agendaGroup);
        System.out.println(droolsRuleLog.get());
        assert droolsRuleLog.get().getHitCount() == 1;
        List<ExecuteVo> executeVos = (List<ExecuteVo>) droolsRuleLog.get().getLogs().get(0).get("rhs");
        assert executeVos.size()>0 && executeVos.get(0).getValue().equals(expect_cashStrgyRslt);
    }

    public MetaMap getMetaMap(){
        //input_keys.cust_age    output_keys.cash_strgy_rslt
            List<DataModel> dataModelList = new LinkedList<>();
            List<Variable> variableList = new LinkedList<>();
            DataModel dataModel = new DataModel("dm001","input_keys","input_keys","IN");
            Variable variable = new Variable("var1-001","cust_age","cust_age","dm001", VarType.INTEGER);
            variableList.add(variable);
            dataModel.setVariables(variableList);
        dataModelList.add(dataModel);


        List<Variable> variableList2 = new LinkedList<>();
        DataModel dataModel2 = new DataModel("dm002","output_keys","output_keys","OUT");
        Variable variable2 = new Variable("var2-001","cash_strgy_rslt","cash_strgy_rslt","dm002", VarType.STRING);
        variableList2.add(variable2);
        dataModel2.setVariables(variableList2);
        dataModelList.add(dataModel2);
        return new MetaMap(dataModelList);
    }

    public Map<String,Object> getDataMap(String val1,String val2){
       Map<String,Object> dataMap = new HashMap<>();
       Map<String,Object> inputKeyMap = new HashMap<>();
       inputKeyMap.put("cust_age",val1);
       dataMap.put("input_keys",inputKeyMap);

        Map<String,Object> outputKeyMap = new HashMap<>();
        outputKeyMap.put("cash_strgy_rslt",val2);
        dataMap.put("output_keys",outputKeyMap);
        return dataMap;
    }

}
