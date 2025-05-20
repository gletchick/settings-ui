package org.settingsui.api; // Изменено на org.settingsui.api

import org.settingsui.config.AppConfig; // Используем AppConfig из org.settingsui.config
import org.theme.Theme; // Используем Theme из проекта Theme (groupId org.matvey.kpo)

import java.io.IOException;

/**
 * Интерфейс для фабрики, создающей и отображающей окно настроек.
 * Позволяет создавать различные реализации окна настроек.
 */
public interface SettingsWindowFactory {

    /**
     * Интерфейс для слушателя изменений настроек.
     * Основное приложение должно реализовать его для получения уведомлений.
     */
    interface SettingsChangeListener {
        /**
         * Вызывается, когда настройки сохраняются.
         * @param config Обновленная конфигурация приложения.
         */
        void onSettingsSaved(AppConfig config);

        /**
         * Вызывается, когда тема изменяется через окно настроек.
         * @param newTheme Новая выбранная тема.
         */
        void onThemeChanged(Theme newTheme);
    }

    /**
     * Отображает окно настроек.
     *
     * @param initialConfig Начальная конфигурация, которая будет отображена в окне.
     * @param listener Слушатель для уведомлений об изменениях настроек.
     * @throws IOException Если возникает ошибка при загрузке UI окна.
     */
    void showSettingsWindow(AppConfig initialConfig, SettingsChangeListener listener) throws IOException;
}