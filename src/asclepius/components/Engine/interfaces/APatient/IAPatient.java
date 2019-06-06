package asclepius.components.Engine.interfaces.APatient;

import asclepius.components.Engine.interfaces.ADoctor.IADoctor;
import asclepius.components.Hermes.interfaces.IHermes;

public interface IAPatient {
    void ask(String question);
    void tellDisease(String disease);
    int getCurSym();

    void connect(IADoctor doctor);
    IADoctor getDoc();
    void connect(IHermes hermes);

    void hi();
    void isReady();
    void symAnswer(String answer);



}
