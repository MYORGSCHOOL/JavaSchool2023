package tumbaev.backbone.dto;

import tumbaev.backbone.util.constant.HttpCode;

/**
 * Class representing a payload that should be passed in the response if request wasn't successful.
 */
public class ErrorResponse {
    private HttpCode code;
    private String message;
    private String details;

    //required to convert to json
    public ErrorResponse() {
    }

    public ErrorResponse(HttpCode code, String message, String details) {
        this.message = message;
        this.code = code;
        this.details = details;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpCode getCode() {
        return code;
    }

    public void setCode(HttpCode code) {
        this.code = code;
    }

    /**
     * Used to convert current object to json in case external class failed to convert the object.
     *
     * @return json representation of this object.
     */
    public String toJson() {
        return "{code:" + code +
                ",message:\"" + message + "\"" +
                ",details:\"" + details + "\"}";
    }
}
