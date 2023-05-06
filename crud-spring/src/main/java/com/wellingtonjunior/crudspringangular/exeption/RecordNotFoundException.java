package com.wellingtonjunior.crudspringangular.exeption;

public class RecordNotFoundException extends RuntimeException {

    public  static final int serialVersionUID = 1;

    public RecordNotFoundException(Long id) {
        super("Registro não encontrado com ID: "+ id);
    }

}
