package Menu;

import Entity.ReadersEntity;
import Service.ReadersService;

import java.io.IOException;
import java.text.ParseException;

public class ReadersMenu extends AbstractMenu {

    private final ReadersService readersService = new ReadersService();

    public ReadersMenu() throws IOException, ParseException {

        String choiceReader = actionSelection();

        boolean boolReader = true;
        while (boolReader) {
            switch (choiceReader) {
                case "1":
                    System.out.println("\nВыберите действие: ");
                    System.out.println("1 - Найти всех читателей");
                    System.out.println("2 - Найти по ID");
                    System.out.println("3 - Найти по ФИО");
                    System.out.println("4 - Найти по возрасту");
                    System.out.println("0 - Вернуться назад\n");

                    System.out.print("Номер действия: ");
                    String findReader = in.next();
                    System.out.println();

                    boolean boolReaderFind = true;
                    while (boolReaderFind) {
                        switch (findReader) {
                            case "1":
                                var listReaderFind = readersService.findAll();
                                if (listReaderFind == null || listReaderFind.isEmpty()) {
                                    System.out.println("\nПо запросу ничего не найдено\n");
                                } else {
                                    for (ReadersEntity reader : listReaderFind) {
                                        System.out.print("\n" + reader + "\n");
                                    }
                                }
                                boolReaderFind = false;
                                break;

                            case "2":
                                System.out.print("Введите ID читателя: ");
                                int idReaderFind = in.nextInt();
                                System.out.println();
                                var readerIDFind = readersService.find(idReaderFind);
                                if (readerIDFind == null) {
                                    System.out.println("\nПо запросу ничего не найдено\n");
                                } else {
                                    System.out.print("\n" + readerIDFind + "\n");
                                }
                                boolReaderFind = false;
                                break;

                            case "3":
                                System.out.print("Введите ФИО читателя: ");
                                String nameReaderFind = bf.readLine();
                                System.out.println();
                                var readerNameFind = readersService.findByName(nameReaderFind);
                                if (readerNameFind == null || readerNameFind.isEmpty()) {
                                    System.out.println("\nПо запросу ничего не найдено\n");
                                } else {
                                    for (ReadersEntity reader : readerNameFind) {
                                        System.out.print("\n" + reader + "\n");
                                    }
                                }
                                boolReaderFind = false;
                                break;

                            case "4":
                                System.out.print("Введите возраст: ");
                                int ageReaderFind = in.nextInt();
                                System.out.println();
                                var readerAgeFind = readersService.findByAge(ageReaderFind);
                                if (readerAgeFind == null || readerAgeFind.isEmpty()) {
                                    System.out.println("\nПо запросу ничего не найдено\n");
                                } else {
                                    for (ReadersEntity reader : readerAgeFind) {
                                        System.out.print("\n" + reader + "\n");
                                    }
                                }
                                boolReaderFind = false;
                                break;

                            case "0":
                                boolReaderFind = false;
                                readersMenu = new ReadersMenu();
                                break;

                            default:
                                System.out.println("\nВведено неверное значение\n");
                                boolReaderFind = false;
                                break;
                        }
                    }
                    break;

                case "2":
                    System.out.println("\nВыберите действие: ");
                    System.out.println("1 - Добавить нового читателя");
                    System.out.println("0 - Вернуться назад\n");

                    System.out.print("Номер действия: ");
                    String saveReader = in.next();
                    System.out.println();

                    boolean boolReaderSave = true;
                    while (boolReaderSave) {
                        switch (saveReader) {
                            case "1":
                                ReadersEntity readersEntitySave = new ReadersEntity();
                                System.out.println("Введите данные для добавления читателя: \n");

                                System.out.print("ФИО читателя: ");
                                readersEntitySave.setFullName(bf.readLine());

                                System.out.print("Возраст читателя: ");
                                String inputAge = in.next();
                                int Age;
                                if (tryParseInt(inputAge)) {
                                    Age = Integer.parseInt(inputAge);
                                } else {
                                    System.out.println("\nВведено неверное значение!\n");
                                    break;
                                }
                                readersEntitySave.setAge(Age);

                                System.out.print("Адрес читателя: ");
                                readersEntitySave.setAddressReader(bf.readLine());
                                System.out.println();

                                boolean actionAuthSave = confirmationOfAction();
                                if (actionAuthSave) {
                                    readersService.save(readersEntitySave);
                                } else {
                                    System.out.println("\nОбъект НЕ добавлен!\n");
                                }
                                boolReaderSave = false;
                                break;

                            case "0":
                                boolReaderSave = false;
                                readersMenu = new ReadersMenu();
                                break;

                            default:
                                System.out.println("\nВведено неверное значение\n");
                                boolReaderSave = false;
                                break;
                        }
                    }
                    break;

                case "3":
                    System.out.println("\nВыберите действие: ");
                    System.out.println("1 - Удалить читателя по ID");
                    System.out.println("0 - Вернуться назад\n");

                    System.out.print("Номер действия: ");
                    String deleteReader = in.next();
                    System.out.println();

                    try {
                        switch (deleteReader) {
                            case "1":
                                System.out.print("Введите ID читателя: ");
                                int idReaderDelete = in.nextInt();
                                System.out.println();
                                boolean actionReaderDelete = confirmationOfAction();
                                if (actionReaderDelete) {
                                    readersService.delete(idReaderDelete);
                                } else {
                                    System.out.println("\nОбъект НЕ удален!\n");
                                }
                                break;

                            case "0":
                                readersMenu = new ReadersMenu();
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
                    System.out.println("2 - Обновить ФИО читателя");
                    System.out.println("3 - Обновить возраст читателя");
                    System.out.println("4 - Обновить адрес читателя");
                    System.out.println("0 - Вернуться назад\n");

                    System.out.print("Номер действия: ");
                    String updateReader = in.next();
                    System.out.println();

                    try {
                        switch (updateReader) {
                            case "1":
                                var authIDUpdate = getUpdateEntity(readersService);
                                if (authIDUpdate != null) {
                                    System.out.print("ФИО читателя: ");
                                    authIDUpdate.setFullName(bf.readLine());

                                    System.out.print("Возраст читателя: ");
                                    String inputAge = in.next();
                                    int Age;
                                    if (tryParseInt(inputAge)) {
                                        Age = Integer.parseInt(inputAge);
                                    } else {
                                        System.out.println("\nВведено неверное значение!\n");
                                        break;
                                    }
                                    authIDUpdate.setAge(Age);

                                    System.out.print("Адрес читателя: ");
                                    authIDUpdate.setAddressReader(bf.readLine());
                                    System.out.println();

                                    boolean actionReaderUpdateAll = confirmationOfAction();
                                    if (actionReaderUpdateAll) {
                                        readersService.update(authIDUpdate);
                                    } else {
                                        System.out.println("\nОбъект НЕ обновлен!\n");
                                    }
                                }
                                break;

                            case "2":
                                authIDUpdate = getUpdateEntity(readersService);
                                if (authIDUpdate != null) {
                                    System.out.print("ФИО читателя: ");
                                    authIDUpdate.setFullName(bf.readLine());
                                    boolean actionReaderUpdateName = confirmationOfAction();
                                    if (actionReaderUpdateName) {
                                        readersService.update(authIDUpdate);
                                    } else {
                                        System.out.println("\nОбъект НЕ обновлен!\n");
                                    }
                                }
                                break;

                            case "3":
                                authIDUpdate = getUpdateEntity(readersService);
                                if (authIDUpdate != null) {
                                    System.out.print("Возраст читателя: ");
                                    String inputAge = in.next();
                                    int Age;
                                    if (tryParseInt(inputAge)) {
                                        Age = Integer.parseInt(inputAge);
                                    } else {
                                        System.out.println("\nВведено неверное значение!\n");
                                        break;
                                    }
                                    authIDUpdate.setAge(Age);
                                    boolean actionReaderUpdateAge = confirmationOfAction();
                                    if (actionReaderUpdateAge) {
                                        readersService.update(authIDUpdate);
                                    } else {
                                        System.out.println("\nОбъект НЕ обновлен!\n");
                                    }
                                }
                                break;

                            case "4":
                                authIDUpdate = getUpdateEntity(readersService);
                                if (authIDUpdate != null) {
                                    System.out.print("Адрес читателя: ");
                                    authIDUpdate.setAddressReader(bf.readLine());
                                    boolean actionReaderUpdateAddress = confirmationOfAction();
                                    if (actionReaderUpdateAddress) {
                                        readersService.update(authIDUpdate);
                                    } else {
                                        System.out.println("\nОбъект НЕ обновлен!\n");
                                    }
                                }
                                break;

                            case "0":
                                readersMenu = new ReadersMenu();
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
                    boolReader = false;
                    mainMenu = new MainMenu();
                    break;

                default:
                    System.out.println("\nВведено неверное значение\n");
                    break;
            }
        }
    }
}
