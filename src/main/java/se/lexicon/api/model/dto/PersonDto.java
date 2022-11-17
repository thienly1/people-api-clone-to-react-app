package se.lexicon.api.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Getter
@Setter
@ToString
public class PersonDto {

    private Integer id;
    @NotEmpty(message = "FirstName should not empty")
    @Size(min = 2, max = 40, message = "FirstName length should be between 2-40")
    private String firstName;
    @NotEmpty(message = "LastName should not be empty")
    @Size(min = 2, max = 40, message = "LastName length should be between 2-40")
    private String lastName;
    @NotEmpty(message = "Email should not be empty")
    @Size(min = 2, max = 80, message = "Email length should be between 2-80")
    private String email;
    private String title;


}