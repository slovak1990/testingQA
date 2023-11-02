package hackMeBank11.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public class RegistrationModel {
    @JsonProperty("userName")
    public String userName;

    @JsonProperty("login")
    public String login;
    @JsonProperty("phoneNumber")
    public String phoneNumber;

    @JsonProperty("password")
    public String password;

    @JsonProperty("passwordValidation")
    public String passwordValidation;
}