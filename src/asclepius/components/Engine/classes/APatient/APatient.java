package asclepius.components.Engine.classes.APatient;

import asclepius.components.Engine.interfaces.ADoctor.IADoctor;
import asclepius.components.Engine.interfaces.APatient.IAPatient;
import asclepius.components.Engine.interfaces.all.IEnquirer;
import asclepius.components.Hermes.interfaces.IHermes;

public class APatient implements IAPatient {
    private int curSym = 0;

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

    public void hi(){
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
        hermes.takeIn(question);
    }

    @Override
    public void tellDisease(String disease) {
        hermes.takeIn(disease);
    }

    @Override
    public int getCurSym() {
        return this.curSym;
    }
}
