package at.moritzmusel.gwent.backend.data;

/**
 * Enums for available commands to use.
 * E.g. Environment.GET_CURRENT_GAMESTATE.getCommand();
 */
enum Game_Commands {
    GET_CURRENT_GAMESTATE("get gamestate"),
    GET_PASSWORD("get password"),
    INIT_GAME("initialize game");

    private String command;

    Game_Commands(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
}
