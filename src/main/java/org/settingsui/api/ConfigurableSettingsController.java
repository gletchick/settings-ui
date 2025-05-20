package org.settingsui.api; // Изменено на org.settingsui.api

import javafx.stage.Stage;
import org.settingsui.config.AppConfig; // Используем AppConfig из org.settingsui.config

/**
 * Интерфейс для контроллеров, управляющих UI настроек.
 * Обеспечивает унифицированный способ инициализации контроллера.
 */
public interface ConfigurableSettingsController {

    /**
     * Инициализирует контроллер настроек.
     *
     * @param initialConfig Начальная конфигурация, которую должен использовать контроллер.
     * @param listener      Слушатель для уведомлений об изменениях настроек.
     * @param stage         Сцена окна настроек, на которой отображается контроллер.
     */
    void initialize(AppConfig initialConfig, SettingsWindowFactory.SettingsChangeListener listener, Stage stage);
}