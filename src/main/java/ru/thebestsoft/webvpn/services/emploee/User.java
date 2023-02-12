package ru.thebestsoft.webvpn.services.emploee;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public class User {
    @NotBlank
    @Pattern(regexp = "[A-z0-9_]+", message = "Можно использовать цифры, английские буквы и знак \"_\"!")
    String login;
    String typeVPN = "IPSec/IKEv2 MSCHAPv2 EAP";
    @NotBlank
    @Pattern(regexp = "[A-z0-9_]+", message = "Можно использовать цифры, английские буквы и знак \"_\"!")
    String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getTypeVPN() {
        return typeVPN;
    }

    public void setTypeVPN(String typeVPN) {
        this.typeVPN = typeVPN;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
