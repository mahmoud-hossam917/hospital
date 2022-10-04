package com.example.hospital.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseWithSetOfObject<T> extends Response{

    private Set<T> data;


    public ResponseWithSetOfObject(String status, String message, Set<T> data) {
        super(status, message);
        this.data = data;
    }
}
