## Проект по автоматизации тестирования интернет-магазина Wildberries
> <a target="_blank" href="https://www.wildberries.ru/">Ссылка на главную страницу сайта</a>

## :page_facing_up: Содержание:
- <a href="#computer-технологии-и-инструменты">Технологии и инструменты</a>
- <a href="#black_nib-реализованные-проверки">Реализованные проверки</a>
- <a href="#electric_plug-сборка-в-jenkins">Сборка в Jenkins</a>
- <a href="#arrow_forward-запуск-из-терминала">Запуск из терминала</a>
- <a href="#open_book-allure-отчет">Allure отчет</a>
- <a href="#-отчет-в-telegram">Отчет в Telegram</a>
- <a href="#-видео-пример-прохождения-теста">Видео пример прохождения теста</a>

## :computer: Технологии и инструменты
<p align="center">
<img width="6%" title="IntelliJ IDEA" src="images/logo/Intelij_IDEA.svg">
<img width="6%" title="Java" src="images/logo/Java.svg">
<img width="6%" title="Selenide" src="images/logo/Selenide.svg">
<img width="6%" title="Selenoid" src="images/logo/Selenoid.svg">
<img width="6%" title="Allure Report" src="images/logo/Allure_Report.svg">
<img width="6%" title="Gradle" src="images/logo/Gradle.svg">
<img width="6%" title="JUnit5" src="images/logo/JUnit5.svg">
<img width="6%" title="GitHub" src="images/logo/GitHub.svg">
<img width="6%" title="Jenkins" src="images/logo/Jenkins.svg">
<img width="6%" title="Telegram" src="images/logo/Telegram.svg">
</p>

```mermaid        
    stateDiagram-v2
        State1: START
        State2: Java & IntelliJ IDEA
        State3: Selenide & JUnit5
        State4: Gradle
        State5: GitHub
        State6: Jenkins
        State7: Selenoid
        State8: Allure Report
        State9: Telegram
        State10: STOP
        State1 --> State2
        State2 --> State3
        State3 --> State4
        State4 --> State5
        State5 --> State6
        State6 --> State7
        State7 --> State8
        State8 --> State9
        State9 --> State10
        note right of State2 : Работа с кодом
        note left of State3 : Фреймворки
        note right of State4 : Сборка проекта
        note left of State5 : Система контроля версий и хостинг проекта
        note right of State6 : Параметризация и запуск сборки
        note left of State7 : Контейнеризация
        note right of State8 : Отчётность
        note left of State9 : Уведомления
```

## :black_nib: Реализованные проверки
- Наличие заголовка на главной странице
- Проверка функциональности поисковой строки
- Проверка возможности открыть в каталоге категории различных уровней вложенности
- Проверка добавления товара в корзину
- Проверка выбора страны в заголовке страницы
- Проверка выбора адреса доставки в заголовке страницы
- Проверка возможности просмотреть список пунктов выдачи
- Проверка на наличие ошибок в console log

## :electric_plug: Сборка в Jenkins
### <a target="_blank" href="https://www.wildberries.ru/">Сборка в Jenkins</a>
*Для запуска сборки необходимо указать значения параметров и нажать кнопку <code><strong>*Собрать с параметрами*</strong></code>.*
<p align="center">
<img title="Jenkins Dashboard" src="images/screenshots/Jenkins.png">
</p>

### Параметры сборки в Jenkins:
Сборка в Jenkins
- browser (браузер, по умолчанию chrome)
- size (размер окна браузера, по умолчанию 1920x1080)
- необходимо добавить файл resources/config/remote.properties (по примеру local.properties)

## :arrow_forward: Запуск из терминала
Локальный запуск:
```
gradle clean test
```

Удаленный запуск:
```
clean
test
-Dbrowser=${BROWSER}
-Dsize=${BROWSER_SIZE}
```

## :open_book: Allure отчет
- ### Главный экран отчета
<p align="center">
<img title="Allure Overview Dashboard" src="images/screenshots/allure_overview.png">
</p>

- ### Страница с проведенными тестами
<p align="center">
<img title="Allure Test Page" src="images/screenshots/allure_behaviors.png">
</p>

- ### Основной дашборд
<p align="center">
<img src="images/screenshots/allure_overview_dashboard01.png">
<img src="images/screenshots/allure_overview_dashboard02.png">
</p>

## <img width="4%" title="Telegram" src="images/logo/Telegram.svg"> Отчет в Telegram

> После завершения сборки бот, созданный в <code>Telegram</code>, автоматически обрабатывает и отправляет сообщение с отчетом.
<p align="center">
<img title="Telegram notification message" src="images/screenshots/telegram_notifications.png" width="50%">
</p>

## <img width="4%" title="Selenoid" src="images/logo/Selenoid.svg"> Видео пример прохождения теста
> К каждому тесту в отчете прилагается видео. Одно из таких видео представлено ниже.
>
https://user-images.githubusercontent.com/103368614/173416855-a19498b8-752c-4e30-8a05-c5c5af154d8d.mp4
