package asclepius.components.TLG.interfaces;

import asclepius.components.Hermes.classes.Hermes;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.lang.reflect.Array;
import java.util.ArrayList;

public interface ITLG {

    void connect(Hermes hermes);

    String getName();
    long getChatID();

    void sendText(String text, long chatID);
    void sendText(String text, String[][] teclado, long chatID);
    void sendImage(String file, long chatID);
    void sendDocument(String file, long chatID);


}
