### A Telegram bot

Bot URL : https://t.me/spolish_kbot_bot

### How to use

Please use commands below to manage your notification

    /start hello 
 Returns the Bot version

### Local use

1. ```sudo git clone https://github.com/spolish/kbot.git```
2. Create a bot and get a token from the [BotFather](https://t.me/BotFather)
3. Get token for bot and user it as variable `TELE_TOKEN`
    ```
    read -s TELE_TOKEN 
    export TELE_TOKEN
    go build -ldflags "-X="github.com/spolish/kbot/cmd.appVersion=<your-app-version>
   ```