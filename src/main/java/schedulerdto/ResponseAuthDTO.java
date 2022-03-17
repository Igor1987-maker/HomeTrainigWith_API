package schedulerdto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString


public class ResponseAuthDTO {
    boolean registration;
    String status;
    String token;


}
