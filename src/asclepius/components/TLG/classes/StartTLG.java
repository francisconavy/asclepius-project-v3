package asclepius.components.TLG.classes;

import asclepius.components.Hermes.classes.Hermes;
import asclepius.components.Hermes.interfaces.IHermes;
import asclepius.components.TLG.interfaces.IStartTLG;
import asclepius.components.TLG.interfaces.ITLG;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.BotSession;

public class StartTLG implements IStartTLG {
    private TLG telegram;

    public void start(){
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(telegram = new TLG());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public ITLG getConnectionObject(){
        return telegram;
    }

    public void connect(Hermes messenger){
        telegram.setHermes(messenger);
    }

}
