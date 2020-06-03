package Menu;

public class StartMenu extends AbstractMenu{

    public StartMenu() {
        System.out.println("Добро пожаловать в библиотечную систему!");
        System.out.println("Для дальнейшей работы с системой необходима авторизация.\n");
        loginMenu = new LoginMenu();
    }
}
