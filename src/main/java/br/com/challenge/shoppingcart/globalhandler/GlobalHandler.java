package br.com.challenge.shoppingcart.globalhandler;

import br.com.challenge.shoppingcart.dto.error.ErrorValidationDTO;
import br.com.challenge.shoppingcart.dto.error.FieldValidationDTO;

import com.google.common.base.Strings;
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
        List<FieldValidationDTO> errorDTOList = new ArrayList<>();
        fieldErrorList.forEach(e->{
            String message = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            FieldValidationDTO errorDTO = new FieldValidationDTO(e.getField(),message, exception.getClass().getSimpleName());
            errorDTOList.add(errorDTO);
        });
        return new ResponseEntity<>(errorDTOList,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> userNotFoundException(RuntimeException exception) {
        Map<String, String> mapMessage = new HashMap<>();
        String message = Strings.isNullOrEmpty(exception.getMessage()) ? exception.getClass().getSimpleName() : exception.getMessage();
        ErrorValidationDTO errorDTO = new ErrorValidationDTO(message, exception.getClass().getSimpleName());
        return new ResponseEntity<>(errorDTO,HttpStatus.BAD_REQUEST);
    }
}
