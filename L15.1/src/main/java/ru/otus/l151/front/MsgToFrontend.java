package ru.otus.l151.front;

import ru.otus.l151.messageSystem.Address;
import ru.otus.l151.app.FrontendService;
import ru.otus.l151.messageSystem.Addressee;
import ru.otus.l151.messageSystem.Message;

/**
 * Created by tully.
 */
public abstract class MsgToFrontend extends Message {
    public MsgToFrontend(Address from, Address to) {
        super(from, to);
    }

    @Override
    public void exec(Addressee addressee) {
        if (addressee instanceof FrontendService) {
            exec((FrontendService) addressee);
        }
    }

    public abstract void exec(FrontendService frontendService);
}