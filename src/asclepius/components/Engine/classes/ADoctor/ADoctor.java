package asclepius.components.Engine.classes.ADoctor;

import asclepius.components.Engine.classes.TBC.DataTree;
import asclepius.components.Engine.classes.TBC.DiagMatrix;
import asclepius.components.Engine.constants.SetConstants;
import asclepius.components.Engine.interfaces.ADoctor.IADoctor;
import asclepius.components.Engine.interfaces.APatient.IAPatient;
import asclepius.components.Engine.interfaces.TBC.ITree;
import asclepius.components.Engine.interfaces.all.IResponder;
import asclepius.components.Engine.interfaces.all.ITableProducer;

import java.util.ArrayList;
import java.util.List;

public class ADoctor implements IADoctor {
    private IAPatient curPatient;
    private ITableProducer producer;
    private IResponder responder;
    private String[] diagnostic;
    private int num_sym;
    private String attributes[];

    private ITree ivTree = DataTree.generate(SetConstants.getDefaultSource());
    private ArrayList<ArrayList<String>> diag = ivTree.diagCheck();

    private int diagStartSize;
    private int firstArraySet = 0, stoppingCondition = 0, nonVoidFound = 0; ArrayList<String> curArray = null; //Usado na condição código 1
    private ArrayList<String> result = new ArrayList<>();

    public ITree getIvTree(){
        return ivTree;
    }

    public void connect(IAPatient targetPatient){
        //System.out.println(diag); //Remover depois
        curPatient = targetPatient;
    }

    public void startInterview(){
        curPatient.ask("Olá "+curPatient.getName()+"!\nVamos iniciar a consulta?");
    }

    public void endInterview(){
        curPatient.tellDisease();
//        for(int i = 0; i < result.size(); i++){
//            curPatient.tellDisease(result.get(i));
//            System.out.println("Disease guess: " + result.get(i) );
//        }
    }

    public ArrayList<String> getResult(){
        return result;
    }

    public void getDiagnosis(){
        curPatient.ask("Irei te fazer algumas perguntas, e preciso que responda com SIM ou NÃO");
        curPatient.ask(ivTree.requestAttributes()[curPatient.getCurSym()]);
    }


    public void getDiagnosis(String answer){
        //Fase de Quebra
        if(answer.equalsIgnoreCase(SetConstants.getTrue())){ //LINK START - Mandar a X# pergunta
            diagStartSize = diag.size();

            for(int y = 0; y < diagStartSize/2; y++){
                diag.remove(0);
            }

        }else if(answer.equalsIgnoreCase(SetConstants.getFalse())){
            diagStartSize = diag.size();

            for(int y = 0; y < diagStartSize/2; y++){
                diag.remove(diagStartSize/2);
            }

        }else{
            //System.out.println("WARNING: 'unknown' answer from curPatient.");
            return; //@TODO Trocar mecanismo que retorne erro
        }
        //Fim da Fase de Quebra
        //System.out.println(diag); //Remover depois
        //Fase de Checagem
        //Checagem de condição de parada antes da reiteração (perguntas vão parar aqui se uma das condições for cumprida)
        if(DiagMatrix.verify(diag) == 0) { //Se há apenas 1 array não-vazio...
            //System.out.println("Condição 0 acionada."); //Remover depois...
            for (int a = 0; a < diag.size(); a++) { //Percorre diag e o diagnóstico é esse array-não vazio;
                if (diag.get(a).isEmpty() == false) {
                    result = diag.get(a);
                    break;
                }
            }
            endInterview();
        }else if(DiagMatrix.verify(diag) == 1){ //Se há 2 ou mais arrays não-vazios...
            //DEV-B
            //System.out.println("Condição 1 acionada."); //Remover depois...
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
                //System.out.println("Condição 1 quebrou o ciclo...!");
                endInterview();
            }else { //Se a condição de parada não foi atingida... Vá para a próxima pergunta.
                firstArraySet = 0;
                stoppingCondition = 0;
                nonVoidFound = 0;
                curPatient.ask(ivTree.requestAttributes()[curPatient.getCurSym()]);
            }
        }else if(DiagMatrix.verify(diag) == 2){ //Se todos os arrays são vazios...
            //System.out.println("Condição 2 acionada."); //Remover depois...
            //System.out.println("All arrays are void... Insert databuild engine here.");
            result.add("Doença não identificada");
            endInterview();
        }else{
            //System.out.println("Condição 3 acionada - ERRO."); //Remover depois...
            //System.out.println("ERROR: Something went wrong during DiagMatrix's verify(); - bad list.");
            result.add("Que porra é essa");
        }
    }
}

