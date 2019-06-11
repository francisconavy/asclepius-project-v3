package asclepius.components.TLG.classes;

import asclepius.components.Hermes.classes.Hermes;
import asclepius.components.TLG.interfaces.ITLG;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TLG extends TelegramLongPollingBot implements ITLG{
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
        //System.out.println("@TELEGRAM: "+text);
        hermes.takeOut(text, chat_id);
    }

    public void sendText(String text, String[][] teclado, long chatID){
        SendMessage message = new SendMessage() // Create a message object object
            .setChatId(chatID)
            .setText(text+"\n");
        message.setReplyMarkup(keyboardFactory(teclado));
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendText(String text, long chatID){
        SendMessage message = new SendMessage();
        message.setText(text+"\n");
        message.setChatId(chatID);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendImage(String file, long chatID){
        InputStream inputStream;
        try {
            inputStream = new FileInputStream(file);
            SendPhoto message = new SendPhoto().setPhoto(text, inputStream);
            message.setChatId(chatID);
            execute(message);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void sendDocument(String file, String name, long chatID){
        InputStream inputStream;
        try {
            inputStream = new FileInputStream(file);
            SendDocument message = new SendDocument().setDocument(name, inputStream);
            message.setChatId(chatID);
            execute(message);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ReplyKeyboardMarkup keyboardFactory(String[][] teclado){
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboard = new ArrayList<>();
        for(int i = 0; i < teclado.length; i++){
            KeyboardRow row = new KeyboardRow();
            for(int j = 0; j < teclado[i].length; j++){
                row.add(teclado[i][j]);
            }
            keyboard.add(row);
        }
        return keyboardMarkup.setKeyboard(keyboard);
    }

    public String getName(){
        return name;
    }

    public long getChatID(){
        return chat_id;
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
