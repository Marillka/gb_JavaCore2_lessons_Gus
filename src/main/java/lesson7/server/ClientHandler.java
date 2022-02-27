package lesson7.server;

import lesson7.constants.Constants;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Обработчик для конкретного клиента
 */
public class ClientHandler {

    private MyServer server;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private String name;

    public ClientHandler(MyServer server, Socket socket) {
        try {
            this.server = server;
            this.socket = socket;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
            new Thread(() -> {
                try {
                    authentification();
                    readMessage();

                } catch (IOException ex) {
                    ex.printStackTrace();
                } finally {
                    closeConnection();
                }
            }).start();


        } catch (IOException ex) {
            throw new RuntimeException("Проблема при создании обработчика");
        }
    }

    // auth    login    pass

    private void authentification() throws IOException {
        while (true) {
            String str = in.readUTF();
            if (str.startsWith(Constants.AUTH_COMMAND)) {
                String[] tokens = str.split("\\s+");// разбивает строку по пробелам. Получим массив длинной 3. В нулевом элементе команда, в первом логин, во втором пароль. \\s+ регулярное выражение, которое означает пробел один или больше пробелов.
                String nick = server.getAuthService().getNickByLoginAndPass(tokens[1], tokens[2]);

                if (nick != null) {
                    // Авторизовались
                    name = nick;
                    sendMessage(Constants.AUTH_OK_COMMAND + " " + nick);
                    server.broadcastMessage(nick + " Вошел в чат");
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
        while (true) {
            String messageFromClient = in.readUTF();
            System.out.println("Сообщение от " + name + ": " + messageFromClient);
            if (messageFromClient.equals(Constants.END_COMMAND)) {
                break;
            }
            server.broadcastMessage(messageFromClient);
        }
    }

    private void closeConnection() {
        server.unsubscribe(this);
        server.broadcastMessage(name + " вышел из чата");
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
