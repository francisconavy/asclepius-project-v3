package asclepius.components.Engine.classes.DoctorPlus;

import asclepius.components.Engine.classes.TBC.DataTree;
import asclepius.components.Engine.classes.TBC.DiagMatrix;
import asclepius.components.Engine.interfaces.DoctorPlus.IDoctorPlus;
import asclepius.components.Engine.interfaces.PatientPlus.IPatientPlus;
import asclepius.components.Engine.interfaces.TBC.ITree;
import java.util.ArrayList;

public class DoctorPlus implements IDoctorPlus {
    private IPatientPlus curPatient;
    private String answer = "";
    
    public DoctorPlus(IPatientPlus targetPatient) {
        this.connect(targetPatient);
    }
    
    @Override
    public void connect(IPatientPlus targetPatient) {
        curPatient = targetPatient;
    }

    @Override
    public void startInterview() {
    	//Verifica ser foi efetuada a conexão
        if(curPatient != null){
            //Se a conexão foi efetuada com sucesso...
            ITree ivTree = DataTree.generate("resources\\data\\test-cases-minus.csv");
            ArrayList<ArrayList<String>> diag = ivTree.diagCheck();
            System.out.println(diag); //Remover depois
            
            int diagStartSize;
            
            for(int x = 0; x < ivTree.requestAttributes().length - 1; x++){
                //Cortando metade do array "diag" (seguindo um dos dois caminhos do nó da árvore)
                if(curPatient.ask(ivTree.requestAttributes()[x]).equalsIgnoreCase("t")){
                    diagStartSize = diag.size();
                    
                    for(int y = 0; y < diagStartSize/2; y++){
                        diag.remove(0);
                    }
                    
                }else if(curPatient.ask(ivTree.requestAttributes()[x]).equalsIgnoreCase("f")){
                    diagStartSize = diag.size();
                    
                    for(int y = 0; y < diagStartSize/2; y++){
                        diag.remove(diagStartSize/2);
                    }
                    
                }else{
                    System.out.println("WARNING: 'unknown' answer from curPatient.");
                    break;
                }
                
                //Checagem de condição de parada antes da reiteração (perguntas vão parar aqui se uma das condições for cumprida)
                if(DiagMatrix.verify(diag) == 0){ //Se há apenas 1 array não-vazio...
                    /*for(int x = 0; x < diag.size(); x++) { //Percorre diag e retorna esse array-não vazio;
                        if (diag.get(x).isEmpty() == false) {
                            return diag.get(x);
                        }
                    }*/
                    //VOCÊ PAROU AQUI - ENCONTRE UMA FORMA DE FAZER COM QUE AS CONDIÇÕES 1, 2 E 3 CAUSEM A QUEBRA DO LOOP E UMA RESPOSTA FINAL SEJA DADA PELO MÉDICO (NÃO TEM RELAÇÃO NENHUMA COM O MÉTODO finalAnswer() CRIADO PELO SANTANCHE).
                }else if(DiagMatrix.verify(diag) == 1){ //Se há 2 ou mais arrays não-vazios...
                    /**/
                }else if(DiagMatrix.verify(diag) == 2){ //Se todos os arrays são vazios...
                    /**/
                }else{
                    System.out.println("ERROR: Something went wrong during DiagMatrix's verify(); - bad list.");
                }

                //Print de teste; remover depois...
                System.out.println(diag);
            }
            
        }else{
            //Se a conexão não foi efeutada com sucesso...
            System.out.println("WARING: curPatient is null at 'startInterview()' doctor's call.");
        }
    }
}
