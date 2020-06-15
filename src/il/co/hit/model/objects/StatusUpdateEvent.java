package il.co.hit.model.objects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatusUpdateEvent {

    private Contact contact;
    private LabStatus newStatus;
    private String phoneName;

    public StatusUpdateEvent(Contact contact, LabStatus newStatus, String phoneName) {
        this.contact = contact;
        this.newStatus = newStatus;
        this.phoneName = phoneName;
    }
}
