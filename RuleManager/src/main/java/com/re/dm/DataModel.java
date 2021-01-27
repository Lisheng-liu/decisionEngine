package com.re.dm;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Data

public class DataModel {
    private String id;
    private String name;
    private String zhName;
    private String paramType;//IN OUT OTHER
    private String comment;
    private String projectId;


    private List<Variable> variables;

    public DataModel(String id, String name, String zhName, String paramType) {
        this.id = id;
        this.name = name;
        this.zhName = zhName;
        this.paramType = paramType;
    }

    public Optional<List<Variable>> getVariables() {
        return Optional.ofNullable(this.variables);
    }
}
