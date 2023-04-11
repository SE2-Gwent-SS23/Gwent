package at.moritzmusel.gwent.networking;

/**
 * Enums for available commands to use.
 * E.g. Environment.GET_CURRENT_GAMESTATE.getCommand();
 */
enum Game_Commands {
    GET_CURRENT_GAMESTATE("get gamestate");

    private String command;

    Game_Commands(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
}
