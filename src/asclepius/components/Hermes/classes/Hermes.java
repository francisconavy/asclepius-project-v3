package asclepius.components.Hermes.classes;

import asclepius.components.Engine.classes.Patient.Patient;
import asclepius.components.Engine.constants.SetConstants;
import asclepius.components.Engine.interfaces.APatient.IAPatient;
import asclepius.components.Engine.interfaces.Patient.IPatient;
import asclepius.components.Hermes.interfaces.IHermes;
import asclepius.components.TLG.classes.TLG;
import asclepius.components.TLG.interfaces.ITLG;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;

public class Hermes implements IHermes {

    private ITLG messenger;
    private IAPatient patient;
    private String fromOut;
    private String fromIn;

    //private String[] dict = {"Sim", "sim", "Não", "não", "/start"};

    public boolean inDict(String[] dict, String text){
        for(int i = 0; i < dict.length; i++)
            if(text.equals(dict[i]))
                return true;
        return false;
    }

    public void connect(ITLG messenger){
        this.messenger = messenger;
    }

    public void connect(IAPatient patient){
        this.patient = patient;
    }

    public void takeOut(String text){
        fromOut = text;
        if(text.equalsIgnoreCase("/start")){
            patient.hi();
        }
        else if(text.equalsIgnoreCase("Vamos!") || text.equalsIgnoreCase("Vamos")){
            patient.isReady();
        }
        else if(text.equalsIgnoreCase("Sim")){
            patient.symAnswer(SetConstants.getTrue());
        }
        else if(text.equalsIgnoreCase("Não") || text.equalsIgnoreCase("Nao")){
            patient.symAnswer(SetConstants.getFalse());
        }

    }

    public void takeIn(String text){
        fromIn = text;
        if(inDict(patient.getDoc().getIvTree().requestAttributes(), text)){
            messenger.sendText("Você está com "+text+"?");
        }
        else if(text.equalsIgnoreCase("Diagnostico Final")){
            ArrayList<String> result = patient.getDoc().getResult();
            if(result==null){
                messenger.sendText(("Não identificamos nenhuma doença"));
            }
            else{
                /*messenger.sendText(("Possíveis doenças:"));
                for(int i = 0; i < result.size(); i++){
                    messenger.sendText("Suspeita "+(i+1)+": "+result.get(i));
                    System.out.println("Disease guess: " + result.get(i) );
                }*/

                String msg = "Diagnóstico concluído:\nVocê está com ";
                for(int i = 0; i < result.size(); i++){
                    msg = msg + result.get(i);

                    if(result.size() > 1){
                        if(i < (result.size() - 2)) {
                            msg = msg + ", ";
                        }else if(i == (result.size() - 2)) {
                            msg = msg + " e ";
                        }else if(i == (result.size() - 1)) {
                            msg = msg + ".";
                        }
                    }else{
                        msg = msg + ".";
                    }
                }
                messenger.sendText(msg);
            }
        }
        else {
            messenger.sendText(text);
        }
    }

    public String sendOut(){
        return fromOut;
    }

    public String sendIn(){
        return fromIn;
    }

}
