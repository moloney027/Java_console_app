package Menu;

import Entity.LogInAndSignUpEntity;
import Service.LogInAndSignUpService;
import org.apache.commons.codec.digest.DigestUtils;

public class LoginMenu extends AbstractMenu {

    public LogInAndSignUpService logInAndSignUpService = new LogInAndSignUpService();

    public LoginMenu() {

        boolean boolLogin = true;
        int choice = 0;
        while (boolLogin) {
            try {
                System.out.println("\nДля входа нажмите 1, для регистрации нажмите 2.");
                System.out.print("\nВыбор: ");
                String input = bf.readLine();
                if (tryParseInt(input)) {
                    choice = Integer.parseInt(input);
                }
                switch (choice) {
                    case 1:
                        authorization();
                        break;
                    case 2:
                        registration();
                        break;
                    default:
                        break;
                }
            } catch (Exception e) {
                System.out.println("\n" + e.getMessage());
            }
        }
    }

    private void registration() throws Exception {
        System.out.print("\nВведите новый логин: ");
        String log = bf.readLine();
        System.out.print("Введите новый пароль: ");
        String pass = bf.readLine();
        System.out.println();

        boolean conf = confirmationOfAction();

        if (conf) {
            var listLogin = logInAndSignUpService.findAll();
            if (listLogin.stream().anyMatch(lg -> lg.getLogin_().equals(log))) {
                System.out.println("\nДанный пользователь уже существует!\n");
            } else {
                LogInAndSignUpEntity signUpEntity = new LogInAndSignUpEntity();
                signUpEntity.setLogin_(log);
                signUpEntity.setPassword_(pass);
                logInAndSignUpService.save(signUpEntity);
                System.out.println("\nРегистрация прошла успешно!\n");
                mainMenu = new MainMenu();
            }
        }
    }

    private void authorization() throws Exception {
        System.out.print("\nВведите логин: ");
        String log = bf.readLine();
        System.out.print("Введите пароль: ");
        String pass = DigestUtils.md5Hex(bf.readLine());
        System.out.println();

        var listLogin = logInAndSignUpService.findAll();
        if (listLogin.stream().anyMatch(lg -> lg.getLogin_().equals(log) && (lg.getPassword_().equals(pass)))) {
            System.out.println("\nАвторизация успешна!\n");
            mainMenu = new MainMenu();
        } else {
            System.out.println("\nПароль или логин введен неверно!\n");
        }
    }
}
