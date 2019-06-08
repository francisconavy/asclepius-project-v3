package asclepius.components.Engine.interfaces.ADoctor;

import asclepius.components.Engine.interfaces.APatient.IAPatient;
import asclepius.components.Engine.interfaces.TBC.ITree;

import java.util.ArrayList;

public interface IADoctor {
    ITree getIvTree();
    void connect(IAPatient curPatient);
    void startInterview();
    void endInterview();
    void getDiagnosis();
    void getDiagnosis(String answer);
    void dataIncrease(int value);
    int getDataIncreaseStatus();

    ArrayList<String> getResult();
}
