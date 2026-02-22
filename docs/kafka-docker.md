# Kafka в Docker

Инфраструктура Kafka для локальной разработки: брокер в KRaft-режиме (образ `apache/kafka:3.7.0`), тестовый топик и веб-дашборд (Kafka UI).

## Запуск

Только Kafka и дашборд (без остальных сервисов):

```bash
docker compose up -d kafka kafka-init kafka-ui
```

Только брокер Kafka (без создания топика и дашборда):

```bash
docker compose up -d kafka
```

## Проверка healthcheck

После запуска дождитесь перехода сервиса в состояние `healthy`:

```bash
docker compose ps
```

У сервиса `kafka` в колонке статуса должно быть `healthy`. Проверка выполняется командой `kafka-topics.sh --list --bootstrap-server localhost:9092` внутри контейнера.

Ручная проверка из контейнера:

```bash
docker compose exec kafka kafka-topics.sh --list --bootstrap-server localhost:9092
```

Команда должна выполниться без ошибки и вывести список топиков.

## Тестовый топик

Имя тестового топика: **`notify.test`**.

Он создаётся сервисом `kafka-init` при старте (после перехода Kafka в healthy). В списке топиков он должен отображаться после запуска `docker compose up -d kafka kafka-init kafka-ui`.

## Дашборд (Kafka UI)

- **URL:** http://localhost:8085  
- Подключение к кластеру настроено в docker-compose (переменные `KAFKA_CLUSTERS_0_*`), при открытии в браузере сразу виден кластер и тестовый топик `notify.test`.

Запуск дашборда вместе с Kafka и init:

```bash
docker compose up -d kafka kafka-init kafka-ui
```

После перехода `kafka` и `kafka-init` в healthy дашборд будет доступен по указанному URL.
