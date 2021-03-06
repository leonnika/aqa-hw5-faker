# Тестирование приложения "Заказ доставки карты"
## Краткое описание
Приложение  выполняет заказ доставки карты. 

Требования к содержимому полей:
* Город - один из административных центров субъектов РФ
* Дата - не ранее трёх дней с текущей даты
* Поле Фамилия и имя - разрешены только русские буквы, дефисы и пробелы
* Поле телефон - только цифры (11 цифр), символ + (на первом месте)
* Флажок согласия должен быть выставлен

 Если заполнить форму повторно теми же данными за исключением "Даты встречи", то система предложит перепланировать время встречи.
 После нажатия на кнопке "Перепланировать" произойдёт перепланирование встречи.
 Выходит окно об успешном перепданировании.
## Руководство использования
При разработке автотестирования использовались Faker, Lombok, Data-классы (для группировки нужных полей) и утилитный класс-генератор данных.
* Запустите приложение командой 

java -jar ./artifacts/app-card-delivery.jar
* Запустите автотесты командой

./gradlew test -Dselenide.headless=true --info

* Проверить успешность сборки CI на Appveyor


[![Build status](https://ci.appveyor.com/api/projects/status/ht1fpabgknw8fc5n/branch/master?svg=true)](https://ci.appveyor.com/project/leonnika/aqa-hw5-faker/branch/master)

## Результаты тестирования
В ходе тестирования были выявлены ошибки приложения. По ошибкам заведены issue:

[Не создается заявка при вводе в поле Фамилия Имя данных с буквой Ё ](https://github.com/leonnika/aqa-hw5-faker/issues/1)

[Ошибочная отправка заявки при вводе ФИ разными регистрами](https://github.com/leonnika/aqa-hw5-faker/issues/2)

[Ошибочная отправка заявки при вводе в поле телефон не валидных данных](https://github.com/leonnika/aqa-hw5-faker/issues/3)

[Неправильная отправка заявки при вводе в поле телефон специальных символов](https://github.com/leonnika/aqa-hw5-faker/issues/4)


[Ошибочное перепланирование встречи при заявке с той же датой](https://github.com/leonnika/aqa-hw5-faker/issues/5)

[Ошибочная отправка заявки при вводе в поле ФИ только фамилии](https://github.com/leonnika/aqa-hw5-faker/issues/6)

