package asclepius.components.ClownCare.classes;

import asclepius.components.ClownCare.interfaces.IClownCare;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class ClownCare implements IClownCare {
    private ArrayList<String> jokes = new ArrayList<>();

    public ClownCare() {
        try {
            FileReader arquivo = new FileReader("src/asclepius/components/ClownCare/jokes.txt");
            BufferedReader formatado = new BufferedReader(arquivo);
            String linha = formatado.readLine();
            while(linha!=null){
                jokes.add(linha);
                linha = formatado.readLine();
            }
            formatado.close();
        }catch (IOException e){
            System.out.println("Nao foi possivel abrir o arquivo 'jokes.txt'");
        }
    }
    public void addJoke(String joke){
        try {
            FileWriter arquivo = new FileWriter("src/asclepius/ClownCare/jokes.txt", true);
            PrintWriter formatado = new PrintWriter(arquivo);
            if(!jokes.contains(joke)) {
                formatado.println(joke);
                jokes.add(joke);
                System.out.println("Piada gravada no arquivo com sucesso!");
            }
            else
                System.out.println("Piada ja encontrada no repertório");
            formatado.close();
        }catch (IOException e){
            System.out.println("Nao foi possivel abrir o arquivo 'jokes.txt'");
        }
    }

    public String randJoke(int percent){
        Random gerador = new Random();
        int aleatorio = gerador.nextInt(101);
        if(percent > aleatorio){
            return jokes.get(gerador.nextInt(jokes.size()));
        }
        else{
            return null;
        }
    }
}
