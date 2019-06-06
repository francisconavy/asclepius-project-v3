package asclepius.components.Engine.interfaces.APatient;

import asclepius.components.Engine.interfaces.ADoctor.IADoctor;
import asclepius.components.Hermes.interfaces.IHermes;

public interface IAPatient {
    void ask(String question);
    void tellDisease();
    int getCurSym();

    void connect(IADoctor doctor);
    IADoctor getDoc();
    void connect(IHermes hermes);

    void hi(String nome, long chatID);
    void isReady();
    void symAnswer(String answer);


    void understand(String text);

    String getName();
}
