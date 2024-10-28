package it.cascella.dbsetup.dto;

import it.cascella.dbsetup.entities.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link it.cascella.dbsetup.entities.User}
 */
@Value
public class UserDto implements Serializable {
    @NotBlank(message = "Username is mandatory")
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Username should contain only letters and numbers")
    private String username;
    @Size(min = 6, message = "Password should be at least 6 characters")
    @NotBlank(message = "Password is mandatory")
    private String password;
    @Email(message = "Email should be valid")
    private String email;
    public static User fromDtoto(UserDto userDto) {
        return new User(userDto.getUsername(), userDto.getPassword(), userDto.getEmail());
    }
}