package ru.otus.l62.b1.state;

/**
 * Created by tully.
 *
 * State.
 */
public interface State {
    void print(StateContext context, char letter);
}
