package com.re.util.listener;

import org.kie.api.event.rule.ObjectDeletedEvent;
import org.kie.api.event.rule.ObjectInsertedEvent;
import org.kie.api.event.rule.ObjectUpdatedEvent;
import org.kie.api.event.rule.RuleRuntimeEventListener;

public class MyRuleRunTimeListener implements RuleRuntimeEventListener {
    @Override
    public void objectInserted(ObjectInsertedEvent objectInsertedEvent) {

    }

    @Override
    public void objectUpdated(ObjectUpdatedEvent objectUpdatedEvent) {
        System.out.println(objectUpdatedEvent);
    }

    @Override
    public void objectDeleted(ObjectDeletedEvent objectDeletedEvent) {

    }
}
