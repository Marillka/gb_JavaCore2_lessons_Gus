package lesson7.client;

import lesson7.constants.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class EchoClient extends JFrame {


    private JTextField textField;
    private JTextArea textArea;

    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private String login;

    public EchoClient() {
        try {
            openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        prepareUI();
    }

    private void openConnection() throws IOException {
        socket = new Socket(Constants.SERVER_ADDRESS, Constants.SERVER_PORT);
        dataInputStream = new DataInputStream(socket.getInputStream());
        dataOutputStream = new DataOutputStream(socket.getOutputStream());
        new Thread(() -> {
            try {
                while (true) {
                    String messageFromServer = dataInputStream.readUTF();
                    if (messageFromServer.equals("/end")) {
                        break;
                    } else if (messageFromServer.startsWith(Constants.AUTH_COMMAND)) {
                        String[] tokens = messageFromServer.split("\\s+");
                        this.login = tokens[1];
                        textArea.append("Успешно авторизован как " + login);
                        textArea.append("\n");
                    }
                        if (messageFromServer.startsWith(Constants.CLIENTS_LIST_COMMAND)) {
                        // список клиентов
                    } else {
                        textArea.append(messageFromServer);
                        textArea.append("\n");
                    }
                }
                textArea.append("Соединение разорвано");
                textField.setEnabled(false);
                closeConnection();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }).start();
    }

    private void closeConnection() {
        try {
            dataOutputStream.close();
        } catch (Exception ex) {

        }
        try {
            dataInputStream.close();
        } catch (Exception ex) {

        }
        try {
            socket.close();
        } catch (Exception ex) {

        }
        // закрываем в обратном порядке
    }

    private void sendMessage() {
        if (textField.getText().trim().isEmpty()) {// если получили текст, обрезали пробелы спереди и сзади, и если это сообщение пустое, то мы ничего никуда не отправляем и выходим из метода.
            return;
        }
        try {
            dataOutputStream.writeUTF(textField.getText());
            textField.setText("");// стираем что написанно
            textField.grabFocus();// оставить курсор здесь
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void prepareUI() {
        setBounds(200, 200, 500, 500);//границы
        setTitle("EchoClient");//заголовок
        setDefaultCloseOperation(EXIT_ON_CLOSE);//выход на закрытие

        textArea = new JTextArea();// инициализация
        textArea.setEditable(false);// редактировать нельзя
        textArea.setLineWrap(true);// перенос строк
        add(new JScrollPane(textArea), BorderLayout.CENTER);// скрол по центру


        JPanel panel = new JPanel(new BorderLayout());// JPanel - панель внутри панели
        JButton button = new JButton("Send");
        panel.add(button, BorderLayout.EAST);
        textField = new JTextField();
        panel.add(textField, BorderLayout.CENTER);

        add(panel, BorderLayout.SOUTH);

        // верхняя панель с логином и паролем
        JPanel loginPanel = new JPanel(new GridLayout());
        // поле для логина
        JTextField loginField = new JTextField();
        loginPanel.add(loginField, BorderLayout.EAST);
        // поле для пароля
        JTextField passField = new JTextField();
        loginPanel.add(passField, BorderLayout.WEST);
        // кнопка авторизации
        JButton authButton = new JButton("Авторизоваться");
        loginPanel.add(authButton, BorderLayout.EAST);
        // добавляем панель с логином и паролем
        add(loginPanel, BorderLayout.NORTH);

        authButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    dataOutputStream.writeUTF(Constants.AUTH_COMMAND + " " + loginField.getText() + " " + passField.getText());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        button.addActionListener(e -> sendMessage());
        textField.addActionListener(e -> sendMessage());

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EchoClient());
    }
}
