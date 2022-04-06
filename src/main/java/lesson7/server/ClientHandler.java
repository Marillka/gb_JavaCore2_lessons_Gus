package lesson7.server;

import lesson14_ОбзорСредствРазработки.CurrentClass;
import lesson7.constants.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Обработчик для конкретного клиента
 */
public class ClientHandler {

    private MyServer server;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private String name;
    private static final Logger logger = LogManager.getLogger(CurrentClass.class);


    public ClientHandler(ExecutorService executorService,MyServer server, Socket socket) {
        try {
            this.server = server;
            this.socket = socket;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
            executorService.execute(() -> {
                try {
                    authentication();
                    readMessage();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } finally {
                    closeConnection();
                }
            });

//            new Thread(() -> {
//                try {
//                    authentication();
//                    // история переписки
//                    readMessage();
//                } catch (IOException ex) {
//                    ex.printStackTrace();
//                } finally {
//                    closeConnection();
//                }
//            }).start();

        } catch (IOException ex) {
            throw new RuntimeException("Проблема при создании обработчика");
        }
    }

    // auth    login    pass
    private void authentication() throws IOException {
        while (true) {
            String str = in.readUTF();
            if (str.startsWith(Constants.AUTH_COMMAND)) {
                String[] tokens = str.split("\\s+");// разбивает строку по пробелам. Получим массив длинной 3. В нулевом элементе команда, в первом логин, во втором пароль. \\s+ регулярное выражение, которое означает пробел один или больше пробелов.
                Optional<String> nick = server.getAuthService().getNickByLoginAndPass(tokens[1], tokens[2]);

                if (nick.isPresent()) {// isPresent() - есть значение или нет
                    // Авторизовались
                    name = nick.get();// так как nick - Optional, должны достать из него значение
                    sendMessage(Constants.AUTH_OK_COMMAND + " " + nick);
                    logger.trace("Авторизовался клиент с ником " + name);
                    server.broadcastMessage(nick + " Вошел в чат");
                    server.broadcastMessage(server.getActiveClients());
                    server.subscribe(this);
                    return;
                } else {
                    sendMessage("Неверный логин/пароль");
                }
            }
        }
    }


    public void sendMessage(String message) {
        try {
            out.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void readMessage() throws IOException {

        // создание доступа к файлу
        File file = new File("generalHistory.txt");
        // если файла нет - создать
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Не удалось создать файл");
            }
        }

        // выводим историю сообщений на экран
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String str;
            while ((str = reader.readLine()) != null ) {
                server.broadcastMessage(str);
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        // читаем сообщение
        while (true) {
            String messageFromClient = in.readUTF();
            // получение активных клиентов
            if (messageFromClient.startsWith(Constants.CLIENTS_LIST_COMMAND)) {
                sendMessage((server.getActiveClients()));
            } else { // иначе
                System.out.println("Сообщение от " + name + ": " + messageFromClient);
                logger.trace("Клиент " + name  + " отправил сообщение");
                // прервывание
                if (messageFromClient.equals(Constants.END_COMMAND)) {
                    break;
                }
                // смена ника
                if (messageFromClient.equals(Constants.CHANGE_NICK_COMMAND)) {
                    logger.trace("Клиент + " + name + " сменил ник");
                    // НАПИСАТЬ

                }
                // рассылка сообщения всем в чат
                server.broadcastMessage(name + ": " + messageFromClient);
                // пишем в файл
                try (Writer writer = new BufferedWriter(new FileWriter(file, true))) {
                    writer.append(name + ": " + messageFromClient + " \n");
                } catch (IOException exception) {
                    exception.printStackTrace();
                }

            }
        }
    }

    public String getName() {
        return name;
    }

    private void closeConnection() {
        server.unsubscribe(this);
        server.broadcastMessage(name + " вышел из чата");
        logger.trace("Клиент " + name + " отключился");
        try {
            in.close();
        } catch (IOException ex) {
            //ignore
        }
        try {
            out.close();
        } catch (IOException ex) {
            //ignore
        }
        try {
            socket.close();
        } catch (IOException ex) {
            //ignore
        }
    }
    // Если внести их в один try/catch, то если попадется первая строчка, код дальше не выполнится
}
