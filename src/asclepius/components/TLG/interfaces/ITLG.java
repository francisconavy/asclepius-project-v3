package asclepius.components.TLG.interfaces;

import asclepius.components.Hermes.classes.Hermes;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface ITLG {
    void setHermes(Hermes messenger);
    boolean inDict(String text);

    //pensar na real necessidade dessas funções abaixo na interface, pois são apenas do telegram.
    //acho que deviamos criar uma interface comunicado, não uma telegram. Pois telegram já temos a classe e a interface deveria
    //ser algo mais genérico

    void onUpdateReceived(Update update);
    String getBotUsername();
    String getBotToken();
}
