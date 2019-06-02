package asclepius.components.TLG.classes;

import asclepius.components.Hermes.classes.Hermes;
import asclepius.components.TLG.interfaces.ITLG;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TLG extends TelegramLongPollingBot implements ITLG{

    private String[] dict = {"Sim", "sim", "Não", "não", "/start"};
    private Hermes hermes;
    private String text;
    private String name;
    private long chat_id;

    public void connect(Hermes hermes){
        this.hermes = hermes;
    }

    public void onUpdateReceived(Update update) {
        this.text = update.getMessage().getText();
        this.name = update.getMessage().getFrom().getFirstName();
        this.chat_id = update.getMessage().getChatId();

        hermes.takeOut(text);

//        if(!inDict(text))
//            sendText(update, "Me desculpe, mas não pude te compreender");

        //else if(text.equals("/start")){
//            SendMessage message = new SendMessage() // Create a message object object
//                    .setChatId(chat_id)
//                    .setText("Vamos iniciar a consulta "+nome+"?" );
//            // Create ReplyKeyboardMarkup object
//            ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
//            // Create the keyboard (list of keyboard rows)
//            List<KeyboardRow> keyboard = new ArrayList<>();
//            // Create a keyboard row
//            KeyboardRow row = new KeyboardRow();
//            // Set each button, you can also use KeyboardButton objects if you need something else than text
//            row.add("Sim");
//            // Add the first row to the keyboard
//            keyboard.add(row);
//            // Create another keyboard row
//            row = new KeyboardRow();
//            // Set each button for the second line
//            row.add("Não");
//            // Add the second row to the keyboard
//            keyboard.add(row);
//            // Set the keyboard to the markup
//            keyboardMarkup.setKeyboard(keyboard);
//            // Add it to the message
//            message.setReplyMarkup(keyboardMarkup);
//            try {
//                execute(message);
//            } catch (TelegramApiException e) {
//                e.printStackTrace();
//            }
//        }
//        else{
//            postman.takeOut(text);
//        }

        //System.out.println(her.sendOut());
    }

    public boolean inDict(String text){
        for(int i = 0; i < dict.length; i++)
            if(text.equals(dict[i]))
                return true;
        return false;
    }

    public void sendText(String text){
        SendMessage message = new SendMessage();
        message.setText(text+"\n");
        message.setChatId(chat_id);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
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
