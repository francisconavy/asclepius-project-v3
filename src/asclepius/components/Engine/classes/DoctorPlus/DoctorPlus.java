package asclepius.components.Engine.classes.DoctorPlus;

import asclepius.components.Engine.classes.TBC.DataTree;
import asclepius.components.Engine.classes.TBC.DiagMatrix;
import asclepius.components.Engine.constants.SetConstants;
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

            //DEV-A
            ITree ivTree = DataTree.generate(SetConstants.getDefaultSource());
            ArrayList<ArrayList<String>> diag = ivTree.diagCheck();
            System.out.println(diag); //Remover depois
            
            int diagStartSize;
            int firstArraySet = 0, stoppingCondition = 0, nonVoidFound = 0; ArrayList<String> curArray = null; //Usado na condição código 1
            ArrayList<String> result = null;
            //Fim do DEV-A

            for(int x = 0; x < ivTree.requestAttributes().length - 1; x++){
                //Cortando metade do array "diag" (seguindo um dos dois caminhos do nó da árvore)
                if(curPatient.ask(ivTree.requestAttributes()[x]).equalsIgnoreCase(SetConstants.getTrue())){ //LINK START - Mandar a X# pergunta
                    diagStartSize = diag.size();
                    
                    for(int y = 0; y < diagStartSize/2; y++){
                        diag.remove(0);
                    }
                    
                }else if(curPatient.ask(ivTree.requestAttributes()[x]).equalsIgnoreCase(SetConstants.getFalse())){
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
                    System.out.println("Condição 0 acionada."); //Remover depois...
                    for(int a = 0; a < diag.size(); a++) { //Percorre diag e o diagnóstico é esse array-não vazio;
                        if (diag.get(a).isEmpty() == false) {
                            result = diag.get(a);
                            break;
                        }
                    }
                    break; //Deve ser trocado pelo retorno.
                }else if(DiagMatrix.verify(diag) == 1){ //Se há 2 ou mais arrays não-vazios...
                    //DEV-B
                    System.out.println("Condição 1 acionada."); //Remover depois...
                    for(int b = 0; b < diag.size(); b++) {
                        if(diag.get(b).isEmpty() == false) {
                            if (firstArraySet == 0) {
                                nonVoidFound++;
                                curArray = diag.get(b);
                                firstArraySet = 1;

                            } else if (firstArraySet == 1) {
                                nonVoidFound++;
                                if ((diag.get(b).containsAll(curArray) && curArray.containsAll(diag.get(b))) == false) { //Se TODOS os arrays não-vazios de diagnósticos até agora NÃO SÃO IGUAIS...
                                    break;
                                } else if ((diag.get(b).containsAll(curArray) && curArray.containsAll(diag.get(b))) == true) { //Se TODOS os arrays não-vazios de diagnósticos até agora SÃO IGUAIS...
                                    stoppingCondition++;
                                }
                            }
                        }
                    }
                    if(nonVoidFound - 1 == stoppingCondition){ //Se a condição de parada foi atingida... //DEV-C
                        result = curArray;
                        firstArraySet = 0;
                        stoppingCondition = 0;
                        nonVoidFound = 0;
                        System.out.println("Condição 1 quebrou o ciclo...!");
                        break;
                    }else { //Se a condição de parada não foi atingida... Vá para a próxima pergunta.
                        firstArraySet = 0;
                        stoppingCondition = 0;
                        nonVoidFound = 0;
                    }
                    //DEV-D /|\ Mandar para o paciente a próxima pergunta

                }else if(DiagMatrix.verify(diag) == 2){ //Se todos os arrays são vazios...
                    System.out.println("Condição 2 acionada."); //Remover depois...
                    System.out.println("All arrays are void... Insert databuild engine here.");
                    result = null;
                }else{
                    System.out.println("Condição 3 acionada - ERRO."); //Remover depois...
                    System.out.println("ERROR: Something went wrong during DiagMatrix's verify(); - bad list.");
                    result = null;
                }

                //Print de teste; remover depois...
                System.out.println("%: " + diag);
            }

            System.out.println("O DIAGNÓSTICO FINAL É: " + result); //Trocar essa apresentação do diagnóstico depois - remover depois do APOCALYPSE MERGE
            
        }else{
            //Se a conexão não foi efeutada com sucesso...
            System.out.println("WARING: curPatient is null at 'startInterview()' doctor's call.");
        }
    }
}
