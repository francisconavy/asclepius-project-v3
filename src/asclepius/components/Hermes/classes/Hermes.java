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

    public void connect(ITLG messenger){
        this.messenger = messenger;
    }

    public void connect(IPatient patient){
        this.patient = patient;
    }

    public void takeOut(String text){
        fromOut = text;
        if(text.equalsIgnoreCase("/start")){
            patient.isReady();
        }
    }

    public void takeIn(String text){
        fromIn = text;
        messenger.sendText(text);
    }

    public String sendOut(){
        return fromOut;
    }

    public String sendIn(){
        return fromIn;
    }

}
