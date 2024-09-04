package com.platform.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Getter
@Setter
@Jacksonized
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor

public class User {

    private static final String NAME_EMPTY_MSG = "Name of user can not be null or empty";
    private static final String NAME_REGEX = "^[a-zA-Z //-]+";
    private static final String NAME_REGEX_MSG = "Name of user can contain letters and hyphen";


    private static final String SURNAME_EMPTY_MSG = "Surname of user can not be null or empty";
    private static final String SURNAME_REGEX = "^[a-zA-Z //-]+";
    private static final String SURNAME_REGEX_MSG = "Surname of user can contain letters and hyphen";

    private static final String EMAIL_EMPTY_MSG = "Surname of user can not be null or empty";
    private static final String EMAIL_REGEX = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    @Schema(description = "unique identifier of the user", hidden = true)
    private UUID userId;

    @NotEmpty(message = NAME_REGEX)
    @Pattern(regexp = NAME_REGEX, message = NAME_REGEX_MSG)
    @Schema(example = "Jon")
    private String name;

    @NotEmpty(message = SURNAME_EMPTY_MSG)
    @Pattern(regexp = SURNAME_REGEX, message = SURNAME_REGEX_MSG)
    @Schema(example = "Surname of user", defaultValue = "Liman")
    private String surname;

    @NotEmpty(message = EMAIL_EMPTY_MSG)
    @Pattern(regexp = EMAIL_REGEX)
    @Schema(example = "Email address of user", defaultValue = "Jon@gmail.com")
    private String email;
    @Hidden
    private String password;
    private UUID accountId;
    private Address address;
}
