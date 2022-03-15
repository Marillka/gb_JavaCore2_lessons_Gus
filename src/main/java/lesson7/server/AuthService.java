package lesson7.server;


import java.sql.SQLException;
import java.util.Optional;

/**
 * Сервис аутентификации
 */
public interface AuthService {

    /**
     * Запустить сервис
     */
    void start() throws SQLException;

    /**
     * Отключить сервер
     */
    void stop();

    /**
     * Получить никнейм по логину и паролю
     * @param login
     * @param pass
     * @return никнейм если найден или null, если такого нет
     */
//    String getNickByLoginAndPass(String login, String pass);

    // Я могу вернуть значение, а могу не вернуть значение. Я не null верну, я верну всегда объект, но будет ли в нем лежать значение или нет - я не гарантирую.
    Optional<String> getNickByLoginAndPass(String login, String pass);
}
