/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex5lista3;

import java.util.ArrayList;

/**
 *
 * @author Alexandre
 */
public class Anagrama {
    private String ordenada;
    private ArrayList<String> listaAnagramas;
    private Integer qtd;

    public Integer getQtd() {
        return qtd;
    }

    public void setQtd(Integer qtd) {
        this.qtd = qtd;
    }

    public String getOrdenada() {
        return ordenada;
    }

    public void setOrdenada(String ordenada) {
        this.ordenada = ordenada;
    }

    public ArrayList<String> getListaAnagramas() {
        return listaAnagramas;
    }

    public Anagrama(String ordenada, String palavra, Integer qtd) {
        ArrayList<String> lista = new ArrayList<String>();
        lista.add(palavra);
        
        this.ordenada = ordenada;
        this.listaAnagramas = lista;
        this.qtd = qtd;
    }
    public String stringAnagramas(){
        String lista = "[";
        boolean primeiro = true;
        for (String listaAnagrama : listaAnagramas) {            
            if (primeiro){
                lista+=listaAnagrama;            
                primeiro = false;
            }
            else
                lista += ","+listaAnagrama;
            
                
            
        }
        lista += "]";
        
        return lista;
    }

    
}
