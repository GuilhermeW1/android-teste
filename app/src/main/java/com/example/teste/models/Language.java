package com.example.teste.models;

public class Language {

    private String name;
    private int id;
    private String description;
    private int favorito;
    private int nota;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFavorito() {
        return favorito;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public void setFavorito(int favorito) {
        this.favorito = favorito;
    }

    @Override
    public String toString(){
        return name+": { "+description+" }";

    }

}
