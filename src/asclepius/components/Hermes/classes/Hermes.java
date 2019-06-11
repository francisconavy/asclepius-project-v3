package asclepius.components.Hermes.classes;

import asclepius.components.Engine.classes.ADoctor.ADoctor;
import asclepius.components.Engine.classes.APatient.APatient;
import asclepius.components.Engine.interfaces.ADoctor.IADoctor;
import asclepius.components.Engine.interfaces.APatient.IAPatient;
import asclepius.components.Hermes.interfaces.IHermes;
import asclepius.components.TLG.interfaces.ITLG;
import jsmaiorjava.implementations.ImprimeAtestado;
import jsmaiorjava.implementations.Prontuario;
import jsmaiorjava.interfaces.IImprimeAtestado;
import jsmaiorjava.interfaces.IProntuario;

import java.io.File;
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
            messenger.sendImage("resources/images/symbol.png", chat_id);

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
            if(findPatientID(chat_id) == -1){
                String[][] teclado = {{"/start"}};
                takeIn("Precione /start para começar a consulta", teclado, chat_id);
                return;
            }
            IAPatient patient = findPatient(chat_id);
            patient.understand(text);
        }
    }


    public void takeIn(String text, String[][] teclado, long chat_id){
        messenger.sendText(text, teclado, chat_id);
    }

    public void takeInAt(String text, long chat_id){
        IProntuario pront = new Prontuario("Asclepius", this.findPatient(chat_id).getName(), text);
        IImprimeAtestado atest = new ImprimeAtestado(pront);
        atest.imprime("resources/atestados/");
        messenger.sendDocument("resources/atestados/atestado1.pdf", "atestado.pdf",  chat_id);
        File arq = new File("resources/atestados/atestado1.pdf");
        arq.delete();
    }

    public void takeIn(String text, long chat_id){
        messenger.sendText(text, chat_id);
    }
}
