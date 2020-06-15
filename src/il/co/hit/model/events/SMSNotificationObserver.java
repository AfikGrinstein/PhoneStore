package il.co.hit.model.events;

import il.co.hit.model.Utils;
import il.co.hit.model.objects.StatusUpdateEvent;

import java.text.MessageFormat;
import java.util.Observable;
import java.util.Observer;

public class SMSNotificationObserver implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        if (arg != null) {
            StatusUpdateEvent event = (StatusUpdateEvent) arg;
            if (Utils.hasText(event.getContact().getPhoneNumber())) {
                String smsBody = MessageFormat.format("SMS was sent to {0}; Hi {3}, Phone ''{1}'' status has been updated to: {2}",
                        event.getContact().getPhoneNumber(),
                        event.getPhoneName(),
                        event.getNewStatus().toString(),
                        event.getContact().getName());
                // TODO: Send an SMS
                System.out.println(smsBody);
            }
        }
    }
}
