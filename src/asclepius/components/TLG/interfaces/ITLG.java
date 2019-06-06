package asclepius.components.TLG.interfaces;

import asclepius.components.Hermes.classes.Hermes;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface ITLG {

    void connect(Hermes hermes);

    String getName();
    long getChatID();

    void sendText(String text);
}
