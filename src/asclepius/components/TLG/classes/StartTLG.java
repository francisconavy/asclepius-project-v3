package asclepius.components.TLG.classes;

import asclepius.components.Hermes.classes.Hermes;
import asclepius.components.Hermes.interfaces.IHermes;
import asclepius.components.TLG.interfaces.IStartTLG;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class StartTLG implements IStartTLG {
    public void start(Hermes her){
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new TLG(her));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}
