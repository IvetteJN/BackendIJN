package com.portfolio.Dto;

import javax.validation.constraints.NotBlank;

public class DtoPersona {
  
    @NotBlank
    private String nombre;
    @NotBlank
    private String apellido;
    @NotBlank
    private String descripcionP; 
    @NotBlank
    private String tituloP;
    @NotBlank
    private String imgP;

    public DtoPersona() {
    }

    public DtoPersona(String nombre, String apellido, String descripcionP, String tituloP, String imgP) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.descripcionP = descripcionP;
        this.tituloP = tituloP;
        this.imgP = imgP;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDescripcionP() {
        return descripcionP;
    }

    public void setDescripcionP(String descripcionP) {
        this.descripcionP = descripcionP;
    }

    public String getTituloP() {
        return tituloP;
    }

    public void setTituloP(String tituloP) {
        this.tituloP = tituloP;
    }

    public String getImgP() {
        return imgP;
    }

    public void setImgP(String imgP) {
        this.imgP = imgP;
    }    
}
