
<h2 align="center">Проект по автоматизации для МТС ЛИНК <a target="_blank" href="https://mts-link.ru/products/meetings/"></a> </h2>

<p align="center">
<img title="mts-link" src="images/mts-link.jpg">
</p>

<a name="наверх"></a>

# :green_book: *Содержание*
+ [Технологии и инструменты](#Technology)
+ [Особенности проекта](#особенности-проекта)
+ [Примеры автоматизированных тест кейсов](#TestCases)
+ [Запуск тестов в Jenkins](#Jenkins)
+ [Команды для запуска из терминала](#SystemProperty)
+ [Отчет о результатах тестирования в Allure Report](#AllureReport)
+ [Интеграция с Allure TestOps](#AllureTestOps)
+ [Интеграция с Jira](#Jira)
+ [Уведомление в Telegram при помощи Alert bot](#Telegram)
+ [Пример видео выполнения теста на Browserstack](#Browserstack)

<h1 align="left">
<a name="Technology"><i>Технологии и инструменты</i></a>
</h1>


<p align="center">  
<a href="https://www.jetbrains.com/idea/"><img src="images/Intelij_IDEA.svg" width="50" height="50"  alt="IDEA"/></a>  
<a href="https://www.java.com/"><img src="images/Java.svg" width="50" height="50"  alt="Java"/></a>  
<a href="https://github.com/"><img src="images/Github.svg" width="50" height="50"  alt="Github"/></a>  
<a href="https://junit.org/junit5/"><img src="images/JUnit5.svg" width="50" height="50"  alt="JUnit 5"/></a>  
<a href="https://gradle.org/"><img src="images/Gradle.svg" width="50" height="50"  alt="Gradle"/></a>  
<a href="https://selenide.org/"><img src="images/Selenide.svg" width="50" height="50"  alt="Selenide"/></a>
<a href="https://github.com/allure-framework/allure2"><img src="images/Allure_Report.svg" width="50" height="50"  alt="Allure"/></a> 
<a href="https://qameta.io/"><img src="images/AllureTestOps.svg" width="50" height="50"  alt="Allure TestOps"/></a>
<a href="https://www.jenkins.io/"><img src="images/Jenkins.svg" width="50" height="50"  alt="Jenkins"/></a>  
<a href="https://www.atlassian.com/ru/software/jira/"><img src="images/Jira.svg" width="50" height="50"  alt="Jira"/></a>  
</p>

## Особенности проекта
- Проект состоит из веб-тестов (UI) и тестов API
- В данном проекте автотесты написаны на языке `Java`
- В качестве сборщика был использован - `Gradle`
- Использованы фреймворки `JUnit 5` и `Selenide`
- Шаблон проектирования `Page Object`
- Использование техноголии `Owner` для придания тестам гибкости и легкости конфигурации
- Возможность запуска тестов: локально, удалённо, по тегам
- Использование `Faker` для генерации данных
- Использование `Lombok` для моделей в API тестах
- Возможность запуска проектов с помощью `Jenkins`
- Возможность запуска тестов напрямую из `Allure TestOps`
- Интеграция с `Jira`
- Уведомление о результатах прохождения в `Telegram`
- По итогу прохождения автотестов генерируется `Allure` отчет. Содержание отчета:
    - Шаги теста
    - Скриншот страницы на последнем шаге
    - Исходный код страницы в браузере
    - Логи консоли браузера
    - Видео выполнения автотеста

<h1 align="left">
<a name="TestCases"><i>Примеры автоматизированных тест кейсов</i></a>
</h1>

### Web
- :white_check_mark: Проверка успешной авторизации пользователя
- :white_check_mark: Проверка неуспешной авторизации пользователя с проверкой логина и пароля 
- :white_check_mark: Проверка полей формы авторизации при разной локализации `ParameterizedTest`: `Русский`, `English`
- :white_check_mark: Проверка усрешного редактирования профиля пользователя
- :white_check_mark: Проверка успешного создания быстрой встречи
- :white_check_mark: Проверка успешного создания постоянной встречи
- :white_check_mark: Проверка успешного создания запланированной встречи
- :white_check_mark: Проверка успешного редактирования запланированной встречи
- :white_check_mark: Проверка успешного удаления мероприятия

### API
- :white_check_mark: Проверка успешной авторизации пользователя
- :white_check_mark: Проверка неуспешной авторизации пользователя с проверкой логина и пароля
- :white_check_mark: Проверка успешного создания шаблона мероприятия
- :white_check_mark: Проверка успешного создания мероприятия по шаблону без параметров
- :white_check_mark: Проверка неуспешного создания мероприятия по шаблону с невалидными параметрами
- :white_check_mark: Проверка успешного создания мероприятия по шаблону с параметрами, заданными в файле
- :white_check_mark: Проверка успешного получения данных мероприятия
- :white_check_mark: Проверка успешного удаления мероприятия

<h1 align="left">
<img src="images/Jenkins.svg" width="25" height="25" alt="Jenkins"/>  <a name="Jenkins"><i>Запуск тестов в Jenkins</i></a>
</h1>

<a target="_blank" href="https://jenkins.autotests.cloud/job/qa-guru-diplom-mts-link-tests/">**Сборка в Jenkins**</a>
<p align="center">  
<a href="https://jenkins.autotests.cloud/job/qa-guru-diplom-mts-link-tests/"><img src="images/jenkins.jpg" alt="Jenkins"/></a>  
</p>

<h1 align="left">
<a name="SystemProperty"><i>Команды для запуска из терминала</i></a>
</h1>

### Допустимые комбинации

```mermaid 
flowchart LR
    A(gradle) --> B(clean)
    B --> C{Выбрать тег}
    C --> D[test]
    C --> E[web]
    C --> F[api]
    E --> G[webAll]
    E --> H[webEvents]
    E --> J[webAccount]
    F --> K[ApiAll]
    F --> L[ApiEvents]
    F --> M[ApiAccount]
```

***Локальный запуск:***
> Для работы со своей учетной записью на https://mts-link.ru/products/meetings/ следует отредактировать файл - authData.properties

```bash  
gradle test -Denv=remote
```

***Удаленный запуск (через browserstack):***
```bash  
gradle test -Denv=local
```


<h1 align="left">
<img src="images/Allure_Report.svg" width="25" height="25" alt="Allure_Report"/>  <a name="AllureReport"><i>Отчет о результатах тестирования в Allure</i></a>
</h1>

<a target="_blank" href="https://jenkins.autotests.cloud/job/qa-guru-diplom-mts-link-tests/allure/">**Отчёт в Allure**</a>
<p align="center">  



### *Основная страница отчёта*

<p align="center">  
<img title="Allure Overview Dashboard" src="images/Allure_Report_Overview.jpg">  
</p>  

### *Тест кейсы*

<p align="center">  
<img title="Allure Tests" src="images/Test_cases.jpg">  
</p>

### *Графики*

 <p align="center">  
<img title="Allure Graphics" src="images/Allure_Charts.jpg">  
</p>


<h1 align="left">
<img src="images/AllureTestOps.svg" width="25" height="25" alt="AllureTestOps"/> <a name="AllureTestOps"><i>Интеграция с Allure TestOps</i></a>
</h1>

<a target="_blank" href="https://allure.autotests.cloud/project/4352/dashboards">**Проект в TestOps**</a>
<p align="center">  


<p align="center">  
<img title="Allure TestOps Dashboard" src="images/Allure_Test_Ops_Dashboard.jpg">  
</p>  

## *Тест кейсы*

<p align="center">  
<img title="Allure TestOps Tests" src="images/TestOpsTests.jpg">  
</p>


<h1 align="left">
<img src="images/Jira.svg" width="25" height="25" alt="Jira"/> <a name="Jira"><i>Интеграция с Jira</i></a>
</h1>

<a target="_blank" href="https://jira.autotests.cloud/browse/HOMEWORK-1338">**Тикет в Jira**</a>
<p align="center">  


<p align="center">  
<img title="Jira" src="images/JiraTicket.jpg">  
</p>

<h1 align="left">
<img src="images/Telegram.svg" width="25" height="25"  alt="Allure"/> <a name="Telegram"><i>Уведомление в Telegram при помощи Alert bot</i></a>
</h1>

<p align="center">  
<img title="Telegram notification" src="images/Telegram_bot.jpg">  
</p>

<h1 align="left">
<a name="Browserstack"><i>Пример видео выполнения теста</i></a>
</h1>

<p align="center"> 
<img title="Video" src="images/video.gif" width="450" height="450"  alt="video">   
</p>

[Наверх ⬆](#наверх)
