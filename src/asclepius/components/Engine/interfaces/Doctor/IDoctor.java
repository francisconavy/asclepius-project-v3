package asclepius.components.Engine.interfaces.Doctor;
import asclepius.components.Engine.interfaces.all.*;

public interface IDoctor extends IEnquirer, IResponderReceptacle, ITableProducerReceptacle {
    void verifyDiagnostic(String[] diagnosis);
}