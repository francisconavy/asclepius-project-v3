package asclepius.components.Hermes.interfaces;

import asclepius.components.Engine.interfaces.APatient.IAPatient;

import java.util.ArrayList;

public interface IHermes {
    void disconnect( IAPatient patient);
    void connect(IAPatient patinent);
    void takeOut(String text, long chat_id);
    void takeIn(String text, long chat_id);
    void takeIn(String text, String[][] teclado, long chat_id);
    void takeInAt(String text, long chat_id);
}
