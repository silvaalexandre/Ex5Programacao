/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex5lista3;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.text.NumberFormatter;

/**
 *
 * @author Alexandre
 */
public class Ex5Lista3 extends JFrame{
    	private JPanel contentPane;
	private JTextArea textArea;
        private JFileChooser jFileChooser;
        private File arquivo;               
	private JFormattedTextField textField;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private Ex5Lista3 main;

    public static void main(String[] args) {
            EventQueue.invokeLater(new Runnable() {
                    public void run() {
                            try {
                                    Ex5Lista3 frame = new Ex5Lista3();
                                    frame.setVisible(true);
                            } catch (Exception e) {
                                    e.printStackTrace();
                            }
                    }
            });
    }

	/**
	 * Create the frame.
	 */
    public Ex5Lista3() {
            setBackground(Color.WHITE);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setBounds(100, 100, 460, 600);
            contentPane = new JPanel();
            contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
            setContentPane(contentPane);
            contentPane.setLayout(null);

            JLabel lblArquivoDestino = new JLabel("Número de palavras no conjunto de anagramas:");
	    lblArquivoDestino.setBounds(19, 115, 402, 20);
	    contentPane.add(lblArquivoDestino);
                
            jFileChooser = new JFileChooser();
            contentPane.add(jFileChooser);
            jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            
            JButton btnOrigem = new JButton("Selecionar arquivo");
            btnOrigem.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                     int resultado = jFileChooser.showOpenDialog(jFileChooser);
                     if(resultado == JFileChooser.CANCEL_OPTION)
                        System.exit(1);

                     arquivo = jFileChooser.getSelectedFile();                          
                    }
            });
            NumberFormat format = NumberFormat.getInstance();
            NumberFormatter formatter = new NumberFormatter(format);            
                    
            textField = new JFormattedTextField(formatter);
	    textField.setBounds(19, 138, 402, 20);
            textField.setEditable(true);                
            contentPane.add(textField);
            
            
            btnOrigem.setBounds(19, 80, 402, 20);
            contentPane.add(btnOrigem);
            JButton btnProcessar = new JButton("Processar");
            textArea = new JTextArea();
            textArea.setBounds(19, 180, 402, 300);                           
            textArea.setBorder(new BevelBorder(1, Color.black, Color.black));
            textArea.setVisible(true);
            textArea.setEditable(false);   
            contentPane.add(textArea);
            
            btnProcessar.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {                           

                        try{                         
                           processar(arquivo); 
                        }
                        catch (Exception ex) {
                            System.out.println(".actionPerformed()");                            
                        }
                    }
            });
            btnProcessar.setBounds(183, 500, 89, 23);
            contentPane.add(btnProcessar);
            

    }
    
    private void processar(File arq) throws IOException{
        textArea.setText("");
        String palavraSemOrdenacao;
        HashMap<String,Anagrama> hashAnagrama = new HashMap<String,Anagrama>();
        for (String palavra : ArquivoAcoes.ler(arq)) {
            palavraSemOrdenacao = palavra;
            char[] palavraArray = palavra.toCharArray();            
            Arrays.sort(palavraArray);
            String palavraOrdenada = "";
            for (char c : palavraArray) {
                palavraOrdenada += c;
            }
            if (hashAnagrama.containsKey(palavraOrdenada)){
                Anagrama anagramaAtual = hashAnagrama.get(palavraOrdenada);
                anagramaAtual.getListaAnagramas().add(palavraSemOrdenacao);
                anagramaAtual.setQtd(anagramaAtual.getQtd()+1);
            }
            else{                
                Anagrama anagrama = new Anagrama(palavraOrdenada, palavraSemOrdenacao, 1);                
                hashAnagrama.put(palavraOrdenada, anagrama);
            }            
        }
        textArea.append("Listas de anagramas:");
        boolean temAlgum = false;
        for (Map.Entry<String, Anagrama> object : hashAnagrama.entrySet()) {
            
            if (object.getValue().getQtd() > Integer.parseInt(textField.getText())){                
                temAlgum = true;
                textArea.append("\n"+ object.getValue().stringAnagramas());
            }
            
        }
        if (!temAlgum)
                textArea.append("Nenhum conjunto de anagramas com tantas palavras.");

    }   
    
}