package asclepius.components.Engine.classes.APatient;

import asclepius.components.Engine.constants.SetConstants;
import asclepius.components.Engine.interfaces.ADoctor.IADoctor;
import asclepius.components.Engine.interfaces.APatient.IAPatient;
import asclepius.components.Hermes.interfaces.IHermes;

import java.util.ArrayList;

public class APatient implements IAPatient {
    private int curSym = 0;
    private String name;
    private long chatID;
    private int status=0;

    private IADoctor doctor;
    private IHermes hermes;

    public void connect(IADoctor doctor){
        this.doctor = doctor;
    }

    public IADoctor getDoc(){
        return doctor;
    }

    public void connect(IHermes hermes){
        this.hermes = hermes;
    }

    public void hi(String nome, long chatID){
        this.name = nome;
        this.chatID = chatID;
        status++;
        doctor.startInterview();
    }

    public void isReady() {
        doctor.getDiagnosis();
    }

    public void symAnswer(String answer) {
        curSym++;
        doctor.getDiagnosis(answer);
    }

    @Override
    public void ask(String question) {
        if(question.equalsIgnoreCase("Vamos iniciar a consulta?")){
            hermes.takeIn(question);
        }
        else if(question.contains("perguntas")){
            hermes.takeIn(question);
        }
        else{
            hermes.takeIn("Você está com "+question+"?");
        }
    }

    @Override
    public void tellDisease() {
        String msg = "Diagnóstico concluído:\nVocê está com ";
        ArrayList<String> result = getDoc().getResult();
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
        hermes.takeIn(msg);
    }

    public void understand(String text){
        if(status == 1){
            status++;
            isReady();
        }
        else if(text.equalsIgnoreCase("Sim")){
            symAnswer(SetConstants.getTrue());
        }
        else if(text.equalsIgnoreCase("Não") || text.equalsIgnoreCase("Nao")){
            symAnswer(SetConstants.getFalse());
        }
    }

    @Override
    public int getCurSym() {
        return this.curSym;
    }
}
