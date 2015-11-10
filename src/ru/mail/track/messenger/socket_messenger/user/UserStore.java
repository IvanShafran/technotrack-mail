package ru.mail.track.messenger.socket_messenger.user;

import com.sun.istack.internal.Nullable;

/**
 * Хранилище информации о пользователе
 */
public interface UserStore {

    /**
     * Добавить пользователя в хранилище
     * Вернуть его же
     */
    @Nullable
    User addUser(User user);

    /**
     *
     * Получить пользователя по логину/паролю
     */
    @Nullable
    User getUser(String login);

    /**
     *
     * Получить пользователя по id, например запрос информации/профиля
     */
    @Nullable
    User getUserById(Long id);
}
