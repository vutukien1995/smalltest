package com.kien.smalltest.infras.handle;

import com.kien.smalltest.infras.dto.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author kienvt3
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    MessageSource messageSource;

    @ExceptionHandler({ BadRequestException.class })
    public ResponseEntity<Object> handleBadRequestException(
            Exception ex, WebRequest request) {
        var error = getMessageForError(ex.getMessage());
        List<Response.Error> errors = List.of(error);
        return new ResponseEntity<>(new Response<>(false, null, null, errors),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ MethodArgumentNotValidException.class })
    public ResponseEntity<Object> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex, WebRequest request, Locale locale) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        List<Response.Error> errors = new ArrayList<>();
        for (FieldError fieldError : fieldErrors) {
            Response.Error error = getMessageForError(fieldError.getDefaultMessage());
            error.setMsg("'" + fieldError.getField() + "'" + " " + error.getMsg());
            errors.add(error);
        }
        return new ResponseEntity<>(new Response<>(false, null, null, errors),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ Exception.class })
    public ResponseEntity<Object> handleAllException(
            Exception ex, WebRequest request) {
        ex.printStackTrace();
        Response.Error error = new Response.Error(999, "Something wrong !! Contact IT and back to our world !!");
        List<Response.Error> errors = List.of(error);
        return new ResponseEntity<>(new Response<>(false, null, null, errors),
                HttpStatus.BAD_REQUEST);
    }

    // ================================== PRIVATE FUNCTION =============================================
    private Response.Error getMessageForError (String message) {
        Response.Error error = new Response.Error();
        String messageSourceMessage = messageSource.getMessage(message, null, null);
        String[] txt = messageSourceMessage.split("-");
        error.setCode(Integer.parseInt(txt[0]));
        error.setMsg(txt[1]);

        return error;
    }

}
