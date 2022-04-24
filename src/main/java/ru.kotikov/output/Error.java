package ru.kotikov.output;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

public class Error {
    @Getter
    @JsonProperty("type")
    private final String TYPE = "error";
    @Getter
    @Setter
    @JsonProperty("message")
    private String message;

    public Error(String message) {
        this.message = message;
    }
}
