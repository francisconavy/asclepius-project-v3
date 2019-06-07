package asclepius.components.Hermes.interfaces;

import java.util.ArrayList;

public interface IHermes {
    void takeOut(String text, long chat_id);
    void takeIn(String text, long chat_id);
    void takeIn(String text, String[][] teclado, long chat_id);
}
