package com.re.dm;

import lombok.Data;

import java.util.List;
import java.util.Optional;

@Data
public class Domain {
    private String id;
    private String projectId;
    private String varId;
    private String name;
    private String zhName;
    private VarType varType;

    private List<DomainValue> domainValues;

    public Optional<List<DomainValue>> getDomainValues() {
        return Optional.of(this.domainValues);
    }
}
