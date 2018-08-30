package pl.home.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAppUserDTO {
    private Long userId;
    private String firstname;
    private String lastname;
    private String password;
    private String confirm_password;
}
