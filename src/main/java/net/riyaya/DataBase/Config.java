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

    private static String       token, prefix;
    private static int          servers;


    public void load() {
        mapper = new ObjectMapper();
        try {
            json                = mapper.readTree(Paths.get("./config.json").toFile()).toString();
            jsonNode            = mapper.readTree(json);
            objectNode          = jsonNode.deepCopy();

            token               = objectNode.get("token").asText();
            prefix              = objectNode.get("prefix").asText();
            servers             = objectNode.get("servers").asInt();
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

    public String getPrefix() {
        return prefix;
    }

    public int getServers() {
        return servers;
    }

    public void setServers(int value) {
        servers = value;
    }
}