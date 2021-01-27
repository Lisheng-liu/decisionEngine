package com.re.dm.vo;

import com.re.dm.Domain;
import com.re.dm.VarType;
import com.re.dm.Variable;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class VariableVo {
private String id;
private String name;
private String zhName;
private String dmId;
private VarType varType;
private String numberRange;//[] {} [} {]
private String dateFormat;//yyyy-MM-dd  yyyy-MM-dd HH:mm:SS


private Map<String,String> domainValueMap;

    public VariableVo(Variable variable) {
        this.id=variable.getId();
        this.name=variable.getName();
        this.zhName=variable.getZhName();
        this.dmId=variable.getDmId();
        this.varType=variable.getVarType();
        this.numberRange=variable.getNumberRange();
        this.dateFormat=variable.getDateFormat();
    }
}
