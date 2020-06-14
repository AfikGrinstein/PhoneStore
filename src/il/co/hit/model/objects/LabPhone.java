package il.co.hit.model.objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class LabPhone extends Phone implements Serializable {

    private static final long serialVersionUID = 1L;

    private LabStatus status;
    private Contact contact;
    private Date creationDate;
}
