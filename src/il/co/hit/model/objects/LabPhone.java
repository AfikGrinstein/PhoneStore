package il.co.hit.model.objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LabPhone implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String phoneId;
    private LabStatus status;
    private Contact contact;
    private LocalDateTime creationDate;

    public LabPhone(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LabPhone labPhone = (LabPhone) o;
        return Objects.equals(id, labPhone.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
