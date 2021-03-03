import java.util.Scanner;
import java.util.Stack;

public class Main
{
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Stack<Character> stack = new Stack<>();

        String str = sc.next();

        String openBraces = "[{(";
        String closedBraces = "]})";

        for (int i = 0; i < str.length(); i++) {
            Character ch = str.charAt(i);
            if(openBraces.contains(Character.toString(ch)))
            {
                stack.push((Character) ch);
            }
            if(closedBraces.contains(Character.toString(ch)))
            {
                Character peek = stack.peek();
                if(peek<50)
                {
                    peek = (char)(peek + 1);
                }
                else
                {
                    peek = (char)(peek + 2);
                }
                if (ch.equals(peek))
                {
                    stack.pop();
                }
                else
                {
                    throw new Exception("Wrong exception");
                }
            }
        }
        System.out.println("the program is finished, no errors found");
        
    }
}
