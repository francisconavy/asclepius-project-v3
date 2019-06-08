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

    public void disconnect( IAPatient patient){
        patients.remove(findPatientID(patient));
    }

    private int findPatientID(IAPatient patient){
        for(int i = 0;i < patients.size(); i++){
            if(patients.get(i) == patient){
                return i;
            }
        }
        return -1;
    }

    private int findPatientID(long chat_id){
        for(int i = 0;i < patients.size(); i++){
            if(patients.get(i).getChatID() == chat_id){
                return i;
            }
        }
        return -1;
    }


    private IAPatient findPatient(long chat_id){
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
                pCont++;
            }
            else{
                int aux = findPatientID(chat_id);
                if(aux != -1){
                    this.disconnect(patients.get(aux));
                }
                IAPatient p = new APatient();
                this.connect(p);
                p.connect(this);

                IADoctor d = new ADoctor();
                d.connect(p);

                p.connect(d);

                patients.add(p);

                patients.get(patients.size() - 1).hi(messenger.getName(), chat_id);

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
