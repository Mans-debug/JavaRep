import java.util.Scanner;
import java.util.Stack;

public class PolishNotation
{
    static Boolean tryParseInt(Character input) {
        try {
            Integer.parseInt(input.toString());
        }
        catch (Exception e) {
            return false;
        }
        return true;
    }
    static int charOper(char ch, int x, int y) throws Exception
    {
        int res = 0;
        switch (ch)
        {
            case '*':
                res = x * y;
                break;
            case '+':
                res = x + y;
                break;
            case '-':
                res = y - x;
                break;
            case '/':
                res = y / x;
                break;
            default:
                throw new Exception("Wrong operator");
        }
        return res;
    }

    public static void main(String[] args)
    {
        Stack<Integer> stack = new Stack<>();
        Scanner sc = new Scanner(System.in);

        String str = sc.nextLine();
        for (int i = 0; i < str.length(); i++)
        {
            Character character = str.charAt(i);

            if(tryParseInt(character))
            {
                Character temp = str.charAt(i);
                String integer = temp.toString();
                while (tryParseInt(str.charAt(i+1)))
                {
                    i++;
                    Character temporary = str.charAt(i);
                    integer += temporary.toString();

                }

                stack.add(Integer.parseInt(integer));
            }
            else if(character!=' ')
            {
                try
                {
                    int res = charOper(str.charAt(i), stack.pop(), stack.pop());
                    stack.add(res);
                } catch (Exception e)
                {
                    e.printStackTrace();
                }

            }

        }
        System.out.println(stack.pop());
    }
}
