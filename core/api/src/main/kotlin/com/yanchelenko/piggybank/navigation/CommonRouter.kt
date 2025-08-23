package com.yanchelenko.piggybank.navigation

/**
 * [CommonRouter] — базовый интерфейс навигации, предоставляющий общие действия,
 * доступные для большинства экранов.
 *
 * Используется в фичевых Router-интерфейсах как родительский, чтобы не дублировать
 * реализацию возврата назад или перехода к корневому экрану.
 *
 * Примеры использования:
 * - navigateBack() — для выхода с текущего экрана
 * - navigateToRoot() — для очистки back stack и возврата к стартовому экрану
 *
 * ---
 *
 * [CommonRouter] is a base navigation interface providing common actions
 * available for most screens.
 *
 * Used as a parent interface in feature-specific routers to avoid code duplication
 * for back navigation and returning to the root screen.
 *
 * Usage examples:
 * - navigateBack() — exits the current screen
 * - navigateToRoot() — clears the back stack and returns to the start screen
 */

interface CommonRouter { // описание разнится с реальностью, тут только назад можно навигироваться
    fun navigateBack()
}
