import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import static java.util.Comparator.comparingInt;

class PhoneBookData {
  private static HashMap<String, ArrayList<Integer>> phoneBook = new HashMap<>();

  // Добавление данных в телефонную книгу.
  public void addRes(String name, Integer phoneNum) {
    if (phoneBook.containsKey(name)) {
      phoneBook.get(name).add(phoneNum);
    } else {
      ArrayList<Integer> phones = new ArrayList<>();
      phones.add(phoneNum);
      phoneBook.put(name, phones);
    }
  }

  // Поиск номеров телефона по имени.
  public ArrayList<Integer> find(String name) {
    if (phoneBook.containsKey(name)) {
      return phoneBook.get(name);
    }
    return new ArrayList<Integer>();
  }

  // Вывод телефонной книги.
  public void showPhoneBook() {
    if (!phoneBook.isEmpty()) {

      // Сортировка по убыванию числа телефонов.
      Map<String, List<Integer>> sorted = phoneBook.entrySet().stream()
          .sorted(comparingInt(e -> -e.getValue().size()))
          .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> {
            throw new AssertionError();
          }, LinkedHashMap::new));

      System.out.println(sorted);
    } else
      System.out.println("Телефонная книга пуста :(\n");
  }
}

public class PhoneBook {
  public static void main(String[] args) {
    PhoneBookData phoneBookData = new PhoneBookData();

    while (true) {
      System.out.println("\n1. Добавить запись;\n2. Найти запись;\n3. Вывести весь список;\n0. Выход.\n");
      Scanner scanner = new Scanner(System.in);
      System.out.print("Выберите действие: ");
      int number = scanner.nextInt();
      scanner.nextLine();

      if (number == 0)
        break;
      else if (number == 1) {
        System.out.print("Введите имя: ");
        String name = scanner.nextLine();
        System.out.print("Введите номер: ");
        Integer phoneNum = scanner.nextInt();
        phoneBookData.addRes(name, phoneNum);
      } else if (number == 2) {
        System.out.print("Введите имя для поиска номера(ов): ");
        String name = scanner.nextLine();
        System.out.println(phoneBookData.find(name));
      } else if (number == 3)
        phoneBookData.showPhoneBook();
      else
        System.out.print("Команда не распознана. Попробуйте еще раз.\n");
    }
  }
}