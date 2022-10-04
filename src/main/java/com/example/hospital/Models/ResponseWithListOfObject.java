package com.example.hospital.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseWithListOfObject<T> extends Response{

    private  List<T> data;

    public ResponseWithListOfObject(String status, String message, List<T> data) {
        super(status, message);
        this.data = data;
    }
}
