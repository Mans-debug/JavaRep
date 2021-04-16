import lombok.SneakyThrows;
import org.apache.log4j.Logger;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.util.Arrays;
import java.util.HashMap;
import java.util.InputMismatchException;

public class Bot extends TelegramLongPollingBot
{
    private static final String PORT = System.getenv("PORT");
    private HashMap<String, Method> commands;
    static Logger logger = Logger.getLogger(Bot.class);

    public Bot() {
        commands = new HashMap<>();

        Class botCommandsClass = new BotCommands().getClass();
        for (Method m : botCommandsClass.getDeclaredMethods()) {
            if (!m.isAnnotationPresent(Command.class))
                continue;

            Command cmd = m.getAnnotation(Command.class);

            //if a method is still in progress, we don't include it in the map
            if(cmd.inProgress())
                continue;

            for (String name : cmd.possibleNames())
                commands.put(name, m);
        }
    }

    public String commandProcessor(String input) throws InputMismatchException
    {
        if(input.isEmpty())
            throw new InputMismatchException("Wrong input");

        String[] strings = input.split(" ");
        String command = strings[0].toLowerCase();
        String[] args = Arrays.copyOfRange(strings,1,  strings.length);

        Method m = commands.get(command);

        if(m == null)
            throw new InputMismatchException("There is no such method");

        try
        {
           return (String) m.invoke(new BotCommands(), (Object) args);
        }
        catch (Exception e)
        {
            System.out.println("Something went wrong while calling the method: " + m.getName());
        }
            return "Something went wrong while processing the input command";
    }

    @SneakyThrows
    public void run() {
        TelegramBotsApi botsApi = null;
        try
        {
            botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new Bot());
        } catch (TelegramApiException e)
        {
            logger.error("Something went wrong while registration");
            e.printStackTrace();
        }


        try (ServerSocket serverSocket = new ServerSocket(Integer.parseInt(PORT))) {
            while (true) {
                serverSocket.accept();
            }
        } catch (IOException e) {
            logger.error("IO exception occurred during run method.\n");
            e.printStackTrace();
        }
    }



    @Override
    public String getBotUsername()
    {
        return "MansMM_bot";
    }

    @Override
    public String getBotToken()
    {
        return "1619416274:AAHVpqO29DrsFIvuIOq2Ad6vovTnbZu1MLQ";
    }

    @Override
    public void onUpdateReceived(Update update)
    {
        if(!update.hasMessage())
            return;

        Message message = update.getMessage();
        String chatId = String.valueOf(message.getChatId());

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);

        String messageFromUser = message.getText();

        String output = commandProcessor(messageFromUser);

        sendMessage.setText(output);

        try
        {
            execute(sendMessage);
        } catch (TelegramApiException e)
        {
            logger.error("Something went wrong while sending message to the user!");
            e.printStackTrace();
        }


    }
}
