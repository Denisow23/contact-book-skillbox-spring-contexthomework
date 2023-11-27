# ContactsBook

## Описание
Данный проект разработан в рамках практического задания курса "фреймворк Spring" онлайн-школы SkillBox. 
Проект иммитирует работы приложения "Контакты". Ввод и вывод данных осуществляется посредством консоли, или чтением/записью в файл в зависимости от настроек приложения.

## Цели работы ##

Закрепить знания следующих тем:

+ Работа с контекстом Spring
+ Работа с бинами.
+ Настройка бинов.
+ Работа со свойствами приложения.
+ Конфигурация профилей приложения.
+ Работа с методами жизненного цикла.

## Профили
В приложении предусмотрены два профиля Spring: ***init*** и ***default***.
Изменение профиля производится в файле application.yml([здесь](https://github.com/Denisow23/contact-book-skillbox-spring-contexthomework/blob/master/src/main/resources/application.yml)) 

Пример настройки профиля:
```
spring:
  profiles:
    active : init
```
По умолчанию установлен профил ***init***

Профиль ***init*** предполагает инициализацию контактов из файла [initContacts](https://github.com/Denisow23/contact-book-skillbox-spring-contexthomework/blob/master/src/main/resources/initContacts.txt). Формат добавления контактов в файл следующий:
```
<ФИО>; <Номер телефона>; <Адрес электронной почты>
```
При этом предусмотрены проверки ввода на правильности для строки контакта, электронной почти и номера телефона. При возникновении ошибки программа оповестит пользователя об ошибке с указанаием ее места.

## Функционал

Взаимодействие с приложением происходит через консольное меню. Выбор необходимого пункта производится вводом цифры в консоль. Защита от неверного ввода пункта меню предусмотрена с помощью блока **try-catch**, а также обработкой **default** в блоке **switch-case**. В случае неудачного выбора пункта меню программа сообщит об этом пользователю сообщением **"Такой команды не сущетсвует!"**.  Меню состоит из следующих пунктов:
+ add
+ delete
+ view
+ save to file

### add

Команда добавления контакта.

Формат добавления контакта:
```
<ФИО>; <Номер телефона>; <Адрес электронной почты>
```
При этом предусмотрены проверки ввода на правильности для строки контакта, электронной почти и номера телефона. При возникновении ошибки программа оповестит пользователя об ошибке с указанаием ее места.
В случае успешного добавления выводится сообщение **"Контакт записан!"**. В случае, если контакт с данным именем уже сущетсвует, выводится команда **"Такой контакт уже существует"**

### delete

Команда удаления контакта.

В случае вызова команды **delete** программа запросит имя контакта, который необходимо удалить. Необходимо ввести полное имя контакта. В случае успешного удаления контакта программа оповестит пользователя сообщением **"Контакт удален!"**. В случае, если указанного контакта не существует, программа сообщит об этом сообщением **"Такого контакта не сущетсвует!"**.

### view

Команда выведения списка контактов на экран.

При наличии хотя бы одного контакта команда возвращает список контактов в формате: 
```
<ФИО> | <Номер телефона> | <Адрес электронной почты>
```
Если же список контактов пуст, программа уведомит об этом пользователя сообщением вида "Список контактов пуст!"

### save to file

Команда сохранения списка контактов в файл. В случае успешного выполнения программа оповестит пользователя сообщением **"Контакты записаны в файл!"**, в случае неудачи **"Ошибка записи в файл!"**.
Путь к файлу сохранения можно менять в файле [application.yml](https://github.com/Denisow23/contact-book-skillbox-spring-contexthomework/blob/master/src/main/resources/application.yml)
```
app:
  pathToSave : "src/main/resources/savedContacts.txt"
```

