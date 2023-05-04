package at.moritzmusel.gwent.network.data;

public class ObjectMessage {
    private Game_Commands command;
    private Object message;
    private String playerInfo;

    public ObjectMessage(Game_Commands command, Object message, String playerInfo) {
        this.command = command;
        this.message = message;
        this.playerInfo = playerInfo;
    }

    public Game_Commands getCommand() {
        return command;
    }

    public void setCommand(Game_Commands command) {
        this.command = command;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public String getPlayerInfo() {
        return playerInfo;
    }

    public void setPlayerInfo(String playerInfo) {
        this.playerInfo = playerInfo;
    }
}
