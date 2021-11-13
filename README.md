# Тестовое задание vk chat-bot 

### [Vk-chat-bot](https://vk.com/public208783602)
Бот развёрнут на EC2 AWS по адресу http://3.120.184.130 с помощью GitHub Actions.
При каждом новом коммите происходит следующее:
- CI: сборка приложения, создаётся докер образ и помещается в GitHub Packages.
- CD: деплой приложения на EC2 AWS, и запускается на нём с помощью docker-compose

Переменные среды:
- VK_BOT_TOKEN - токен vk бота
- VK_BOT_GROUP_ID - group_id бота
- VK_BOT_CODE_ANSWER_SERVER - строка ответа вашего сервера, при регистрации бота в CallBack API