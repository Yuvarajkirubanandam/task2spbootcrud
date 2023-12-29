package com.yuvarajk.Controller;

import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor
@Data
public class CustomResponse<T> {
 

    
    private boolean status;
    private String message;
    private T data;
    
    

}
