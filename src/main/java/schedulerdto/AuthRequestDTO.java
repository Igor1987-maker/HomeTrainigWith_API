package schedulerdto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString

public class AuthRequestDTO {
String email;
String password;

}
