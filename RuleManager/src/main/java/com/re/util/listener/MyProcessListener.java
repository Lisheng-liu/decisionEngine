package com.re.util.listener;

import com.re.util.DroolsRuleLog;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.kie.api.event.process.*;

import java.util.LinkedList;
import java.util.List;

public class MyProcessListener implements ProcessEventListener {

    private List<String> parentNodes = new LinkedList<>();
    private String lastNode;
    private DroolsRuleLog droolsRuleLog;

    public MyProcessListener(DroolsRuleLog droolsRuleLog){
        this.droolsRuleLog = droolsRuleLog;
    }



    @Override
    public void beforeProcessStarted(ProcessStartedEvent processStartedEvent) {

    }

    @Override
    public void afterProcessStarted(ProcessStartedEvent processStartedEvent) {

    }

    @Override
    public void beforeProcessCompleted(ProcessCompletedEvent processCompletedEvent) {

    }

    @Override
    public void afterProcessCompleted(ProcessCompletedEvent processCompletedEvent) {

    }

    @Override
    public void beforeNodeTriggered(ProcessNodeTriggeredEvent event) {
        System.out.println("beforeNodeTriggered:nodeName: "+ event.getNodeInstance().getNodeName()+"::"+event.getNodeInstance().getId());
        String nodeName = event.getNodeInstance().getNodeName()+"("+String.valueOf(event.getNodeInstance().getNode().getMetaData().get("UniqueId"))+")";

        if(StringUtils.isNotBlank(lastNode) && event.getNodeInstance().getNode().getIncomingConnections().size() == 0){
            parentNodes.add(lastNode);
            return;
        }

        if(StringUtils.isNotBlank(lastNode) && event.getNodeInstance().getNode().getOutgoingConnections().size() == 0){
            parentNodes.remove(parentNodes.size() - 1);
            return;
        }

        if(StringUtils.isNotBlank(nodeName)){
            lastNode = nodeName;
        }

        if(CollectionUtils.isNotEmpty(parentNodes)){
            nodeName = combineNodeList(parentNodes)+"->"+nodeName;
        }
        droolsRuleLog.setNodeName(nodeName);

    }

    private String combineNodeList(List<String> parentNodes) {
        StringBuffer stringBuffer = new StringBuffer();
        for(int i =0; i < parentNodes.size();i++){
            if(i == 0){
                stringBuffer.append(parentNodes.get(i));
                continue;
            }
            stringBuffer.append("->").append(parentNodes.get(i));

        }
        return stringBuffer.toString();
    }

    @Override
    public void afterNodeTriggered(ProcessNodeTriggeredEvent processNodeTriggeredEvent) {

    }

    @Override
    public void beforeNodeLeft(ProcessNodeLeftEvent processNodeLeftEvent) {

    }

    @Override
    public void afterNodeLeft(ProcessNodeLeftEvent processNodeLeftEvent) {

    }

    @Override
    public void beforeVariableChanged(ProcessVariableChangedEvent processVariableChangedEvent) {
        System.out.println("beforeVariableChanged => "+ processVariableChangedEvent);
    }

    @Override
    public void afterVariableChanged(ProcessVariableChangedEvent processVariableChangedEvent) {
        System.out.println("afterVariableChanged => "+ processVariableChangedEvent);
    }

    @Override
    public void beforeSLAViolated(SLAViolatedEvent event) {

    }

    @Override
    public void afterSLAViolated(SLAViolatedEvent event) {

    }
}
