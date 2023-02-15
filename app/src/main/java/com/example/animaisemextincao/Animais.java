package com.example.animaisemextincao;

import android.graphics.Bitmap;

public class Animais {
    private String NOME;
    private String NOMECIENTIFICO;
    private String LOCALIZACAO;
    private String AMEACAS;
    private String CLASSIFICACAO;
    private String TENDENCIA;
    private String DESCRICAO;
    private String CATEGORIA;

    public Animais(String nome,String nomeCientifico,String localizacao,String ameacas, String classificacao, String tendencia,String descricao, String categoria ){
        this.NOME = nome;
        this.NOMECIENTIFICO = nomeCientifico;
        this.LOCALIZACAO = localizacao;
        this.AMEACAS = ameacas;
        this.CLASSIFICACAO = classificacao;
        this.TENDENCIA = tendencia;
        this.DESCRICAO = descricao;
        this.CATEGORIA = categoria;
    }

    public Animais(){ }
    public String getNOME() { return NOME; }
    public String getNOMECIENTIFICO() {
        return NOMECIENTIFICO;
    }
    public String getLOCALIZACAO() {
        return LOCALIZACAO;
    }
    public String getAMEACAS() { return AMEACAS; }
    public String getCLASSIFICACAO() {
        return CLASSIFICACAO;
    }
    public String getTENDENCIA() {
        return TENDENCIA;
    }
    public String getDESCRICAO() { return DESCRICAO; }
    public String getCATEGORIA() { return CATEGORIA; }
}
