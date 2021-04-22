
import lombok.SneakyThrows;
import org.apache.log4j.Logger;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.util.*;

public class Bot extends TelegramLongPollingBot
{
    private static final String PORT = System.getenv("PORT");
    private HashMap<String, Method> commands;
    static Logger logger = Logger.getLogger(Bot.class);
    Method method = null;
    ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
    ArrayList<String> commandsForButtons;
    int page;


    public Bot()
    {
        commands = new HashMap<>();
        commandsForButtons = new ArrayList<>();

        Class botCommandsClass = new BotCommands().getClass();
        for (Method m : botCommandsClass.getDeclaredMethods())
        {
            if (!m.isAnnotationPresent(Command.class))
                continue;

            Command cmd = m.getAnnotation(Command.class);

            //if a method is still in progress, we don't include it in the map
            if (cmd.inProgress())
                continue;

            for (String name : cmd.possibleNames())
            {
                if (name.toLowerCase().matches("[а-я]+") && !name.equals("Дальше"))
                    commandsForButtons.add(name);
                commands.put(name.toLowerCase(), m);
            }
        }
    }

    @SneakyThrows
    public void run()
    {
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


        try (ServerSocket serverSocket = new ServerSocket(Integer.parseInt(PORT)))
        {
            while (true)
            {
                serverSocket.accept();
            }
        } catch (IOException e)
        {
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

    public String commandProcessor(String input) throws InputMismatchException
    {
        if (input.isEmpty())
            throw new InputMismatchException("Wrong input");

        String[] strings = input.split(" ");
        String command = strings[0].toLowerCase();
        String[] args = Arrays.copyOfRange(strings, 1, strings.length);

        Method m = commands.get(command);

        if (m == null)
        {
            return "There is no such method";
        }
        Command cmd = m.getAnnotation(Command.class);
        if (cmd.takesArgs() && method == null && args.length == 0)
        {
            method = m;
            return "";
        }

        try
        {
            method = null;
            String str = (String) m.invoke(new BotCommands(), (Object) args);
            return str;
        } catch (Exception e)
        {
            System.out.println("Something went wrong while calling the method: " + m.getName() + "\n" + e.getStackTrace());
        }
        return "Something went wrong while processing the input command: " + m.getName();
    }

    @Override
    public void onUpdateReceived(Update update)
    {
        if (!update.hasMessage())
            return;

        Message message = update.getMessage();
        String chatId = String.valueOf(message.getChatId());

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        String messageFromUser = message.getText();

        String output = "";
        buttonsMarkup(messageFromUser);

        if (method == null)
        {
            output += commandProcessor(messageFromUser);
            if (output.equals(""))
                output += "Enter args: ";

        } else
        {
            Command cmd = method.getAnnotation(Command.class);
            output += commandProcessor(cmd.possibleNames()[0] + " " + messageFromUser.trim());
        }


        sendMessage.setText(output);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);


        try
        {
            execute(sendMessage);
        } catch (TelegramApiException e)
        {
            logger.error("Something went wrong while sending message to the user!");
            e.printStackTrace();
        }
    }

    public String buttonsMarkup(String messageFromUser)
    {

        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow keyboardFirstRow = new KeyboardRow();
        KeyboardRow keyboardSecondRow = new KeyboardRow();
        KeyboardRow keyboardThirdRow = new KeyboardRow();
        keyboardThirdRow.add("Дальше");
        if (messageFromUser.equals("Дальше"))
            page += 4;
        if (page >= commandsForButtons.size())
            page = 0;
        for (int i = page, j = 0; j < 4; i++, j++)
        {
            try
            {
                if (i % 2 == 0)
                    keyboardFirstRow.add(commandsForButtons.get(i));
                else
                    keyboardSecondRow.add(commandsForButtons.get(i));
            }
            catch (IndexOutOfBoundsException e)
            {
                break;
            }
        }


        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);
        keyboard.add(keyboardThirdRow);
        replyKeyboardMarkup.setKeyboard(keyboard);

        return "Тыкните на кнопку";

    }
/*
    public boolean nextButton()
    {
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);
        String str = "";
        for (int i = 0, j = 0; i < commandsForButtons.size(); i++, j++)
        {
            str += commandsForButtons.get(i) + " ";
        }
        str += page;
        if (page + 4 >= commandsForButtons.size())
            page = 0;

        Gson gson = new Gson();
        Type listOfMyClassObject = new TypeToken<ArrayList<KeyboardRow>>()
        {
        }.getType();
        ArrayList<KeyboardRow> arrayList = gson.
                fromJson(commandProcessor("Дальше " + str), listOfMyClassObject);
        replyKeyboardMarkup.setKeyboard(arrayList);
        return true;
    }*/
}
