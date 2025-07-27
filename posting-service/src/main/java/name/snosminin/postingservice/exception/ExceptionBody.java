package name.snosminin.postingservice.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class ExceptionBody {

    private String message;
    private Map<String, String> errors;

    public ExceptionBody(final String message) {
        this.message = message;
    }
}