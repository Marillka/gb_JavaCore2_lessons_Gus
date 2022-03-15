package lesson7.server;

import lesson7.constants.Constants;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Логика сервера.
 */
public class MyServer {

    /**
     * Сервис аутентификации.
     */
    private AuthService authService;

    /**
     * Активные клиенты.
     */
    private List<ClientHandler> clients;


    public AuthService getAuthService() {
        return authService;
    }

    public MyServer() {
        try (ServerSocket server = new ServerSocket(Constants.SERVER_PORT)) {
            authService = new BaseAuthService();
            authService.start();


            clients = new ArrayList<>();

            while (true) {
                System.out.println("Сервер ожидает подключения");
                Socket socket = server.accept();
                System.out.println("Клиент подключился");
                new ClientHandler(this, socket);
            }
        } catch (IOException | SQLException ex) {
            System.out.println("Ошибка в работе сервера.");
            ex.printStackTrace();
        } finally {
            if (authService != null) {
                authService.stop();
            }
        }
    }

    public synchronized void broadcastMessage(String message) {
        clients.forEach(client -> client.sendMessage(message));

//        for (ClientHandler client: clients) {
//            client.sendMessage(message);
//        }
    }

    public synchronized void subscribe(ClientHandler client) {
        clients.add(client);
    }

    public synchronized void unsubscribe(ClientHandler client) {
        clients.remove(client);
    }

//    public synchronized String getActiveClients() {
//        StringBuilder sb = new StringBuilder(Constants.CLIENTS_LIST_COMMAND).append(" ");
//        for (ClientHandler clientHandler: clients) {
//            sb.append(clientHandler.getName()).append(" ");
//        }
//        return sb.toString();
//    }

    public synchronized String getActiveClients() {
        //  Берем массив ClientHandler, превращаем его в массив имен. Массив имен через пробел собираем в строчку и джоиним ее в streamBuilder.
        StringBuilder sb = new StringBuilder(Constants.CLIENTS_LIST_COMMAND).append(" ");
        sb.append(clients.stream()
                .map(c -> c.getName())
                .collect(Collectors.joining(" ")));

//        for (ClientHandler clientHandler : clients) {
//            sb.append(clientHandler.getName()).append(" ");
//        }
        return sb.toString();
    }
}
