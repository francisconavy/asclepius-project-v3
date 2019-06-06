package asclepius.components.Hermes.classes;

import asclepius.components.Engine.interfaces.APatient.IAPatient;
import asclepius.components.Hermes.interfaces.IHermes;
import asclepius.components.TLG.interfaces.ITLG;

public class Hermes implements IHermes {

    private ITLG messenger;
    private IAPatient patient;

    public void connect(ITLG messenger){
        this.messenger = messenger;
    }

    public void connect(IAPatient patient){
        this.patient = patient;
    }

    public void takeOut(String text){
        if(text.equalsIgnoreCase("/start")){
            patient.hi(messenger.getName(), messenger.getChatID());
        }
        else{
            patient.understand(text);
        }
    }


    public void takeIn(String text){
        messenger.sendText(text);
    }

}
