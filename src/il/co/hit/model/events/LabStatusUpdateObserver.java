package il.co.hit.model.events;

import il.co.hit.model.objects.StatusUpdateEvent;

import java.util.Observable;

public class LabStatusUpdateObserver extends Observable {

    public void statusUpdated(StatusUpdateEvent event) {
        this.setChanged();
        this.notifyObservers(event);
    }
}
