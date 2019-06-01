package asclepius.components.Hermes.classes;

import asclepius.components.Hermes.interfaces.IHermes;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class Hermes implements IHermes {

    private String[] dict = {"Sim", "sim", "Não", "não", "/start"};

    public SendMessage call(Update update){
        String text = update.getMessage().getText();
        String name = update.getMessage().getFrom().getFirstName();
        long chat_id = update.getMessage().getChatId();
        SendMessage message = new SendMessage().setChatId(chat_id);

        if(!inDict(text))
            message.setText("Me desculpe, mas não pude te compreender");

        else if(text.equals("/start")) {
            message.setText("Bom dia "+name+ ". Vamos iniciar a consulta ?");
            // Create ReplyKeyboardMarkup object
            ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
            // Create the keyboard (list of keyboard rows)
            List<KeyboardRow> keyboard = new ArrayList<>();
            // Create a keyboard row
            KeyboardRow row = new KeyboardRow();
            // Set each button, you can also use KeyboardButton objects if you need something else than text
            row.add("Sim");
            // Add the first row to the keyboard
            keyboard.add(row);
            // Create another keyboard row
            row = new KeyboardRow();
            // Set each button for the second line
            row.add("Não");
            // Add the second row to the keyboard
            keyboard.add(row);
            // Set the keyboard to the markup
            keyboardMarkup.setKeyboard(keyboard);
            // Add it to the message
            message.setReplyMarkup(keyboardMarkup);
        }

        //message.setText(name+" disse: "+text).setChatId(chat_id);

        return message;

    }

    public boolean inDict(String text){
        for(int i = 0; i < dict.length; i++)
            if(text.equals(dict[i]))
                return true;
        return false;
    }
}
