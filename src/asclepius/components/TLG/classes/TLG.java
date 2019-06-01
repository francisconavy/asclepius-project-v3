package asclepius.components.TLG.classes;

import asclepius.components.TLG.interfaces.ITLG;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TLG extends TelegramLongPollingBot implements ITLG{

    public void onUpdateReceived(Update update) {

        /*
        SendMessage message = new SendMessage();
        message.setText("Testando se deu certo");
        message.setChatId(update.getMessage().getChatId());
        System.out.println(update.getMessage().getText());
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

         */
    }

    public String getBotUsername() {
        return null;
    }

    public String getBotToken() {
        File file = new File("resources/token.txt");
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // we just need to use \n as delimiter
        sc.useDelimiter("\n");

        String token = sc.next();
        return token;
    }
}
