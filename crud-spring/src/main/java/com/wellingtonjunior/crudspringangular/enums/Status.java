package com.wellingtonjunior.crudspringangular.enums;

public enum Status {
    ACTIVE("Ativo"), INACTIVE("Inativo");

    private final String value;

    private Status(String value){
        this.value = value;
    }

    @Override
    public String toString() {
        return  value;
    }

    public String getValue(){
        return this.value;
    }
}
