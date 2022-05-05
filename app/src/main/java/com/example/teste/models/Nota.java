package com.example.teste.models;

public class Nota {
    String nome;
    int id;

    public Nota(int id, String nome) {
        this.nome = nome;
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String toString(){
        return this.nome;
    }
}
