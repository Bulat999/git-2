import java.util.List;
import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
               char operation = '0';
        int sumSyn = 0;
        String regex = "\\d+";
        Scanner scan = new Scanner(System.in);
        String expression = scan.nextLine();
        int max = expression.length();
        for (int i = 0; i < expression.length(); i++) {
            switch (expression.charAt(i)) {
                case '+':
                    operation = '+';
                    sumSyn = i;
                    break;
                case '-':
                    operation = '-';
                    sumSyn = i;
                    break;
                case '*':
                    operation = '*';
                    sumSyn = i;
                    break;
                case '/':
                    operation = '/';
                    sumSyn = i;
                    break;
            }
        }
        String number1 = expression.substring(0, sumSyn);
        String number2 = expression.substring(sumSyn + 1, max);
           int numberInt1 = 0;
        int numberInt2 = 0;
        boolean romanCheck = false;
        int summ = 0;
        if (number1.matches(regex) && number2.matches(regex)) {
            numberInt1 = Integer.parseInt(number1);
            numberInt2 = Integer.parseInt(number2);
        } else if (checkForRoman(number1) && checkForRoman(number2)) {
            numberInt1 = romanToArabic(number1);
            numberInt2 = romanToArabic(number2);
            romanCheck = true;
        } else {
          System.out.println("Ошибка! Необходимо чтобы все числа были или арабскими или римскими");
        }
if ((numberInt1>=1)&&(numberInt1<=10)&&(numberInt2>=1)&&(numberInt2<=10)) {
    if (romanCheck == false) {
        System.out.println(valueRes(numberInt1, numberInt2, operation));
    } else {
        summ = valueRes(numberInt1, numberInt2, operation);
        System.out.println(arabicToRoman(summ));
    }
} else {
    System.out.println("Число должно быть в пределах от 1 до 10 включительно");
}
    }
// методы ниже
    public static boolean checkForRoman(String numS) {
        boolean roman = false;
        char[] array1 = new char[]{'X', 'I', 'V', 'L', 'D', 'C'};
        int sum1 = 0;
        for (int i = 0; i < numS.length(); i++) {
            for (int j = 0; j < array1.length; j++) {
                if (numS.charAt(i) == array1[j]) {
                    sum1++;
                }
            }

        }
        if (sum1 == numS.length()) {
            roman = true;
        }
        return roman;
    }

    public static int valueRes(int num1, int num2, char operation) {
        int result = 0;

        switch (operation) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                result = num1 / num2;
                break;
        }

        return result;
    }


    public static int romanToArabic(String input) {
        String romanNumeral = input.toUpperCase();
        int result = 0;

        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

        int i = 0;

        while ((romanNumeral.length() > 0) && (i < romanNumerals.size())) {
            RomanNumeral symbol = romanNumerals.get(i);
            if (romanNumeral.startsWith(symbol.name())) {
                result += symbol.getValue();
                romanNumeral = romanNumeral.substring(symbol.name().length());
            } else {
                i++;
            }
        }

        if (romanNumeral.length() > 0) {
            throw new IllegalArgumentException(input + " cannot be converted to a Roman Numeral");
        }

        return result;
    }

    public static String arabicToRoman(int number) {
        if ((number <= 0) || (number > 4000)) {
            throw new IllegalArgumentException(number + " is not in range (0,4000]");
        }

        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

        int i = 0;
        StringBuilder sb = new StringBuilder();

        while ((number > 0) && (i < romanNumerals.size())) {
            RomanNumeral currentSymbol = romanNumerals.get(i);
            if (currentSymbol.getValue() <= number) {
                sb.append(currentSymbol.name());
                number -= currentSymbol.getValue();
            } else {
                i++;
            }
        }
        return sb.toString();
    }
}
