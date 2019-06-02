package asclepius.components.Engine.classes.PatientPlus;

import asclepius.components.Engine.classes.DSC.DataSetComponentPlus;
import asclepius.components.Engine.interfaces.DSC.IDataSet;
import asclepius.components.Engine.interfaces.PatientPlus.IPatientPlus;
import java.util.ArrayList;
import java.util.Random;

public class PatientPlus implements IPatientPlus {
    /*Esse ArrayList vai guardar os 7 sintomas (yes/no), os dados do IPatientPlus*/
    private ArrayList<String> dataList = new ArrayList<>();
    private int randomData; //Remova esse atributo se a condição "Casos não incluídos na tabela podem ser usados pelos docentes para testar o código" for considerada. 
    public static final String sim = "t";
    public static final String nao = "f";
    public static final String naoSei = "unkown";
    
    public PatientPlus(){
    /*Quando um IPatientPlus for instanciado, ele obrigatória e aleatoriamente recebe um conjunto de dados diretamente da base .CSV*/
        IDataSet DS = new DataSetComponentPlus();
        DS.setDataSource("resources\\data\\test-cases-minus.csv");
        String attributes[] = DS.requestAttributes();
        String instances[][] = DS.requestInstances();
        randomData = new Random().nextInt(instances.length);
        //considerando a última coluna como "diagnostic" ou outro elemento q não deve ser considerado
        for(int x = 0; x < attributes.length - 1; x++){  
            dataList.add(instances[randomData][x]);
        }
        //TESTE OU DEBUGGING
        /*Habilite essa parte do código para fins de teste*/
        for(int x = 0; x < dataList.size(); x++){    
            System.out.printf("%s ", dataList.get(x));
        }
        System.out.println();
        //TESTE OU DEBUGGING
    }
    
    @Override 
    public String ask(String question) {
        /*Chamando um DS para adquirir o vetor de sintomas*/
        IDataSet DS = new DataSetComponentPlus();
        DS.setDataSource("resources\\data\\test-cases-minus.csv");
        String attributes[] = DS.requestAttributes();
        
        /*Checando se o sintoma existe e gerando a posição counter do vetor que possui tal sintoma, se existit.*/
        int counter = 0;
        for(int x = 0; x < attributes.length - 1; x++){
            if(attributes[x].equalsIgnoreCase(question)){
                break;
            }
            counter++;
        }
        
        if(counter < attributes.length - 1){ //Se o sintoma existe...
            if(dataList.get(counter).equalsIgnoreCase("t")){
                return sim;
            }else if(dataList.get(counter).equalsIgnoreCase("f")){
                return nao;
            }
        }else if(counter == attributes.length - 1){ //Se o sintoma não existe...
            return naoSei;
        }
        
        return "Function ask() has crashed.";
    }

    @Override
    /*Se a condição do projeto "Casos não incluídos na tabela podem ser usados pelos docentes para testar o código" for considerada, esse método, finalAnswer, se torna inútil.*/
    public boolean finalAnswer(String answer) {
        IDataSet DS = new DataSetComponentPlus();
        DS.setDataSource("resources\\data\\test-cases.csv");
        String instances[][] = DS.requestInstances();
        
        return answer.equalsIgnoreCase(instances[randomData][7]); //true ou false
    }

}
