package com.re.util;

import com.re.dm.VarType;
import lombok.Data;

@Data
public class ExecuteVo {
    private String name;
    private String value;
    private String dmName;
    private String varName;
    private String varZhName;
    private VarType varType;
}
