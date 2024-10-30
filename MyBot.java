package org.example;

import net.thauvin.erik.crypto.CryptoPrice;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class MyBot extends TelegramLongPollingBot {

    public MyBot(){
        super("7242562600:AAHMyjH3b9Wvc1SDdA8VxzqUVtvGTuE8dIA");
    }
    @Override
    public void onUpdateReceived(Update update) {
        var chatId = update.getMessage().getChatId();
        var text = update.getMessage().getText().toLowerCase();

        try {

            if (text.equals("/start")) {
                sendMessage(chatId,"Hello!");
            } else if (text.equals("btc")) {
                sendPicture(chatId, "bitcoin-btc-logo.png");
                sendPrice(chatId, "BTC");
            } else if (text.equals("eth")) {
                sendPicture(chatId, "ethereum-eth-logo.png");
                sendPrice(chatId, "ETH");
            } else if (text.equals("doge")) {
                sendPicture(chatId, "dogecoin-doge-logo.png");
                sendPrice(chatId, "DOGE");
            } else if (text.equals("/all")) {
                sendPrice(chatId, "BTC");
                sendPrice(chatId, "ETH");
                sendPrice(chatId, "DOGE");
            } else {
                sendMessage(chatId,"Unknown command!");
            }

        } catch (Exception e) {
            System.out.println("Error!");
        }
    }
    void sendPicture(long chatId, String name) throws Exception{
        var photo = getClass().getClassLoader().getResourceAsStream(name);
        var message = new SendPhoto();
        message.setChatId(chatId);
        message.setPhoto(new InputFile(photo, name));
        execute(message);
    }
    void sendPrice(long chatId, String name) throws Exception {
        var price = CryptoPrice.spotPrice(name);
        sendMessage(chatId, name + "price: " + price.getAmount().doubleValue());
    }

    void sendMessage(long chatId, String text) throws Exception {
        var message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);
        execute(message);
    }
    @Override
    public String getBotUsername() {
        return "CalculadorProg_bot";
    }
}
