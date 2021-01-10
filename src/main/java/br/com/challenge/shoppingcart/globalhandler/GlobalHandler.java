package br.com.challenge.shoppingcart.globalhandler;

import br.com.challenge.shoppingcart.dto.error.ErrorDTO;
import br.com.challenge.shoppingcart.exceptions.CartNotFoundException;
import br.com.challenge.shoppingcart.exceptions.UserNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalHandler {

    @Autowired
    MessageSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> fieldValidation(MethodArgumentNotValidException exception) {
        List<FieldError> fieldErrorList = exception.getBindingResult().getFieldErrors();
        List<ErrorDTO> errorDTOList = new ArrayList<>();
        fieldErrorList.forEach(e->{
            String message = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            ErrorDTO errorDTO = new ErrorDTO(e.getField(),message);
            errorDTOList.add(errorDTO);
        });
        return new ResponseEntity<>(errorDTOList,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({UserNotFoundException.class, CartNotFoundException.class, IllegalArgumentException.class})
    public ResponseEntity<?> userNotFoundException(RuntimeException exception) {
        Map<String, String> message = new HashMap<>();
        message.put("Error",exception.getClass().getSimpleName() );
        return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
    }
}
