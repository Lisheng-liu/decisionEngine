package com.re.util;

import com.re.dm.MetaMap;
import com.re.dm.VarType;
import com.re.dm.vo.VariableVo;
import com.re.exception.BusinessException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Data
@Slf4j
public class RuleHelper {

    private String nodeName;
    private MetaMap metaMap;

    private List<ExecuteVo> resultList = new ArrayList<>();

    private Map<String,String> conditionMap = new LinkedHashMap<>();

    public RuleHelper(MetaMap metaMap) {
        this.metaMap = metaMap;
    }


    /**
     *
     * @param map:insertMap
     * @param path:dmName.varName
     * @param value: value
     */
    public void setValue(Map map,String path,Object value) {
        ExecuteVo executeVo = new ExecuteVo();
        executeVo.setName(path);
        executeVo.setValue(value.toString());
        resultList.add(executeVo);
        String[] varNames = path.split("\\.");
        if(varNames.length != 2){
            log.error("setvalue 格式error,path={} ",path);
            throw new BusinessException("setvalue 格式error,path= "+path);
        }

       /* Object parent = null;
        Map<String,Object> parentMap = null;
        parentMap = map;
        for(int i = 0; i< varNames.length-1;i++){
            parent = parentMap.get(varNames[i]);
            if(parent == null){
                parent = new HashMap<>();
                parentMap.put(varNames[i],parent);
            }
            parentMap = (Map<String, Object>) parent;
        }
        VariableVo variableVo = getVariableVo(path);
        Object targetValue = transromeValue(variableVo.getVarType(),value);
        parentMap.put(varNames[varNames.length-1],targetValue);*/

        VariableVo variableVo = this.metaMap.getMetaMap().get(varNames[0]).get(varNames[1]);
        Object targetValue = transromeValue(variableVo.getVarType(),value);
        Map<String,Object> childMap = (Map<String, Object>) map.get(varNames[0]);
        childMap.put(varNames[1],targetValue);

    }

    private Object transromeValue(VarType varType, Object value) {
        Object valueStr = String.valueOf(value);
        switch (varType){
            case INTEGER:
                valueStr = Integer.parseInt(String.valueOf(value));
                break;
            case LONG:
                valueStr = Long.parseLong(String.valueOf(value));
                break;
            case DOUBLE:
                valueStr = Double.valueOf(String.valueOf(value));
                break;
            case FLOAT:
                valueStr = Float.valueOf(String.valueOf(value));
                break;
            case BOOLEAN:
                valueStr = "true".equals(String.valueOf(value).toLowerCase())||"1".equals(String.valueOf(value).toLowerCase());
                break;
            default:
                valueStr  = valueStr;

        }
        return valueStr;
    }

    /**
     *
     * @param map:insertMap
     * @param path:dmName.varName
     * @return
     */
    public Object getValue(Map map,String path){
        String[] varNames = path.split("\\.");
        if(varNames.length != 2){
            log.error("setvalue 格式error,path={} ",path);
            throw new BusinessException("setvalue 格式error,path= "+path);
        }
        Map<String,Object> childMap = (Map<String, Object>) map.get(varNames[0]);
        Object value = childMap.get(varNames[1]);
        this.conditionMap.put(path,String.valueOf(value));
        VariableVo variableVo = this.metaMap.getMetaMap().get(varNames[0]).get(varNames[1]);
        Object targetValue = transromeValue(variableVo.getVarType(),value);
        return targetValue;
    }
}
