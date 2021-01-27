package com.re.util;

import com.re.dm.MetaMap;
import com.re.dm.vo.VariableVo;
import com.re.exception.BusinessException;
import com.re.util.listener.MyAgendaEventListerner;
import com.re.util.listener.MyProcessListener;
import com.re.util.listener.MyRuleRunTimeListener;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.drools.core.io.impl.FileSystemResource;
import org.kie.api.KieBase;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.utils.KieHelper;

import java.io.File;
import java.util.Map;
import java.util.Optional;

@Slf4j
public class DroolUtil {

    public static Optional<DroolsRuleLog> executeDrl(Map<String,Object> insertMap, MetaMap metaMap, String drlPath, String funcDrlPath, String agendaGroup){
        if(!checkDrl(drlPath,null)){
        return Optional.empty();
        }
        KieHelper kieHelper = new KieHelper();
//        kieHelper.addContent(drl, ResourceType.DRL);
        kieHelper.addResource(new FileSystemResource(new File(drlPath),"UTF-8"));
        if(StringUtils.isNotBlank(funcDrlPath)){
//            kieHelper.addContent(funcDrl,ResourceType.DRL);
            kieHelper.addResource(new FileSystemResource(new File(funcDrlPath),"UTF-8"));
        }

        KieBase kieBase = kieHelper.build();
        KieSession kieSession = kieBase.newKieSession();
        kieSession.insert(insertMap);
        if(StringUtils.isNotBlank(agendaGroup)){
            kieSession.getAgenda().getAgendaGroup(agendaGroup).setFocus();
        }



        DroolsRuleLog droolsRuleLog = new DroolsRuleLog();
        RuleHelper ruleHelper = new RuleHelper(metaMap);
        droolsRuleLog.setHitCount(0L);
        droolsRuleLog.setRuleHelper(ruleHelper);

        boolean trace =true;
        if(trace){
            MyAgendaEventListerner agendaEventListerner = new MyAgendaEventListerner();
            MyRuleRunTimeListener ruleRunTimeListener = new MyRuleRunTimeListener();
            MyProcessListener processListener = new MyProcessListener(droolsRuleLog);
            kieSession.addEventListener(agendaEventListerner);
            kieSession.addEventListener(ruleRunTimeListener);
            kieSession.addEventListener(processListener);
        }

        kieSession.setGlobal("ruleHelper",ruleHelper);
        kieSession.setGlobal("droolsRuleLog",droolsRuleLog);
        kieSession.setGlobal("metaMap",metaMap);
        kieSession.setGlobal("dataMap",insertMap);

        try {
            kieSession.fireAllRules();
        }catch (Exception e){
            log.error("exec drl fail ",e);
            throw new BusinessException("exec drl fail,"+e.getMessage() );
        }finally {
            kieSession.dispose();
        }
        return Optional.ofNullable(droolsRuleLog);
    }
    public static boolean checkDrl(String drlPath,String funcDrlPath){
        KieHelper kieHelper = new KieHelper();
        kieHelper.addResource(new FileSystemResource(new File(drlPath),"UTF-8"));
//        kieHelper.addContent(drl,ResourceType.DRL);
        if(StringUtils.isNotBlank(funcDrlPath)){
//            kieHelper.addContent(funcDrl,ResourceType.DRL);
            kieHelper.addResource(new FileSystemResource(new File(funcDrlPath),"UTF-8"));
        }
        try {
            kieHelper.build();
        }catch (Exception e){
            log.error("checkDrl exception ",e);
            throw new BusinessException(e.getMessage());
//            return false;
        }
        return true;
    }

}
