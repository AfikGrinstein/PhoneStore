package il.co.hit.model.events;

import il.co.hit.model.Utils;
import il.co.hit.model.objects.StatusUpdateEvent;

import java.text.MessageFormat;
import java.util.Observable;
import java.util.Observer;

public class EmailNotificationObserver implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        if (arg != null) {
            StatusUpdateEvent event = (StatusUpdateEvent) arg;
            if (Utils.hasText(event.getContact().getEmail())) {
                String emailBody = MessageFormat.format("Email was sent to {0}; Hi {3}, Phone {1} is now in status {2}",
                        event.getContact().getEmail(),
                        event.getPhoneName(),
                        event.getNewStatus().toString(),
                        event.getContact().getName());
                // TODO: Send an email
                System.out.println(emailBody);
            }
        }
    }
}
