package asclepius.components.GraficosComplemento;/*Classe: /Classes/Grafico.java
Autores:
Gabriel de Souza Mafra - RA 197272
Manuela Rafael Onofre de Souza - RA 202534
Victor Toon de Araujo - RA 225231
Gabriel Batista Moura - RA 216101
Augusto Piato Amstalden - RA 213364
Daniel Cardoso Custodio de Oliveira - RA 169400
Objetivo:
O objetivo do componente é criar um gráfico com a frequência de doenças diagnosticadas e outro com a frequencia de sintomas identificados a fim de guiar a anamnsese médica para doenças mais comuns para facilitar o diagnóstico.
Interface:
*/


//importando a biblioteca de numeros aleatorios
import java.io.*;
import java.util.Random;
//importando as bibliotecas usadas pelo DataSet

//------------------------------------------------------------------------------------------------//
import java.lang.String;


public class Sintomas{
    private String nome;
    private int frequencia;

    public Sintomas(String nomeNovo){
        this.nome = nomeNovo;
        this.frequencia = 0;
    }

    public void aumentaFrequencia(){
        this.frequencia++;
    }

    public int getFrequencia(){
        return this.frequencia;
    }

    public String getNome(){
        return this.nome;
    }
}

//-------------------------------------------------------------------------------------------------//