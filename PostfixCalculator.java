// Gratitude to Geeks for Geeks for help with this assignment
// https://www.geeksforgeeks.org/stack-set-2-infix-to-postfix/
// https://www.geeksforgeeks.org/stack-set-4-evaluation-postfix-expression/
import java.util.*;
public class PostfixCalculator 
{
    public static void main(String[] args)
    {
        String input;
        Scanner in = new Scanner(System.in);
        System.out.println("Enter an expression");
        // removes all spaces in the input
        input = in.nextLine().replaceAll("\\s", "");
        // converts the user input to postfix
        System.out.println(convertToPost(input));
        String inputToConvert = convertToPost(input); 
        // eveluates, and returns the solution for the postfix experession.
        System.out.println(postfixEval(inputToConvert));

    }
    // sets the precedence for the equation symbols to ensure the program calculates in the right order.
    public static int precedence(char ch) 
    { 
        switch (ch) 
        { 
        case '+': 
        case '-': 
            return 1; 
       
        case '*': 
        case '/': 
            return 2; 
       
        case '^': 
            return 3; 
        } 
        return -1; 
    } 
    //
    public static String convertToPost(String input)
    {
        String result = new String(""); 
          
        // initializing empty stack 
        Stack<Character> expression = new Stack<Character>(); 
          
        for (int i = 0; i < input.length(); ++i) 
        { 
            char c = input.charAt(i); 
              
            // If the scanned character is an 
            // operand, add it to output. 
            if (Character.isLetterOrDigit(c)) 
                result += c; 
               
            // If the scanned character is a '(',  
            // push it to the stack. 
            else if (c == '(') 
                expression.push(c); 
              
            //  If the scanned character is a ')',  
            // pop and output from the stack  
            // until an '(' is encountered. 
            else if (c == ')') 
            { 
                while (!expression.isEmpty() &&  
                        expression.peek() != '(') 
                    result += expression.pop(); 
                  
                    expression.pop(); 
            } 
            else // an operator is encountered 
            { 
                while (!expression.isEmpty() && precedence(c)  
                         <= precedence(expression.peek())){ 
                    
                    result += expression.pop(); 
             } 
                expression.push(c); 
            } 
       
        } 
       
        // pop all the operators from the stack 
        while (!expression.isEmpty())
        { 
            if(expression.peek() == '(') 
                return "Invalid Expression"; 
            result += expression.pop(); 
         } 
        return result; 
    }
    public static int postfixEval (String input) 
    {
        //create a stack 
        Stack <Integer> evaluation = new Stack<Integer>(); 
          
        // Scan all characters one by one 
        for(int i = 0; i < input.length(); i++) 
        { 
            char c = input.charAt(i); 
              
            // If the scanned character is an operand (number here), 
            // push it to the stack. 
            if(Character.isDigit(c)) 
            evaluation.push(c - '0'); 
              
            //  If the scanned character is an operator, pop two 
            // elements from stack apply the operator 
            else
            { 
                int x = evaluation.pop(); 
                int y = evaluation.pop(); 
                  
                switch(c) 
                { 
                    // claculates based on the sign
                    case '+': 
                    evaluation.push(x + y); 
                    break; 
                      
                    case '-': 
                    evaluation.push(x - y); 
                    break; 
                      
                    case '/': 
                    evaluation.push(x / y); 
                    break; 
                      
                    case '*': 
                    evaluation.push(x * y); 
                    break; 
                } 
            } 
        } 
        // pops the stack only solution to the expression is left and returns that value
        return evaluation.pop();     
    }
}
