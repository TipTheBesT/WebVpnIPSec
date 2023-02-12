package ru.thebestsoft.webvpn.services;

import ru.thebestsoft.webvpn.services.emploee.User;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class UserService {
    final String path = "/etc/ipsec.secrets";
    private ArrayList<User> userList = new ArrayList<>();

    public UserService() {
        try {
            refresh();
        } catch (Exception e) {
            System.out.println("Ошибка чтения: " + e);
        }

    }

    private void refresh() throws IOException {
            userList = new ArrayList<>();
            FileReader fr= new FileReader(path);
            Scanner scan = new Scanner(fr);

            loop:
            while (scan.hasNextLine()) {
                String str = scan.nextLine();
                if (str.startsWith("#WebVPN{users}")) {
                    while (scan.hasNextLine()) {
                        str = scan.nextLine();
                        if (!str.startsWith("#WebVPN{/users}")) {
                            String[] values = str.split(" ");
                            if (values.length>=3) {
                                User user = new User();
                                user.setLogin(values[0].replace(" ",""));
                                user.setPassword(values[3].replace("\"","").replace(" ",""));
                                userList.add(user);
                            }
                        } else {
                            break loop;
                        }
                    }
                }
            }

            scan.close();
            fr.close();

        System.out.println("Файл успешно прочитан!");
    }

    private void save() throws IOException {

            FileWriter writer = new FileWriter(path, false);
            String text = """
                    #WebVPN{serverKey}
                    : RSA "server-key.pem"
                    #WebVPN{/serverKey}
                    #WebVPN{users}
                    """;
            for (User user : userList) {
                text += user.getLogin() + " : EAP \"" + user.getPassword() + "\"\n";
            }
            text += "#WebVPN{/users}";
            writer.write(text);
            writer.flush();
            writer.close();
            System.out.println("Файл успешно перезаписан!");
    }

    public void addUser(User user) {
        try {
            refresh();
            userList.add(user);
            save();
            System.out.println("Пользователь успешно добавлен!");
        } catch (Exception e) {
            System.out.println("Ошибка при удалении: " + e);
        }
    }

    public void delUser(String login) {
        try {
            refresh();
            for (int i = 0; i < userList.size(); i++) {
                if (userList.get(i).getLogin().equals(login)) {
                    userList.remove(i);
                    save();
                    break;
                }
            }
            System.out.println("Пользователь успешно удалён!");
        } catch (Exception e) {
            System.out.println("Ошибка при удаление: " + e);
        }
    }

    public User getUser(String login) {
        try {
            refresh();
            for (User user : userList) {
                if (user.getLogin().equals(login)) {
                    return user;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public ArrayList<User> getUserList() {
        return userList;
    }

}
