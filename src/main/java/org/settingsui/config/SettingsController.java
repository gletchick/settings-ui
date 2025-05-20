package org.settingsui.config; // Изменено на org.settingsui.config

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.json.JSONObject;
import org.settingsui.api.ConfigurableSettingsController; // Импортируем интерфейс из api
import org.settingsui.api.SettingsWindowFactory; // Импортируем интерфейс фабрики для вложенного SettingsChangeListener
import org.theme.AppTheme;
import org.theme.Theme;
import org.theme_manager.ThemeManager;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

// Реализуем наш новый интерфейс ConfigurableSettingsController
public class SettingsController implements ConfigurableSettingsController {

    @FXML
    public TextField fontSizeField;
    @FXML
    public TextField fontFamilyField;
    @FXML
    private Button changeThemeBt;

    private SettingsWindowFactory.SettingsChangeListener listener;
    private Stage stage;
    private AppConfig currentAppConfig;

    public void initialize(AppConfig initialConfig, SettingsWindowFactory.SettingsChangeListener listener, Stage stage) {
        this.currentAppConfig = initialConfig;
        this.listener = listener;
        this.stage = stage;

        fontSizeField.setText(String.valueOf(currentAppConfig .getFontSize()));
        fontFamilyField.setText(currentAppConfig .getFontFamily());

        updateThemeButtonText();
    }

    private void updateThemeButtonText() {
        if (changeThemeBt != null) {
            if (ThemeManager.getCurrentTheme() == AppTheme.DARK) {
                changeThemeBt.setText("Светлая тема");
            } else {
                changeThemeBt.setText("Темная тема");
            }
        }
    }

    @FXML
    public void onChangeThemeBtClick(ActionEvent actionEvent) {
        Theme newTheme;
        if (ThemeManager.getCurrentTheme() == AppTheme.DARK) {
            newTheme = AppTheme.LIGHT;
        } else {
            newTheme = AppTheme.DARK;
        }

        if (stage != null && stage.getScene() != null) {
            ThemeManager.applyTheme(stage.getScene(), newTheme);
        }

        updateThemeButtonText();

        if (listener != null) {
            listener.onThemeChanged(newTheme);
        }
    }

    @FXML
    public void onSaveBt(ActionEvent actionEvent) {
        try {
            String family = fontFamilyField.getText().trim();
            int size = Integer.parseInt(fontSizeField.getText().trim());

            currentAppConfig.setFontFamily(family);
            currentAppConfig.setFontSize(size);

            JSONObject root = new JSONObject()
                    .put("window", new JSONObject()
                            .put("width", currentAppConfig.getWindowWidth())
                            .put("height", currentAppConfig.getWindowHeight()))
                    .put("font", new JSONObject()
                            .put("family", currentAppConfig.getFontFamily())
                            .put("size", currentAppConfig.getFontSize()))
                    .put("theme", new JSONObject()
                            .put("mode", ThemeManager.getCurrentTheme().getName().toLowerCase()));

            Path configPath = Path.of("config.json");
            Files.writeString(configPath, root.toString(2), StandardCharsets.UTF_8);

            if (listener != null) {
                listener.onSettingsSaved(currentAppConfig);
            }

            stage.close();
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: Отобразить ошибку пользователю
        }
    }
}