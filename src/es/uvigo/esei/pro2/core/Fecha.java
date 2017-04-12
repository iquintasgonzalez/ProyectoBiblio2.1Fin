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
public class Fecha {
    private int d;
    private int m;
    private int a;

    public Fecha(int d, int m, int a) {
        this.d = d;
        this.m = m;
        this.a = a;
    }

    @Override
    public String toString() {
        return  d + " / " + m + " / " + a ;
    }
}
