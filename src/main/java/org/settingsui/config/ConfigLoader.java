package org.settingsui.config; // Изменено на org.settingsui.config

import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ConfigLoader {
    private static final String CONFIG_FILE_NAME = "config.json";

    public AppConfig loadConfig() {
        Path configPath = Paths.get(CONFIG_FILE_NAME);
        if (Files.exists(configPath)) {
            try {
                String content = Files.readString(configPath, StandardCharsets.UTF_8);
                JSONObject root = new JSONObject(content);
                AppConfig config = new AppConfig();

                if (root.has("window")) {
                    JSONObject window = root.getJSONObject("window");
                    if (window.has("width")) config.setWindowWidth(window.getInt("width"));
                    if (window.has("height")) config.setWindowHeight(window.getInt("height"));
                }
                if (root.has("font")) {
                    JSONObject font = root.getJSONObject("font");
                    if (font.has("family")) config.setFontFamily(font.getString("family"));
                    if (font.has("size")) config.setFontSize(font.getInt("size"));
                }
                if (root.has("theme")) {
                    JSONObject theme = root.getJSONObject("theme");
                    if (theme.has("mode")) config.setThemeMode(theme.getString("mode"));
                }
                return config;
            } catch (IOException e) {
                System.err.println("Ошибка при чтении config.json: " + e.getMessage());
                return new AppConfig(); // Возвращаем дефолтную конфигурацию при ошибке
            }
        }
        return new AppConfig(); // Возвращаем дефолтную, если файл не существует
    }

    public void saveConfig(AppConfig config) {
        try {
            JSONObject root = new JSONObject()
                    .put("window", new JSONObject()
                            .put("width", config.getWindowWidth())
                            .put("height", config.getWindowHeight()))
                    .put("font", new JSONObject()
                            .put("family", config.getFontFamily())
                            .put("size", config.getFontSize()))
                    .put("theme", new JSONObject()
                            .put("mode", config.getThemeMode()));

            Path configPath = Paths.get(CONFIG_FILE_NAME);
            Files.writeString(configPath, root.toString(2), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.err.println("Ошибка при записи config.json: " + e.getMessage());
        }
    }
}