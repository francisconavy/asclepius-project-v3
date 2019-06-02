package asclepius.components.Engine.interfaces.Patient;

import asclepius.components.Engine.interfaces.all.IEnquirerReceptacle;
import asclepius.components.Engine.interfaces.all.IResponder;
import asclepius.components.Engine.interfaces.all.ITableProducerReceptacle;
import asclepius.components.Hermes.interfaces.IHermes;

public interface IPatient extends IResponder, IEnquirerReceptacle, ITableProducerReceptacle {
    void isReady();
    void connect(IHermes hermes);
}
