package org.settingsui.ui; // Изменено на org.settingsui.config

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.settingsui.api.SettingsWindowFactory; // Импортируем интерфейс из api
import org.settingsui.api.ConfigurableSettingsController; // Импортируем интерфейс из api
import org.settingsui.config.AppConfig;
import org.theme_manager.ThemeManager;

import java.io.IOException;

// Реализуем наш новый интерфейс SettingsWindowFactory
public class DefaultSettingsWindowFactory implements SettingsWindowFactory {

    @Override // Реализуем метод showSettingsWindow из интерфейса
    public void showSettingsWindow(AppConfig initialConfig, SettingsChangeListener listener) throws IOException {
        Stage settingsStage = new Stage();
        settingsStage.initModality(Modality.APPLICATION_MODAL);

        // Путь к FXML теперь должен быть относительно пакета config или resources root
        // Предполагаем, что settings.fxml находится в src/main/resources/org/settingsui/config/view/settings.fxml
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/settingsui/config/view/settings.fxml"));
        Parent root = fxmlLoader.load();

        Object controllerInstance = fxmlLoader.getController();
        if (!(controllerInstance instanceof ConfigurableSettingsController)) {
            throw new IllegalStateException("FXML controller must implement ConfigurableSettingsController interface.");
        }
        ConfigurableSettingsController controller = (ConfigurableSettingsController) controllerInstance;

        controller.initialize(initialConfig, listener, settingsStage);

        Scene scene = new Scene(root);
        ThemeManager.applyCurrentTheme(scene); // Применяем текущую тему из ThemeManager

        settingsStage.setTitle("Настройки");
        settingsStage.setScene(scene);
        settingsStage.setResizable(false);
        settingsStage.showAndWait();
    }
}