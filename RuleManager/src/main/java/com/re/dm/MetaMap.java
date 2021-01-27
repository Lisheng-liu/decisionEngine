package com.re.dm;

import com.re.dm.vo.VariableVo;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Data
public class MetaMap {

    private Map<String, Map<String,VariableVo>> metaMap = new HashMap<>();

    public MetaMap(List<DataModel> dms){
        dms.forEach((dm->{
            Map<String,VariableVo> variableVoMap = new HashMap<>();
            dm.getVariables().ifPresent((variables -> {
                variables.forEach((variable -> {
                Map<String,String> domainValueMap = new HashMap<>();
                variable.getDomains().ifPresent(domains -> {
                    domains.forEach(domain -> {
                    domain.getDomainValues().ifPresent(domainValues-> {
                        domainValues.forEach((domainValue -> {
                        domainValueMap.put(domain.getName()+"__"+domainValue.getKey(),domainValue.getValue());
                        }));
                    });
                    });
                });

                VariableVo variableVo = new VariableVo(variable);
                variableVo.setDomainValueMap(domainValueMap);
                variableVoMap.put(variable.getName(),variableVo);
                }));
            }));
            metaMap.put(dm.getName(),variableVoMap);
        }));
    }

}
