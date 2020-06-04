package Menu;

import Entity.GenreEntity;
import Service.GenreService;

import java.io.IOException;
import java.text.ParseException;

public class GenreMenu extends AbstractMenu {

    private final GenreService genreService = new GenreService();

    public GenreMenu() throws IOException, ParseException {

        String choiceGenre = actionSelection();

        boolean boolGenre = true;
        while (boolGenre) {
            switch (choiceGenre) {
                case "1":
                    System.out.println("\nВыберите действие: ");
                    System.out.println("1 - Найти все жанры");
                    System.out.println("2 - Найти по ID");
                    System.out.println("3 - Найти по названию");
                    System.out.println("0 - Вернуться назад\n");

                    System.out.print("Номер действия: ");
                    String findGenre = bf.readLine();
                    System.out.println();

                    boolean boolGenreFind = true;
                    while (boolGenreFind) {
                        switch (findGenre) {
                            case "1":
                                var listGenreFind = genreService.findAll();
                                if (listGenreFind == null || listGenreFind.isEmpty()) {
                                    System.out.println("\nПо запросу ничего не найдено\n");
                                } else {
                                    for (GenreEntity genre : listGenreFind) {
                                        System.out.print("\n" + genre + "\n");
                                    }
                                }
                                boolGenreFind = false;
                                break;

                            case "2":
                                System.out.print("Введите ID жанра: ");
                                String idGenre = bf.readLine();
                                int idGenreFind;
                                if (tryParseInt(idGenre)) {
                                    idGenreFind = Integer.parseInt(idGenre);
                                } else {
                                    System.out.println("\n'" + idGenre + "' нельзя привести к int!\n");
                                    break;
                                }
                                var genreIDFind = genreService.find(idGenreFind);

                                if (genreIDFind == null) {
                                    System.out.println("\nПо запросу ничего не найдено\n");
                                } else {
                                    System.out.print("\n" + genreIDFind + "\n");
                                }
                                boolGenreFind = false;
                                break;

                            case "3":
                                System.out.print("Введите название жанра: ");
                                String nameGenreFind = bf.readLine();
                                System.out.println();
                                var genreNameFind = genreService.findByTitle(nameGenreFind);

                                if (genreNameFind == null || genreNameFind.isEmpty()) {
                                    System.out.println("\nПо запросу ничего не найдено\n");
                                } else {
                                    for (GenreEntity genre : genreNameFind) {
                                        System.out.print("\n" + genre + "\n");
                                    }
                                }
                                boolGenreFind = false;
                                break;

                            case "0":
                                boolGenreFind = false;
                                genreMenu = new GenreMenu();
                                break;

                            default:
                                System.out.println("\nВведено неверное значение\n");
                                boolGenreFind = false;
                                break;
                        }
                    }
                    break;

                case "2":
                    System.out.println("\nВыберите действие: ");
                    System.out.println("1 - Добавить новый жанр");
                    System.out.println("0 - Вернуться назад\n");

                    System.out.print("Номер действия: ");
                    String saveGenre = bf.readLine();
                    System.out.println();

                    boolean boolGenreSave = true;
                    while (boolGenreSave) {
                        switch (saveGenre) {
                            case "1":
                                GenreEntity genreEntitySave = new GenreEntity();
                                System.out.println("Введите данные для добавления жанра: \n");

                                System.out.print("Название жанра: ");
                                genreEntitySave.setTitle(bf.readLine());
                                System.out.println();

                                boolean actionGenreSave = confirmationOfAction();
                                if (actionGenreSave) {
                                    genreService.save(genreEntitySave);
                                } else {
                                    System.out.println("\nОбъект НЕ добавлен!\n");
                                }
                                boolGenreSave = false;
                                break;

                            case "0":
                                boolGenreSave = false;
                                genreMenu = new GenreMenu();
                                break;

                            default:
                                System.out.println("\nВведено неверное значение\n");
                                boolGenreSave = false;
                                break;
                        }
                    }
                    break;

                case "3":
                    System.out.println("\nВыберите действие: ");
                    System.out.println("1 - Удалить жанр по ID");
                    System.out.println("0 - Вернуться назад\n");

                    System.out.print("Номер действия: ");
                    String deleteGenre = bf.readLine();
                    System.out.println();

                    try {
                        switch (deleteGenre) {
                            case "1":
                                System.out.print("Введите ID жанра: ");
                                String idDelete = bf.readLine();
                                int idGenreDelete;
                                if (tryParseInt(idDelete)) {
                                    idGenreDelete = Integer.parseInt(idDelete);
                                } else {
                                    System.out.println("\n'" + idDelete + "' нельзя привести к int!\n");
                                    break;
                                }

                                boolean actionGenreDelete = confirmationOfAction();
                                if (actionGenreDelete) {
                                    genreService.delete(idGenreDelete);
                                } else {
                                    System.out.println("\nОбъект НЕ удален!\n");
                                }
                                break;

                            case "0":
                                genreMenu = new GenreMenu();
                                break;

                            default:
                                System.out.println("\nВведено неверное значение\n");
                                break;

                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case "4":
                    System.out.println("\nВыберите действие: ");
                    System.out.println("1 - Обновить название жанра");
                    System.out.println("0 - Вернуться назад\n");

                    System.out.print("Номер действия: ");
                    String updateGenre = bf.readLine();
                    System.out.println();

                    try {
                        switch (updateGenre) {
                            case "1":
                                var genreIDUpdate = getUpdateEntity(genreService);
                                if (genreIDUpdate != null) {
                                    System.out.print("Название жанра: ");
                                    genreIDUpdate.setTitle(bf.readLine());

                                    boolean actionGenreUpdate = confirmationOfAction();
                                    if (actionGenreUpdate) {
                                        genreService.update(genreIDUpdate);
                                    } else {
                                        System.out.println("\nОбъект НЕ обновлен!\n");
                                    }
                                }
                                break;

                            case "0":
                                genreMenu = new GenreMenu();
                                break;

                            default:
                                System.out.println("\nВведено неверное значение\n");
                                break;
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case "0":
                    boolGenre = false;
                    mainMenu = new MainMenu();
                    break;

                default:
                    System.out.println("\nВведено неверное значение\n");
                    break;
            }
        }
    }
}
