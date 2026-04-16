import java.util.Stack;

public class stack {

    // Function to check precedence
    static int precedence(char ch) {
        switch (ch) {
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

    // Function to check if operand
    static boolean isOperand(char ch) {
        return Character.isLetterOrDigit(ch);
    }

    // Infix to Postfix
    static String infixToPostfix(String exp) {
        StringBuilder result = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (char ch : exp.toCharArray()) {

            if (isOperand(ch)) {
                result.append(ch);
            }

            else if (ch == '(') {
                stack.push(ch);
            }

            else if (ch == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result.append(stack.pop());
                }
                stack.pop();
            }

            else { // operator
                while (!stack.isEmpty() &&
                        precedence(stack.peek()) >= precedence(ch)) {
                    result.append(stack.pop());
                }
                stack.push(ch);
            }
        }

        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }

        return result.toString();
    }

    // Reverse string
    static String reverse(String exp) {
        return new StringBuilder(exp).reverse().toString();
    }

    // Infix to Prefix
    static String infixToPrefix(String exp) {
        exp = reverse(exp);

        // Replace brackets
        char[] chars = exp.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(')
                chars[i] = ')';
            else if (chars[i] == ')')
                chars[i] = '(';
        }

        String postfix = infixToPostfix(new String(chars));
        return reverse(postfix);
    }

    public static void main(String[] args) {
        String exp = "(A-B/C)*(A/K-L)";

        System.out.println("Infix:   " + exp);
        System.out.println("Postfix: " + infixToPostfix(exp));
        System.out.println("Prefix:  " + infixToPrefix(exp));
    }
}