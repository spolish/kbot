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


   # For Jenkins and ghcr.io use next manual

#### Create a new personal access token (classic) with the appropriate scopes for the tasks you want to accomplish. If your organization requires SSO, you must enable SSO for your new token.

    Note: By default, when you select the write:packages scope for your personal access token (classic) in the user interface, the repo scope will also be selected. The repo scope offers unnecessary and broad access, which we recommend you avoid using for GitHub Actions workflows in particular. For more information, see "Security hardening for GitHub Actions." As a workaround, you can select just the write:packages scope for your personal access token (classic) in the user interface with this url: https://github.com/settings/tokens/new?scopes=write:packages.
- Select the read:packages scope to download container images and read their metadata.
- Select the write:packages scope to download and upload container images and read and write their metadata.
- Select the delete:packages scope to delete container images.
