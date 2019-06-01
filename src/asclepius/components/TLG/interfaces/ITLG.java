package asclepius.components.TLG.interfaces;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface ITLG {
    void onUpdateReceived(Update update);
    String getBotUsername();
    String getBotToken();
}
