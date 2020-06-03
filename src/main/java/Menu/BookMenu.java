package Menu;

import Entity.AdaptationsEntity;
import Entity.AuthorEntity;
import Entity.BookEntity;
import Entity.GenreEntity;
import Service.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;

public class BookMenu extends AbstractMenu {

    private final BookService bookService = new BookService();

    public BookMenu() throws IOException, ParseException {

        String choiceBook = actionSelection();

        boolean boolBook = true;
        while (boolBook) {
            switch (choiceBook) {
                case "1":
                    System.out.println("\nВыберите действие: ");
                    System.out.println("1 - Найти все книги");
                    System.out.println("2 - Найти по ID");
                    System.out.println("3 - Найти по названию");
                    System.out.println("4 - Найти по году написания");
                    System.out.println("5 - Найти по языку написания");
                    System.out.println("0 - Вернуться назад\n");

                    System.out.print("Номер действия: ");
                    String findBook = in.next();
                    System.out.println();

                    boolean boolBookFind = true;
                    while (boolBookFind) {
                        switch (findBook) {
                            case "1":
                                var listBookFind = bookService.findAll();
                                if (listBookFind == null || listBookFind.isEmpty()) {
                                    System.out.println("\nПо запросу ничего не найдено\n");
                                } else {
                                    for (BookEntity book : listBookFind) {
                                        System.out.print("\n" + book + "\n");
                                    }
                                }
                                boolBookFind = false;
                                break;

                            case "2":
                                System.out.print("Введите ID книги: ");
                                int idBookFind = in.nextInt();
                                System.out.println();
                                var bookIDFind = bookService.find(idBookFind);
                                if (bookIDFind == null) {
                                    System.out.println("\nПо запросу ничего не найдено\n");
                                } else {
                                    System.out.print("\n" + bookIDFind + "\n");
                                }
                                boolBookFind = false;
                                break;

                            case "3":
                                System.out.print("Введите название книги: ");
                                String nameBookFind = bf.readLine();
                                System.out.println();
                                var bookNameFind = bookService.findByTitle(nameBookFind);
                                if (bookNameFind == null || bookNameFind.isEmpty()) {
                                    System.out.println("\nПо запросу ничего не найдено\n");
                                } else {
                                    for (BookEntity book : bookNameFind) {
                                        System.out.print("\n" + book + "\n");
                                    }
                                }
                                boolBookFind = false;
                                break;

                            case "4":
                                System.out.print("Введите год написания: ");
                                String inputBook = in.next();
                                System.out.println();
                                int yearBookFind;
                                if (tryParseInt(inputBook)) {
                                    yearBookFind = Integer.parseInt(inputBook);
                                } else {
                                    System.out.println("\nВведено неверное значение!\n");
                                    break;
                                }
                                var bookYearFind = bookService.findByYear(yearBookFind);
                                if (bookYearFind == null || bookYearFind.isEmpty()) {
                                    System.out.println("\nПо запросу ничего не найдено\n");
                                } else {
                                    for (BookEntity book : bookYearFind) {
                                        System.out.print("\n" + book + "\n");
                                    }
                                }
                                boolBookFind = false;
                                break;

                            case "5":
                                System.out.print("Введите язык написания книги: ");
                                String langBookFind = bf.readLine();
                                System.out.println();
                                var bookLangFind = bookService.findByLanguage(langBookFind);
                                if (bookLangFind == null || bookLangFind.isEmpty()) {
                                    System.out.println("\nПо запросу ничего не найдено\n");
                                } else {
                                    for (BookEntity book : bookLangFind) {
                                        System.out.print("\n" + book + "\n");
                                    }
                                }
                                boolBookFind = false;
                                break;

                            case "0":
                                boolBookFind = false;
                                bookMenu = new BookMenu();
                                break;

                            default:
                                System.out.println("\nВведено неверное значение\n");
                                boolBookFind = false;
                                break;
                        }
                    }
                    break;

                case "2":
                    System.out.println("\nВыберите действие: ");
                    System.out.println("1 - Добавить новую книгу");
                    System.out.println("0 - Вернуться назад\n");

                    System.out.print("Номер действия: ");
                    String saveBook = in.next();
                    System.out.println();

                    boolean boolBookSave = true;
                    while (boolBookSave) {
                        switch (saveBook) {
                            case "1":
                                BookEntity bookEntitySave = new BookEntity();
                                PublishingHouseService publishingHouseService = new PublishingHouseService();
                                AdaptationsService adaptationsService = new AdaptationsService();
                                System.out.println("Введите данные для добавления книги: \n");

                                System.out.print("Название книги: ");
                                bookEntitySave.setTitle(bf.readLine());

                                System.out.print("Год написания: ");
                                String inputYear = in.next();
                                int year;
                                if (tryParseInt(inputYear)) {
                                    year = Integer.parseInt(inputYear);
                                } else {
                                    System.out.println("\nВведено неверное значение!\n");
                                    boolBookSave = false;
                                    break;
                                }
                                bookEntitySave.setYearOfWriting(year);

                                System.out.print("Язык написания книги: ");
                                bookEntitySave.setLanguageBook(bf.readLine());

                                System.out.print("ID связанного издательства: ");
                                String inputPH = in.next();
                                System.out.println();
                                int idPH;
                                if (tryParseInt(inputPH)) {
                                    idPH = Integer.parseInt(inputPH);
                                } else {
                                    System.out.println("\nВведено неверное значение!\n");
                                    boolBookSave = false;
                                    break;
                                }
                                bookEntitySave.setPublishingHouseForBook(publishingHouseService.find(idPH));
                                System.out.println();

                                boolean actionBookSave = confirmationOfAction();
                                if (actionBookSave) {
                                    bookService.save(bookEntitySave);
                                } else {
                                    System.out.println("\nОбъект НЕ добавлен!\n");
                                }
                                boolBookSave = false;
                                break;

                            case "0":
                                boolBookSave = false;
                                bookMenu = new BookMenu();
                                break;

                            default:
                                System.out.println("\nВведено неверное значение\n");
                                boolBookSave = false;
                                break;
                        }
                    }
                    break;

                case "3":
                    System.out.println("\nВыберите действие: ");
                    System.out.println("1 - Удалить книгу по ID");
                    System.out.println("0 - Вернуться назад\n");

                    System.out.print("Номер действия: ");
                    String deleteBook = in.next();
                    System.out.println();

                    try {
                        switch (deleteBook) {
                            case "1":
                                System.out.print("Введите ID книги: ");
                                int idBookDelete = in.nextInt();
                                System.out.println();
                                boolean actionBookDelete = confirmationOfAction();
                                if (actionBookDelete) {
                                    bookService.delete(idBookDelete);
                                } else {
                                    System.out.println("\nОбъект НЕ удален!\n");
                                }
                                break;

                            case "0":
                                bookMenu = new BookMenu();
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
                    System.out.println("1 - Обновить все поля");
                    System.out.println("2 - Обновить название книги");
                    System.out.println("3 - Обновить год написания книги");
                    System.out.println("4 - Обновить язык написания книги");
                    System.out.println("5 - Обновить связанное издательство");
                    System.out.println("6 - Установить новые жанры");
                    System.out.println("7 - Установить новых авторов");
                    System.out.println("0 - Вернуться назад\n");

                    System.out.print("Номер действия: ");
                    String updateBook = in.next();
                    System.out.println();

                    try {
                        switch (updateBook) {
                            case "1":
                                PublishingHouseService publishingHouseService = new PublishingHouseService();
                                var bookIDUpdate = getUpdateEntity(bookService);
                                if (bookIDUpdate != null) {
                                    System.out.print("Название книги: ");
                                    bookIDUpdate.setTitle(bf.readLine());

                                    System.out.print("Год написания: ");
                                    String inputYear = in.next();
                                    int year;
                                    if (tryParseInt(inputYear)) {
                                        year = Integer.parseInt(inputYear);
                                    } else {
                                        System.out.println("\nВведено неверное значение!\n");
                                        break;
                                    }
                                    bookIDUpdate.setYearOfWriting(year);

                                    System.out.print("Язык написания книги: ");
                                    bookIDUpdate.setLanguageBook(bf.readLine());

                                    System.out.print("ID связанного издательства: ");
                                    String inputPH = in.next();
                                    System.out.println();
                                    int idPH;
                                    if (tryParseInt(inputPH)) {
                                        idPH = Integer.parseInt(inputPH);
                                    } else {
                                        System.out.println("\nВведено неверное значение!\n");
                                        break;
                                    }
                                    bookIDUpdate.setPublishingHouseForBook(publishingHouseService.find(idPH));
                                    boolean actionBookUpdateAll = confirmationOfAction();
                                    if (actionBookUpdateAll) {
                                        bookService.update(bookIDUpdate);
                                    } else {
                                        System.out.println("\nОбъект НЕ обновлен!\n");
                                    }
                                }
                                break;

                            case "2":
                                bookIDUpdate = getUpdateEntity(bookService);
                                if (bookIDUpdate != null) {
                                    System.out.print("Название книги: ");
                                    bookIDUpdate.setTitle(bf.readLine());
                                    boolean actionAuthUpdateDate = confirmationOfAction();
                                    if (actionAuthUpdateDate) {
                                        bookService.update(bookIDUpdate);
                                    } else {
                                        System.out.println("\nОбъект НЕ обновлен!\n");
                                    }
                                }
                                break;

                            case "3":
                                bookIDUpdate = getUpdateEntity(bookService);
                                if (bookIDUpdate != null) {
                                    System.out.print("Год написания: ");
                                    String inputYear = in.next();
                                    int year;
                                    if (tryParseInt(inputYear)) {
                                        year = Integer.parseInt(inputYear);
                                    } else {
                                        System.out.println("\nВведено неверное значение!\n");
                                        break;
                                    }
                                    bookIDUpdate.setYearOfWriting(year);
                                    boolean actionAuthUpdatePlace = confirmationOfAction();
                                    if (actionAuthUpdatePlace) {
                                        bookService.update(bookIDUpdate);
                                    } else {
                                        System.out.println("\nОбъект НЕ обновлен!\n");
                                    }
                                }
                                break;

                            case "4":
                                bookIDUpdate = getUpdateEntity(bookService);
                                if (bookIDUpdate != null) {
                                    System.out.print("Язык написания книги: ");
                                    bookIDUpdate.setLanguageBook(bf.readLine());
                                    boolean actionAuthUpdateName = confirmationOfAction();
                                    if (actionAuthUpdateName) {
                                        bookService.update(bookIDUpdate);
                                    } else {
                                        System.out.println("\nОбъект НЕ обновлен!\n");
                                    }
                                }
                                break;

                            case "5":
                                bookIDUpdate = getUpdateEntity(bookService);
                                publishingHouseService = new PublishingHouseService();
                                System.out.print("ID связанного издательства: ");
                                String inputPH = in.next();
                                System.out.println();
                                int idPH;
                                if (tryParseInt(inputPH)) {
                                    idPH = Integer.parseInt(inputPH);
                                } else {
                                    System.out.println("\nВведено неверное значение!\n");
                                    break;
                                }
                                bookIDUpdate.setPublishingHouseForBook(publishingHouseService.find(idPH));
                                boolean actionBookUpdatePH = confirmationOfAction();
                                if (actionBookUpdatePH) {
                                    bookService.update(bookIDUpdate);
                                } else {
                                    System.out.println("\nОбъект НЕ обновлен!\n");
                                }
                                break;

                            case "6":
                                bookIDUpdate = getUpdateEntity(bookService);
                                GenreService genreService = new GenreService();
                                System.out.print("ID жанра(ов) для книги: ");
                                String[] inputGenre = bf.readLine().split(" ");
                                System.out.println();
                                Set<GenreEntity> genreEntities = new HashSet<>();

                                for (String idGenre : inputGenre) {
                                    if (tryParseInt(idGenre)) {
                                        genreEntities.add(genreService.find(Integer.parseInt(idGenre)));
                                    } else {
                                        System.out.println("Не получилось \'" + idGenre + "\' привести к int!");
                                    }
                                }
                                bookIDUpdate.setGenresForBook(genreEntities);
                                System.out.println();

                                boolean actionBookUpdate = confirmationOfAction();
                                if (actionBookUpdate) {
                                    bookService.update(bookIDUpdate);
                                } else {
                                    System.out.println("\nЖанр(ы) для книги не добавлен(ы)!\n");
                                    break;
                                }
                                break;

                            case "7":
                                bookIDUpdate = getUpdateEntity(bookService);
                                AuthorService authorService = new AuthorService();
                                System.out.print("ID автора(ов) для книги: ");
                                String[] inputAuthor = bf.readLine().split(" ");
                                System.out.println();
                                Set<AuthorEntity> authorEntities = new HashSet<>();

                                for (String idAuth : inputAuthor) {
                                    if (tryParseInt(idAuth)) {
                                        authorEntities.add(authorService.find(Integer.parseInt(idAuth)));
                                    } else {
                                        System.out.println("Не получилось \'" + idAuth + "\' привести к int!");
                                    }
                                }
                                bookIDUpdate.setAuthorsForBook(authorEntities);
                                System.out.println();

                                boolean actionBookUpdateAuth = confirmationOfAction();
                                if (actionBookUpdateAuth) {
                                    bookService.update(bookIDUpdate);
                                } else {
                                    System.out.println("\nАвтор(ы) для книги не добавлен(ы)!\n");
                                    break;
                                }
                                break;

                            case "0":
                                bookMenu = new BookMenu();
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
                    boolBook = false;
                    mainMenu = new MainMenu();
                    break;

                default:
                    System.out.println("\nВведено неверное значение\n");
                    break;
            }
        }
    }
}
