package asclepius.components.TLG.interfaces;

import asclepius.components.Hermes.classes.Hermes;

public interface IStartTLG {
    void start();
    ITLG getConnectionObject();
    void connect(Hermes messenger);
}
