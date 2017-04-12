/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uvigo.esei.pro2.core;

/**
 *
 * @author alumno
 */
public class ArticuloRevista extends Referencia{

        private String tituloRevista;
        private String doi;
        private int volumen;
        private int numero;
        private int pagInicio;
        private int pagFin;

    public ArticuloRevista(String tituloRevista, String doi, int volumen, int numero, int pagInicio, int pagFin, String autores, String titulo, int ano) {
        super(autores, titulo, ano);
        this.tituloRevista = tituloRevista;
        this.doi = doi;
        this.volumen = volumen;
        this.numero = numero;
        this.pagInicio = pagInicio;
        this.pagFin = pagFin;
    }

    @Override
    public String toString() {
        return "ArticuloRevista{"+ super.toString() + "tituloRevista=" + tituloRevista + ", doi=" + doi + ", volumen=" + volumen + ", numero=" + numero + ", pagInicio=" + pagInicio + ", pagFin=" + pagFin + '}';
    }

    public String getTituloRevista() {
        return tituloRevista;
    }

    public void setTituloRevista(String tituloRevista) {
        this.tituloRevista = tituloRevista;
    }

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public int getVolumen() {
        return volumen;
    }

    public void setVolumen(int volumen) {
        this.volumen = volumen;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getPagInicio() {
        return pagInicio;
    }

    public void setPagInicio(int pagInicio) {
        this.pagInicio = pagInicio;
    }

    public int getPagFin() {
        return pagFin;
    }

    public void setPagFin(int pagFin) {
        this.pagFin = pagFin;
    }
        
        
        
}
