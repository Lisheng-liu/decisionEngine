package com.re.dm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Variable {
private String id;
private String name;
private String zhName;
private String dmId;
private VarType varType;
private String numberRange;//[] {} [} {]
private String dateFormat;//yyyy-MM-dd  yyyy-MM-dd HH:mm:SS


    private List<Domain> domainList;

    public Variable(String id,String name, String zhName, String dmId, VarType varType) {
        this.name = name;
        this.zhName = zhName;
        this.dmId = dmId;
        this.varType = varType;
    }

    public Optional<List<Domain>> getDomains(){
        return Optional.ofNullable(this.domainList);
    }
}
