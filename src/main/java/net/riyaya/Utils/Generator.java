package net.riyaya.Utils;

import net.riyaya.Main;

import java.util.Random;
import java.util.TimerTask;

public class Generator extends TimerTask {
    @Override
    public void run() {
        Main.jda.getTextChannelById(Main.config.getGenChannel()).sendMessage("https://discord.gift/" + getCode()).queue();
    }

    private String getCode() {
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder code = null;
        Random random = new Random();

        for(int i = 0; i < 16; i++) {
            if(code == null) {
                code = new StringBuilder(letters.charAt(random.nextInt(62)));
            }else {
                code.append(letters.charAt(random.nextInt(62)));
            }
        }
        return code.toString();
    }
}
