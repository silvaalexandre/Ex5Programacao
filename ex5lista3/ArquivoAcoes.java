/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex5lista3;

/**
 *
 * @author Alexandre
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * 
 */
public class ArquivoAcoes {
    
    public static ArrayList<String> ler(File arq) throws IOException{
        ArrayList<String> listaLida = new ArrayList<String>();
        // TODO Ler arquivo pelo Path informado
        try {   
            FileReader fileArq = new FileReader(arq);
            BufferedReader lerArq = new BufferedReader(fileArq);
            String linhaLida = lerArq.readLine();
            while (linhaLida != null) 
            {
              listaLida.add(linhaLida);
              linhaLida = lerArq.readLine();                  
            }
            fileArq.close();
        } catch (Exception e) {
            System.err.printf("Arquivo inexistente.\n",
              e.getMessage());
        }  
        return listaLida;
    }
    
    public static void salvar(String pathDestino, String texto) throws IOException{
        try 
        {
            File arquivo = new File(pathDestino);
            FileWriter writer = new FileWriter(arquivo);  
            BufferedWriter buffwriter = new BufferedWriter(writer);  

            buffwriter.write(texto);  
            buffwriter.flush();  
            buffwriter.close();    
        } catch (Exception e) {
           System.err.printf("Erro ao escrever arquivo.\n",
              e.getMessage()); 
        }
    }
}
