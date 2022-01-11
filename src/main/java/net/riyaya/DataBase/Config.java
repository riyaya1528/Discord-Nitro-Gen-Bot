package net.riyaya.DataBase;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import net.riyaya.Utils.Logger;

import java.nio.file.Paths;

public class Config {
    private static ObjectMapper mapper;
    private static String       json;
    private static JsonNode     jsonNode;
    private static ObjectNode   objectNode;

    private static String       token, genChannel;
    private static int          genSec;


    public void load() {
        mapper = new ObjectMapper();
        try {
            json                = mapper.readTree(Paths.get("./config.json").toFile()).toString();
            jsonNode            = mapper.readTree(json);
            objectNode          = jsonNode.deepCopy();

            token               = objectNode.get("token").asText();
            genSec              = objectNode.get("generate_per_sec").asInt();
            genChannel          = objectNode.get("generate_channel").asText();
        }catch (Exception e) {
            Logger.warn(e.toString());
            Logger.warn("Couldn't load config.json");
            Logger.warn(e.toString());
        }
    }

    public void save() {
        try {
            mapper.writeValue(Paths.get("./config.json").toFile(), objectNode);
        }catch (Exception e) {
            Logger.warn("Couldn't save config.json");
            Logger.warn(e.toString());
        }
    }

    public String getBotToken() {
        return token;
    }

    public int getGenSec() {
        return genSec;
    }

    public String getGenChannel() {
        return genChannel;
    }
}