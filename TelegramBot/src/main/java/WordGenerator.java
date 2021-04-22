import java.awt.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Stream;

public class WordGenerator
{
    HashMap<String, HashMap<String, HashMap<String, Integer>>>
            wordMap;
    File source;

    WordGenerator(File file)
    {
        source = file;
        wordMap = new HashMap<>();
        createDictionary();
    }

    private String reformat(String str)
    {
        str = str.trim();
        String characters = ".!@#&()–[{}]:;?/*`~$^+=<>“123456789";
        StringBuilder builder = new StringBuilder(str);
        for (int i = 0; i < builder.length(); i++)
        {
            Character ch = builder.charAt(i);
            if (characters.contains(ch.toString()))
                builder.deleteCharAt(i);
        }
        return builder.toString();
    }

    private int createDictionary()
    {
        Scanner sc = null;
        try
        {
            sc = new Scanner(source);
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
            return -1;
        }
        String first = reformat(sc.next()).toLowerCase();
        String second = reformat(sc.next()).toLowerCase();
        while (sc.hasNext())
        {
            String third = reformat(sc.next()).toLowerCase();

            if (first.equals("") || second.equals("") || third.equals(""))
            {
                first = second;
                second = third;
                continue;
            }

            if (wordMap.containsKey(first))
            {
                if (wordMap.get(first).containsKey(second))
                {
                    if (wordMap.get(first).get(second).containsKey(third))
                    {
                        wordMap
                                .get(first)
                                .get(second)
                                .put(third, wordMap.get(first).get(second).get(third) + 1);
                    } else
                    {
                        wordMap
                                .get(first)
                                .get(second)
                                .put(third, 1);
                    }
                } else
                {
                    HashMap<String, Integer> lastWord = new HashMap<>();
                    lastWord.put(third, 1);

                    wordMap.get(first).put(second, lastWord);
                }
            } else
            {
                HashMap<String, Integer> lastWord = new HashMap<>();
                lastWord.put(third, 1);

                HashMap<String, HashMap<String, Integer>> secondWord = new HashMap<>();
                secondWord.put(second, lastWord);

                wordMap.put(first, secondWord);
            }

            first = second;
            second = third;
        }
        return 1;
    }

    /*private int createDictionary()
    {
        Scanner sc = null;
        FileStorage storage = new FileStorage();
        sc = new Scanner(storage.);
        String first = reformat(sc.next()).toLowerCase();
        String second = reformat(sc.next()).toLowerCase();
        while (sc.hasNext())
        {
            String third = reformat(sc.next()).toLowerCase();

            if (first.equals("") || second.equals("") || third.equals(""))
            {
                first = second;
                second = third;
                continue;
            }

            if (wordMap.containsKey(first))
            {
                if (wordMap.get(first).containsKey(second))
                {
                    if (wordMap.get(first).get(second).containsKey(third))
                    {
                        wordMap
                                .get(first)
                                .get(second)
                                .put(third, wordMap.get(first).get(second).get(third) + 1);
                    } else
                    {
                        wordMap
                                .get(first)
                                .get(second)
                                .put(third, 1);
                    }
                } else
                {
                    HashMap<String, Integer> lastWord = new HashMap<>();
                    lastWord.put(third, 1);

                    wordMap.get(first).put(second, lastWord);
                }
            } else
            {
                HashMap<String, Integer> lastWord = new HashMap<>();
                lastWord.put(third, 1);

                HashMap<String, HashMap<String, Integer>> secondWord = new HashMap<>();
                secondWord.put(second, lastWord);

                wordMap.put(first, secondWord);
            }

            first = second;
            second = third;
        }
        return 1;
    }*/

    public String createSentence(int wordNumber)
    {
        String ret = "";

            if (wordNumber > 100)
                wordNumber = 100;
            ArrayList<String> res = new ArrayList<>();
            for (int i = 0; i < wordNumber - 2; i++)
            {
                String[] strings;
                if (i == 0)
                {
                    strings = getRandomSequence("", "");
                    res.add(strings[0]);
                    res.add(strings[1]);
                    res.add(strings[2]);
                } else
                {
                    strings = getRandomSequence(res.get(res.size() - 2), res.get(res.size() - 1));
                    res.add(strings[2]);
                }

            }
            ret += String.join(" ", res).replaceAll("[A-Za-z]+", "");
        return ret;
    }

    private String[] getRandomSequence(String first, String second)
    {
        Random random = new Random();
        String res = "";
        while  (first.equals("") && second.equals("")|| (!wordMap.containsKey(first)))
        {
            ArrayList<String> firstWords = new ArrayList<>(wordMap.keySet());
            int randomFirstWord = random.nextInt(firstWords.size());
            first = firstWords.get(randomFirstWord);

            ArrayList<String> secondWords = new ArrayList<>(wordMap
                    .get(first)
                    .keySet());
            int randomSecondWord = random.nextInt(secondWords.size());
            second = secondWords.get(randomSecondWord);
        }

        try
        {
            wordMap.get(first).get(second).keySet();
        }
        catch (NullPointerException e)
        {
            ArrayList<String> secondWords = new ArrayList<>(wordMap
                    .get(first)
                    .keySet());
            int randomSecondWord = random.nextInt(secondWords.size());
            second = secondWords.get(randomSecondWord);
        }
        int sum = 0;
        for (String key : wordMap.get(first).get(second).keySet())
        {
            sum += wordMap.get(first).get(second).get(key);
        }
        int randNum = random.nextInt(sum);
        sum = 0;
        String third = "";
        for (String key : wordMap.get(first).get(second).keySet())
        {
            sum += wordMap.get(first).get(second).get(key);
            if (sum >= randNum)
            {
                third += key;
                break;
            }
        }
        return new String[]{first, second, third};
    }


}
