package il.co.hit.model.objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Contact implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String phoneNumber;
    private String email;
}
