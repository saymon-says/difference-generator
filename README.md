[![Actions Status](https://github.com/saymon-says/java-project-lvl2/workflows/my-project-check/badge.svg)](https://github.com/saymon-says/java-project-lvl2/actions)
[![Maintainability](https://api.codeclimate.com/v1/badges/2b8e8f54326dcc50109c/maintainability)](https://codeclimate.com/github/saymon-says/java-project-lvl2/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/2b8e8f54326dcc50109c/test_coverage)](https://codeclimate.com/github/saymon-says/java-project-lvl2/test_coverage)

### Описание
___
Вычислитель отличий – программа, определяющая разницу между двумя структурами данных. Это популярная задача, для решения которой существует множество онлайн-сервисов, например: http://www.jsondiff.com/. Подобный механизм используется при выводе тестов или при автоматическом отслеживании изменении в конфигурационных файлах.
### Реализовано
___
* Поддержка разных входных форматов: yaml и json
* Генерация отчета в виде plain text, stylish и json
### Технологии и подход к разработке
___
* принцип TDD(test-driven development) c использованием фреймворка Junit5 и библиотекой jacoco для анализа покрытия тестами;
* библиотека picocli для работы в командной строке;
* jackson для парсинга файлов
* CodeClimate, Github Actions.
### Требования
___
* OpenJDK_14
* Gradle 7.2
* Make
### Запуск
___
В терминале папки проекта выполнить команду `make install` & `make run-dist`, приложение консольное.
### Примеры работы:
___
![stylish](https://user-images.githubusercontent.com/43708964/130280786-002fa62f-81e6-41d9-bae7-45b49c7441a7.jpg)
![json](https://user-images.githubusercontent.com/43708964/130280789-168f312d-f0bb-46ee-8c50-5846c5883917.jpg)
![Plain](https://user-images.githubusercontent.com/43708964/130280791-c2021679-46fc-456c-a2fd-5e6b3a63dd55.jpg)
