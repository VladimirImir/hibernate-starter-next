package com.dev.listener;

import com.dev.entity.Revision;
import org.hibernate.envers.RevisionListener;

public class DevRevisionListener implements RevisionListener {

    @Override
    public void newRevision(Object revisionEntity) {
        // SecurityContext.getUser().geyId()
        ((Revision) revisionEntity).setUsername("dev");
    }
}
