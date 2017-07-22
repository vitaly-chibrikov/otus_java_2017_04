package ru.otus.l151.db;

import ru.otus.l151.app.DBService;
import ru.otus.l151.front.MsgGetUserIdAnswer;
import ru.otus.l151.messageSystem.Address;
import ru.otus.l151.messageSystem.MessageSystem;

/**
 * Created by tully.
 */
public class MsgGetUserId extends MsgToDB {
    private final MessageSystem messageSystem;
    private final String login;

    public MsgGetUserId(MessageSystem messageSystem, Address from, Address to, String login) {
        super(from, to);
        this.login = login;
        this.messageSystem = messageSystem;
    }

    @Override
    public void exec(DBService dbService) {
        int id = dbService.getUserId(login);
        messageSystem.sendMessage(new MsgGetUserIdAnswer(getTo(), getFrom(), login, id));
    }
}
