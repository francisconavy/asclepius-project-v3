package asclepius.components.GraficosComplemento;


import asclepius.components.GraficosComplemento.IPatient;
import asclepius.components.GraficosComplemento.ITableProducer;

import java.util.Random;

public class Patient implements IPatient {
    private String[]   gabarito;
    private String[]   attributes;
    private ITableProducer ds;
    private int doenca;
    private int idade;
    private int urgencia;
    private Random rand;
    private int CPF;
    private String nome;

    //funcao nova

    //-------------------------------------------------------------------------------------------------//
    public int getIdade(){
        return idade;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCPF(int CPF) {
        this.CPF = CPF;
    }

    public String getNome() {
        return nome;
    }

    public int getCPF() {
        return CPF;
    }

    public void setUrgencia(int urgencia){
        this.urgencia = urgencia;
    }

    public int getUrgencia() {
        return urgencia;
    }

    public int getDoenca() {
        return doenca;
    }

    public void setDoenca(int doenca) {
        this.doenca = doenca;
    }

    public String[] getGabarito() {
        return gabarito;
    }

    public String[] getAttributes(){
        return attributes;
    }
    //-------------------------------------------------------------------------------------------------//
    public Patient(ITableProducer producer){
        idade = 1;
        urgencia = -1;
        connect(producer);
        rand = new Random();
        attributes = ds.requestAttributes();
        doenca = rand.nextInt(producer.requestInstances().length);
        gabarito = ds.requestInstances()[doenca];
    }

    public void crescer(){
        if(idade < 3)
            idade++;
    }

    public void crescer(int n){
        for(int i = 0; i < n; i++)
            crescer();
    }

    public String ask(String question){
        for(int i = 0; i < attributes.length; i++)
            if(question == attributes[i])
                return gabarito[i];
        return "unknown";
    }

    public boolean finalAnswer(String answer){
        if(gabarito[gabarito.length-1].equals(answer))
            return true;
        else
            return false;
    }

    public void connect(ITableProducer producer){
        ds = producer;
    }
}