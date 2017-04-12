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
public class Libro extends Referencia{
    private String editorial;
    private String isbn;
    public enum TipoFormato{PAPEL,EBOOK};
    public TipoFormato formato;

    public Libro(String editorial, String isbn, TipoFormato formato, String autores, String titulo, int ano) {
        super(autores, titulo, ano);
        this.editorial = editorial;
        this.isbn = isbn;
        this.formato = formato;
    }

    @Override
    public String toString() {
        return "Libro{"+ super.toString()+ "editorial=" + editorial + ", isbn=" + isbn + ", formato=" + formato + '}';
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public TipoFormato getFormato() {
        return formato;
    }

    public void setFormato(TipoFormato formato) {
        this.formato = formato;
    }


    }
    
    

