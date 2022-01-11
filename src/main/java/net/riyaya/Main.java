package net.riyaya;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.riyaya.DataBase.Config;
import net.riyaya.Utils.Generator;
import net.riyaya.Utils.Logger;

import java.util.Timer;

public class Main {
    public static Config                config;
    public static JDABuilder            jdaBuilder;
    public static JDA                   jda;

    public static void main(String[] args) {
        config = new Config();
        config.load();

        if(config.getBotToken().equals("TOKEN")) {
            Logger.warn("Please change bot token in config.json");
            System.exit(0);
        }

        try {
            jdaBuilder = JDABuilder.createDefault(config.getBotToken());
            jda = jdaBuilder.build();
            jda.awaitReady();

            Logger.info("Successfully login discord bot");
        } catch (Exception e) {
            Logger.warn("Couldn't login discord bot");
            Logger.warn("Bot Token was wrong?");
            Logger.warn(e.toString());
            System.exit(0);
        }
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new Generator(), 3000, config.getGenSec() * 1000L);
    }
}
