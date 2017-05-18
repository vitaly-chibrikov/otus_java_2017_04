package ru.otus.l62.b1.state;

/**
 * Created by tully.
 *
 * Client in the State pattern.
 */
public class Main {
    public static void main(String[] args) {
        StateContext context = new StateContext(new UpperCaseState());

        String str = "SPacE hArbOUr l61 sTatE";
        for (Character character : str.toCharArray()) {
            context.print(character);
        }
    }
}
