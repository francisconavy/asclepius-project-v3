package asclepius.components.Hermes.classes;

import asclepius.components.Engine.classes.Patient.Patient;
import asclepius.components.Engine.interfaces.Patient.IPatient;
import asclepius.components.Hermes.interfaces.IHermes;
import asclepius.components.TLG.classes.TLG;
import asclepius.components.TLG.interfaces.ITLG;
import org.telegram.telegrambots.meta.api.objects.Update;

public class Hermes implements IHermes {

    private ITLG messenger;
    private IPatient patient;
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

    public void connect(IPatient patient){
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
            patient.symAnswer("t");
        }
        else if(text.equalsIgnoreCase("Não") || text.equalsIgnoreCase("Nao")){
            patient.symAnswer("f");
        }

    }

    public void takeIn(String text){
        fromIn = text;
        if(inDict(patient.getAttributes(), text)){
            messenger.sendText("Você tem "+text+"?");
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
