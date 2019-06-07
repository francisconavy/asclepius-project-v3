package asclepius.components.Hermes.classes;

import asclepius.components.Engine.classes.ADoctor.ADoctor;
import asclepius.components.Engine.classes.APatient.APatient;
import asclepius.components.Engine.interfaces.ADoctor.IADoctor;
import asclepius.components.Engine.interfaces.APatient.IAPatient;
import asclepius.components.Hermes.interfaces.IHermes;
import asclepius.components.TLG.interfaces.ITLG;

import java.util.ArrayList;
import java.util.List;

public class Hermes implements IHermes {

    private int pCont = 0;//contador de pacientes

    private ITLG messenger;

    private List<IAPatient> patients = new ArrayList<>();

    public void connect(ITLG messenger){
        this.messenger = messenger;
    }

    public void connect(IAPatient patient){
        this.patients.add(patient);
    }

    public IAPatient findPatient(long chat_id){
        for(IAPatient p : patients){
            if(p.getChatID() == chat_id){
                return p;
            }
        }
        return null;
    }

    public void takeOut(String text, long chat_id){
        if(text.equalsIgnoreCase("/start")) {
            if(pCont == 0) {
                patients.get(patients.size() - 1).hi(messenger.getName(), chat_id);
                //System.out.println("entrei aqui");
                pCont++;
            }
            else{
                IAPatient p = new APatient();
                this.connect(p);
                p.connect(this);

                IADoctor d = new ADoctor();
                d.connect(p);

                p.connect(d);

                patients.add(p);

                patients.get(patients.size() - 1).hi(messenger.getName(), chat_id);
                //System.out.println("---- " + patients.get(patients.size() -1).getChatID() + " " +
                 //       patients.get(patients.size() -1).getName()  + " ----");
                pCont++;
            }
        }
        else {
            IAPatient patient = findPatient(chat_id);
            //System.out.println(chat_id + " " +  patient.getName() + " " + patient.getChatID());
            patient.understand(text);
        }
    }


    public void takeIn(String text, String[][] teclado, long chat_id){
        messenger.sendText(text, teclado, chat_id);
    }

    public void takeIn(String text, long chat_id){
        messenger.sendText(text, chat_id);
    }

}
