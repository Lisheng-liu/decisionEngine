package com.re.dm;

public enum VarType {

    STRING("string"),INTEGER("integer"),LONG("long"),FLOAT("float"),DOUBLE("double"),BOOLEAN("boolean"),DATE("date"),ENUM("enum");

    private String varType;
    private VarType(String varType){
        this.varType = varType;
    }
    public String getVarType(){
        return this.varType;
    }
}
