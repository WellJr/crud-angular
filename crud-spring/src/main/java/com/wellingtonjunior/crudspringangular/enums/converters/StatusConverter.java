package com.wellingtonjunior.crudspringangular.enums.converters;

import com.wellingtonjunior.crudspringangular.enums.Status;
import jakarta.persistence.AttributeConverter;

import java.util.stream.Stream;

public class StatusConverter implements AttributeConverter<Status, String> {
    @Override
    public String convertToDatabaseColumn(Status status) {

        if(status.getValue() == null){
            return null;
        }

        return status.getValue();
    }

    @Override
    public Status convertToEntityAttribute(String s) {

        return Stream.of(Status.values())
                .filter(x -> x.getValue().equals(s))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
