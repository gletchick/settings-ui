package org.settingsui.config; // Изменено на org.settingsui.config

// Простой POJO для хранения настроек
public class AppConfig {
    private int windowWidth;
    private int windowHeight;
    private String fontFamily;
    private int fontSize;
    private String themeMode; // Имя темы, "dark" или "default"

    public AppConfig() {
        // Значения по умолчанию
        this.windowWidth = 251;
        this.windowHeight = 383;
        this.fontFamily = "System";
        this.fontSize = 12;
        this.themeMode = "dark"; // Тема по умолчанию
    }

    // Геттеры и сеттеры
    public int getWindowWidth() {
        return windowWidth;
    }

    public void setWindowWidth(int windowWidth) {
        this.windowWidth = windowWidth;
    }

    public int getWindowHeight() {
        return windowHeight;
    }

    public void setWindowHeight(int windowHeight) {
        this.windowHeight = windowHeight;
    }

    public String getFontFamily() {
        return fontFamily;
    }

    public void setFontFamily(String fontFamily) {
        this.fontFamily = fontFamily;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public String getThemeMode() {
        return themeMode;
    }

    public void setThemeMode(String themeMode) {
        this.themeMode = themeMode;
    }
}