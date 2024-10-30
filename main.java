package org.example;


import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {
    public static void main(String[] args) throws Exception {

        var api = new TelegramBotsApi(DefaultBotSession.class);
        api.registerBot(new MyBot());

    }
}

//CalculadorProg_bot
//7242562600:AAHMyjH3b9Wvc1SDdA8VxzqUVtvGTuE8dIA
