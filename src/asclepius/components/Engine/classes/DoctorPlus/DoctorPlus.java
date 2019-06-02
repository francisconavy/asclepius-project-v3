package asclepius.components.Engine.classes.DoctorPlus;

import asclepius.components.Engine.classes.TBC.DataTree;
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
                
                //Chegagem de condição de parada antes da reiteração (perguntas vão parar aqui se uma das condições for cumprida)
                /*if(diag.size() == 1){
                    break;
                }*/
                
                System.out.println(diag);
            }
            
        }else{
            //Se a conexão não foi efeutada com sucesso...
            System.out.println("WARING: curPatient is null at 'startInterview()' doctor's call.");
        }
    }
}
