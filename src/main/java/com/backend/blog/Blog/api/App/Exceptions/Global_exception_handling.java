package com.backend.blog.Blog.api.App.Exceptions;

// import java.util.HashMap;
// import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.backend.blog.Blog.api.App.Payloads.Api_response;

@RestControllerAdvice
public class Global_exception_handling {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Api_response> resourceNotFoundExceptionHandler(ResourceNotFoundException ex) {

        String message = ex.getMessage();
        Api_response api_response = new Api_response(message, false);

        return new ResponseEntity<Api_response>(api_response, HttpStatus.NOT_FOUND);
    }

    // TODO add exception for if the arguments fora user is not found

    // @ExceptionHandler(MethordArgumentsNotValidException.class)
    // public ResponseEntity<Map<String, String>> methordArgumentsNotValidExceptionHandler(MethordArgumentsNotValidException ex){
    //     Map<String, String> resp= new HashMap<>();
    //     ex.getBindingResult().getAllErrors().forEach((error) -> {
    //         String field_name = ((FieldError)eror).getfield();
    //         String message = error.getMessage();
    //     });

    //     return new ResponseEntity<Map<String, String>>(resp , HttpStatus.BAD_REQUEST);
    // }

}