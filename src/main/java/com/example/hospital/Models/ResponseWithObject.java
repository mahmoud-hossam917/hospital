package com.example.hospital.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseWithObject<T> extends Response{

    private T data;

    public ResponseWithObject(String status, String message, T data) {
        super(status, message);
        this.data = data;
    }


}
