package asclepius.components.Engine.classes.APatient;

import asclepius.components.Engine.classes.ADoctor.ADoctor;
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
    private ArrayList<String> symVec = new ArrayList<>();

    private IADoctor doctor;
    private IHermes hermes;

    public void connect(IADoctor doctor){
        this.doctor = doctor;
    }

    public IADoctor getDoc(){
        return doctor;
    }

    public String getName(){
        return name;
    }

    public long getChatID(){ return chatID; }

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
        symVec.add(answer);
        doctor.getDiagnosis(answer);
    }

    @Override
    public void ask(String question) {
        if (question.contains("iniciar a consulta")) {
            String[][] teclado = {{"Vamos"},
                    {"Agora não"}};
            hermes.takeIn(question, teclado, chatID);
        } else if (question.contains("perguntas")) {
            hermes.takeIn(question, chatID);
        } else if (question.contains("dados")){
            String[][] teclado = {{"Claro!"},
                    {"Não, obrigado..."}};
            hermes.takeIn(question, teclado, chatID);
        }
        else{
            String[][] teclado = {{"sim", "não"}};
            hermes.takeIn("Você está com " + question + "?", teclado, chatID);
        }
    }

    public void itsAJoke(String joke){
        hermes.takeIn(joke, chatID);
    }

    @Override
    public void tellDisease() {
        ArrayList<String> result = getDoc().getResult();
        int dataIncreaseActive = doctor.getDataIncreaseStatus();

        if(dataIncreaseActive == 0) {
            if(result.contains("dataIncreaseNegated") == false) {
                String msg1 = "Diagnóstico concluído:\nVocê está com ";
                String msg="";
                for (int i = 0; i < result.size(); i++) {
                    msg = msg + result.get(i);

                    if (result.size() > 1) {
                        if (i < (result.size() - 2)) {
                            msg = msg + ", ";
                        } else if (i == (result.size() - 2)) {
                            msg = msg + " e ";
                        } else if (i == (result.size() - 1)) {
                            msg = msg + ".";
                        }
                    } else {
                        msg = msg + ".";
                    }
                }
                String[][] teclado = {{"Nova consulta"}, {"Encerrar consulta"}};
                hermes.takeIn(msg1+msg, teclado, chatID);
                hermes.takeInAt(msg, chatID);
            } else {
                String msg = "Você não quer ajudar? Certo. Tenha um bom dia e não espere por mim quando estiver morrendo!\n^^";
                String[][] teclado = {{"Nova consulta"}, {"Encerrar consulta"}};
                hermes.takeIn(msg, teclado, chatID);
            }
        }else{
            String msg = "" + result.get(0);
            String[][] teclado = {{"Nova consulta"}, {"Encerrar consulta"}};
            hermes.takeIn(msg, teclado, chatID);
            hermes.takeInAt(msg, chatID);
        }
    }

    public void understand(String text) {
        if(status == 0)
            return;
        if (text.equalsIgnoreCase("Vamos")) {
            status++;
            isReady();
        }
        else if(text.equalsIgnoreCase("Agora não")){
            String[][] teclado = {{"/start"}};
            hermes.takeIn("Até a próxima", teclado, chatID);
            hermes.disconnect(this);
        }
        else if (text.equalsIgnoreCase("Sim")) {
            symAnswer(SetConstants.getTrue());
        }
        else if (text.equalsIgnoreCase("Não") || text.equalsIgnoreCase("Nao")) {
            symAnswer(SetConstants.getFalse());
        }
        else if (text.equalsIgnoreCase("Nova consulta")) {
            IADoctor d = new ADoctor();
            d.connect(this);
            this.connect(d);
            this.hi(name, chatID);
            curSym = 0;
            status = 1;
        }
        else if (text.equalsIgnoreCase("Encerrar consulta")) {
            String[][] teclado = {{"/start"}};
            hermes.takeIn("Até a próxima!", teclado, chatID);
            hermes.disconnect(this);
        }
        else if (text.equalsIgnoreCase("Claro!")) {
            doctor.dataIncrease(1);
        }
        else if (text.equalsIgnoreCase("Não, obrigado...")) {
            doctor.dataIncrease(0);
        }
        else{
            hermes.takeIn("Não consegui te entender, você poderia responder à pergunta feita anteriormente utilizando os botões por favor?", chatID);
        }
    }

    @Override
    public int getCurSym() {
        return this.curSym;
    }

    @Override
    public ArrayList<String> getSymVec(){
        return this.symVec;
    }

}
