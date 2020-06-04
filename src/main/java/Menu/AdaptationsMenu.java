package Menu;

import Entity.AdaptationsEntity;
import Service.AdaptationsService;
import Service.BookService;

import java.io.IOException;
import java.text.ParseException;

public class AdaptationsMenu extends AbstractMenu {

    private final AdaptationsService adaptationsService = new AdaptationsService();

    public AdaptationsMenu() throws ParseException, IOException {

        String choiceAdapt = actionSelection();

        boolean boolAdapt = true;
        while (boolAdapt) {
            switch (choiceAdapt) {
                case "1":
                    System.out.println("\nВыберите действие: ");
                    System.out.println("1 - Найти все адаптации");
                    System.out.println("2 - Найти по ID");
                    System.out.println("3 - Найти по типу адаптации");
                    System.out.println("4 - Найти по году");
                    System.out.println("5 - Найти по стране");
                    System.out.println("0 - Вернуться назад\n");

                    System.out.print("Номер действия: ");
                    String findAdapt = bf.readLine();
                    System.out.println();

                    boolean boolAdaptFind = true;
                    while (boolAdaptFind) {
                        switch (findAdapt) {
                            case "1":
                                var listAdaptFind = adaptationsService.findAll();
                                if (listAdaptFind == null || listAdaptFind.isEmpty()) {
                                    System.out.println("\nПо запросу ничего не найдено\n");
                                } else {
                                    for (AdaptationsEntity adapt : listAdaptFind) {
                                        System.out.print("\n" + adapt + "\n");
                                    }
                                }
                                boolAdaptFind = false;
                                break;

                            case "2":
                                System.out.print("Введите ID адаптации: ");
                                String idAdaptFind = bf.readLine();
                                int idAdapt;
                                if (tryParseInt(idAdaptFind)) {
                                    idAdapt = Integer.parseInt(idAdaptFind);
                                } else {
                                    System.out.println("\n'" + idAdaptFind + "' нельзя привести к int!\n");
                                    break;
                                }
                                var adaptIDFind = adaptationsService.find(idAdapt);

                                if (adaptIDFind == null) {
                                    System.out.println("\nПо запросу ничего не найдено\n");
                                } else {
                                    System.out.print("\n" + adaptIDFind + "\n");
                                }
                                boolAdaptFind = false;
                                break;

                            case "3":
                                System.out.print("Введите тип адаптации: ");
                                String typeAdaptFind = bf.readLine();
                                var adaptTypeFind = adaptationsService.findByType(typeAdaptFind);

                                if (adaptTypeFind == null || adaptTypeFind.isEmpty()) {
                                    System.out.println("\nПо запросу ничего не найдено\n");
                                } else {
                                    for (AdaptationsEntity adapt : adaptTypeFind) {
                                        System.out.print("\n" + adapt + "\n");
                                    }
                                }
                                boolAdaptFind = false;
                                break;

                            case "4":
                                System.out.print("Введите год адаптации: ");
                                String inputAdapt = bf.readLine();
                                int yearAdaptFind;
                                if (tryParseInt(inputAdapt)) {
                                    yearAdaptFind = Integer.parseInt(inputAdapt);
                                } else {
                                    System.out.println("\n'" + inputAdapt + "' нельзя привести к int!\n");
                                    break;
                                }
                                var adaptYearFind = adaptationsService.findByYear(yearAdaptFind);

                                if (adaptYearFind == null || adaptYearFind.isEmpty()) {
                                    System.out.println("\nПо запросу ничего не найдено\n");
                                } else {
                                    for (AdaptationsEntity adapt : adaptYearFind) {
                                        System.out.print("\n" + adapt + "\n");
                                    }
                                }
                                boolAdaptFind = false;
                                break;

                            case "5":
                                System.out.print("Введите страну адаптации: ");
                                String countryAdaptFind = bf.readLine();
                                System.out.println();
                                var adaptCountryFind = adaptationsService.findByCountry(countryAdaptFind);

                                if (adaptCountryFind == null || adaptCountryFind.isEmpty()) {
                                    System.out.println("\nПо запросу ничего не найдено\n");
                                } else {
                                    for (AdaptationsEntity adapt : adaptCountryFind) {
                                        System.out.print("\n" + adapt + "\n");
                                    }
                                }
                                boolAdaptFind = false;
                                break;

                            case "0":
                                boolAdaptFind = false;
                                adaptationsMenu = new AdaptationsMenu();
                                break;

                            default:
                                System.out.println("\nВведено неверное значение\n");
                                boolAdaptFind = false;
                                break;
                        }
                    }
                    break;

                case "2":
                    System.out.println("\nВыберите действие: ");
                    System.out.println("1 - Добавить новую адаптацию");
                    System.out.println("0 - Вернуться назад\n");

                    System.out.print("Номер действия: ");
                    String saveAdapt = bf.readLine();
                    System.out.println();

                    boolean boolAdaptSave = true;
                    while (boolAdaptSave) {
                        BookService bookService = new BookService();
                        switch (saveAdapt) {
                            case "1":
                                AdaptationsEntity adaptEntitySave = new AdaptationsEntity();
                                System.out.println("Введите данные для добавления адаптации: \n");

                                System.out.print("Тип адаптации: ");
                                adaptEntitySave.setTypeAdaptation(bf.readLine());

                                System.out.print("Год адаптации: ");
                                String inputYear = bf.readLine();
                                int year;
                                if (tryParseInt(inputYear)) {
                                    year = Integer.parseInt(inputYear);
                                } else {
                                    System.out.println("\n'" + inputYear + "' нельзя привести к int!\n");
                                    boolAdaptSave = false;
                                    break;
                                }
                                adaptEntitySave.setYear(year);

                                System.out.print("Страна адаптации: ");
                                adaptEntitySave.setCountry(bf.readLine());

                                System.out.print("ID связанной книги: ");
                                String inputBook = bf.readLine();
                                System.out.println();
                                int idBook;
                                if (tryParseInt(inputBook)) {
                                    idBook = Integer.parseInt(inputBook);
                                } else {
                                    System.out.println("\n'" + inputBook + "' нельзя привести к int!\n");
                                    boolAdaptSave = false;
                                    break;
                                }
                                adaptEntitySave.setBookForAdaptation(bookService.find(idBook));

                                boolean actionAdaptSave = confirmationOfAction();
                                if (actionAdaptSave) {
                                    adaptationsService.save(adaptEntitySave);
                                } else {
                                    System.out.println("\nОбъект НЕ добавлен!\n");
                                }
                                boolAdaptSave = false;
                                break;

                            case "0":
                                boolAdaptSave = false;
                                adaptationsMenu = new AdaptationsMenu();
                                break;

                            default:
                                System.out.println("\nВведено неверное значение\n");
                                boolAdaptSave = false;
                                break;
                        }
                    }
                    break;

                case "3":
                    System.out.println("\nВыберите действие: ");
                    System.out.println("1 - Удалить адаптацию по ID");
                    System.out.println("0 - Вернуться назад\n");

                    System.out.print("Номер действия: ");
                    String deleteAdapt = bf.readLine();
                    System.out.println();

                    try {
                        switch (deleteAdapt) {
                            case "1":
                                System.out.print("Введите ID адаптации: ");
                                String idAdaptFind = bf.readLine();
                                int idAdaptDelete;
                                if (tryParseInt(idAdaptFind)) {
                                    idAdaptDelete = Integer.parseInt(idAdaptFind);
                                } else {
                                    System.out.println("\n'" + idAdaptFind + "' нельзя привести к int!\n");
                                    break;
                                }

                                boolean actionAdaptDelete = confirmationOfAction();
                                if (actionAdaptDelete) {
                                    adaptationsService.delete(idAdaptDelete);
                                } else {
                                    System.out.println("\nОбъект НЕ удален!\n");
                                }
                                break;

                            case "0":
                                adaptationsMenu = new AdaptationsMenu();
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
                    System.out.println("2 - Обновить тип адаптации");
                    System.out.println("3 - Обновить год адаптации");
                    System.out.println("4 - Обновить страну адаптации");
                    System.out.println("5 - Обновить связанную книгу");
                    System.out.println("0 - Вернуться назад\n");

                    System.out.print("Номер действия: ");
                    String updateAdapt = bf.readLine();
                    System.out.println();

                    try {
                        switch (updateAdapt) {
                            case "1":
                                var adaptIDUpdate = getUpdateEntity(adaptationsService);
                                BookService bookService = new BookService();
                                if (adaptIDUpdate != null) {
                                    System.out.print("Тип адаптации: ");
                                    adaptIDUpdate.setTypeAdaptation(bf.readLine());

                                    System.out.print("Год адаптации: ");
                                    String inputYear = bf.readLine();
                                    int year;
                                    if (tryParseInt(inputYear)) {
                                        year = Integer.parseInt(inputYear);
                                    } else {
                                        System.out.println("\n'" + inputYear + "' нельзя привести к int!\n");
                                        break;
                                    }
                                    adaptIDUpdate.setYear(year);

                                    System.out.print("Страна адаптации: ");
                                    adaptIDUpdate.setCountry(bf.readLine());

                                    System.out.print("ID связанной книги: ");
                                    String inputBook = bf.readLine();
                                    System.out.println();
                                    int idBook;
                                    if (tryParseInt(inputBook)) {
                                        idBook = Integer.parseInt(inputBook);
                                    } else {
                                        System.out.println("\n'" + inputBook + "' нельзя привести к int!\n");
                                        break;
                                    }
                                    adaptIDUpdate.setBookForAdaptation(bookService.find(idBook));

                                    adaptationsService.update(adaptIDUpdate);

                                    boolean actionAdaptUpdateAll = confirmationOfAction();
                                    if (actionAdaptUpdateAll) {
                                        adaptationsService.update(adaptIDUpdate);
                                    } else {
                                        System.out.println("\nОбъект НЕ обновлен!\n");
                                    }
                                }
                                break;

                            case "2":
                                adaptIDUpdate = getUpdateEntity(adaptationsService);
                                if (adaptIDUpdate != null) {
                                    System.out.print("Тип адаптации: ");
                                    adaptIDUpdate.setTypeAdaptation(bf.readLine());

                                    boolean actionAdaptUpdateType = confirmationOfAction();
                                    if (actionAdaptUpdateType) {
                                        adaptationsService.update(adaptIDUpdate);
                                    } else {
                                        System.out.println("\nОбъект НЕ обновлен!\n");
                                    }
                                }
                                break;

                            case "3":
                                adaptIDUpdate = getUpdateEntity(adaptationsService);
                                if (adaptIDUpdate != null) {
                                    System.out.print("Год адаптации: ");
                                    String inputYear = bf.readLine();
                                    int year;
                                    if (tryParseInt(inputYear)) {
                                        year = Integer.parseInt(inputYear);
                                    } else {
                                        System.out.println("\n'" + inputYear + "' нельзя привести к int!\n");
                                        break;
                                    }
                                    adaptIDUpdate.setYear(year);

                                    boolean actionAdaptUpdateYear = confirmationOfAction();
                                    if (actionAdaptUpdateYear) {
                                        adaptationsService.update(adaptIDUpdate);
                                    } else {
                                        System.out.println("\nОбъект НЕ обновлен!\n");
                                    }
                                }
                                break;

                            case "4":
                                adaptIDUpdate = getUpdateEntity(adaptationsService);
                                if (adaptIDUpdate != null) {
                                    System.out.print("Страна адаптации: ");
                                    adaptIDUpdate.setCountry(bf.readLine());

                                    boolean actionAdaptUpdateCountry = confirmationOfAction();
                                    if (actionAdaptUpdateCountry) {
                                        adaptationsService.update(adaptIDUpdate);
                                    } else {
                                        System.out.println("\nОбъект НЕ обновлен!\n");
                                    }
                                }
                                break;

                            case "5":
                                bookService = new BookService();
                                adaptIDUpdate = getUpdateEntity(adaptationsService);
                                if (adaptIDUpdate != null) {
                                    System.out.print("ID связанной книги: ");
                                    String inputBook = bf.readLine();
                                    System.out.println();
                                    int idBook;
                                    if (tryParseInt(inputBook)) {
                                        idBook = Integer.parseInt(inputBook);
                                    } else {
                                        System.out.println("\n'" + inputBook + "' нельзя привести к int!\n");
                                        break;
                                    }
                                    adaptIDUpdate.setBookForAdaptation(bookService.find(idBook));

                                    boolean actionAdaptUpdateBook = confirmationOfAction();
                                    if (actionAdaptUpdateBook) {
                                        adaptationsService.update(adaptIDUpdate);
                                    } else {
                                        System.out.println("\nОбъект НЕ обновлен!\n");
                                    }
                                }
                                break;

                            case "0":
                                adaptationsMenu = new AdaptationsMenu();
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
                    boolAdapt = false;
                    mainMenu = new MainMenu();
                    break;

                default:
                    System.out.println("\nВведено неверное значение\n");
                    break;
            }
        }
    }
}
