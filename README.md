# Проект по автоматизации тестирования интернет-магазина <a target="_blank" href="https://www.wildberries.ru/">Wildberries</a>

## :world_map:    Содержание

➠ [Покрытый функционал](#black_nib-покрытый-функционал)

➠ [Технологический стек](#floppy_disk-технологический-стек)

➠ [Запуск тестов из терминала](#electron-запуск-тестов-из-терминала)

➠ [Удаленный запуск тестов](#удаленный-запуск-тестов)

➠ [Сборка в Jenkins](#-главная-страница-сборки-Jenkins)

➠ [Отчет о результатах тестирования в Allure Report](#-отчет-о-результатах-тестирования-в-allure-report)

➠ [Уведомления в Telegram с использованием бота](#-уведомления-в-telegram-с-использованием-бота)

➠ [Пример запуска теста в Selenoid](#-пример-запуска-теста-в-selenoid)

## <a name="black_nib-покрытый-функционал"></a>:black_nib: Покрытый функционал

### UI

- [x] Наличие заголовка на главной странице
- [x] Проверка функциональности поисковой строки
- [x] Проверка возможности открыть в каталоге категории различных уровней вложенности
- [x] Проверка добавления товара в корзину
- [x] Проверка выбора страны в заголовке страницы
- [x] Проверка выбора адреса доставки в заголовке страницы
- [x] Проверка возможности просмотреть список пунктов выдачи
- [x] Проверка на наличие ошибок в console log

## :floppy_disk: Технологический стек

<p align="center">
<img width="6%" title="IntelliJ IDEA" src="images/logo/Intelij_IDEA.svg">
<img width="6%" title="Java" src="images/logo/Java.svg">
<img width="6%" title="JUnit5" src="images/logo/JUnit5.svg">
<img width="6%" title="Selenide" src="images/logo/Selenide.svg">
<img width="6%" title="Gradle" src="images/logo/Gradle.svg">
<img width="6%" title="GitHub" src="images/logo/GitHub.svg">
<img width="6%" title="Jenkins" src="images/logo/Jenkins.svg">
<img width="6%" title="Selenoid" src="images/logo/Selenoid.svg">
<img width="6%" title="Allure Report" src="images/logo/Allure_Report.svg">
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

## :electron: Запуск тестов из терминала

### Локальный запуск тестов

```
gradle clean test
```

### Удаленный запуск тестов

```
clean
test
-DdefaultBrowserSize=${BROWSER_SIZE}
-DdefaultBrowser=${BROWSER}

```

### Параметры сборки

> <code>BROWSER</code> – браузер, в котором будут выполняться тесты (_по умолчанию - <code>chrome</code>_).
>
> <code>BROWSER_SIZE</code> – размер окна браузера, в котором будут выполняться тесты (_по умолчанию - <code>1920x1080</code>_).

## <img width="4%" title="Jenkins" src="images/logo/Jenkins.svg"> Главная страница сборки [Jenkins](https://jenkins.autotests.cloud/job/C12-vyach_son-hw13_sample_project/)

<p align="center">
  <img src="images/screenshots/Jenkins.PNG">
</p>

## <img width="4%" title="Allure Report" src="images/logo/Allure_Report.svg"> Отчет о результатах тестирования в [Allure Report](https://jenkins.autotests.cloud/job/C12-vyach_son-hw13_sample_project/6/allure/)

### :shield: Главная страница Allure-отчета

<p align="center">
<img title="Allure Overview" src="images/screenshots/allure_overview.PNG">
</p>

### :shield: Страница с тестами

<p align="center">
<img title="Allure Behaviors" src="images/screenshots/allure_behaviors.PNG">
</p>

### :shield: Основной дашборд

<p align="center">
<img title="Allure Overview Dashboard" src="images/screenshots/allure_overview_dashboard01.PNG">
<img src="images/screenshots/allure_overview_dashboard02.PNG">
</p>

## <img width="4%" title="Telegram" src="images/logo/Telegram.svg"> Уведомления в Telegram с использованием бота

> После завершения сборки бот, созданный в <code>Telegram</code>, автоматически обрабатывает и отправляет сообщение с отчетом.
<p align="center">
<img title="Telegram Notifications" src="images/screenshots/telegram_notifications.PNG">
</p>

## <img width="4%" title="Selenoid" src="images/logo/Selenoid.svg"> Пример запуска теста в Selenoid

> К каждому тесту в отчете прилагается видео. Одно из таких видео представлено ниже.

<p align="center">
<video width='100%' height='100%' controls autoplay><source src='images/gif/selenoid_video.mp4' type='video/mp4'></video>
</p>