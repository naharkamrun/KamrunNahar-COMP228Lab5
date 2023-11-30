package game;


public class PlayerAndGame {
    private int player_game_id;
    private int game_id;
    private int player_id;
    private String player_date;
    private int score;

    public PlayerAndGame(int player_game_id, int game_id, int player_id, String player_date, int score) {
        this.player_game_id = player_game_id;
        this.game_id = game_id;
        this.player_id = player_id;
        this.player_date = player_date;
        this.score = score;
    }

    public int getPlayer_game_id() {
        return player_game_id;
    }

    public int getGame_id() {
        return game_id;
    }

    public int getPlayer_id() {
        return player_id;
    }

    public String getPlayer_date() {
        return player_date;
    }

    public int getScore() {
        return score;
    }

    public void setPlayer_game_id(int player_game_id) {
        this.player_game_id = player_game_id;
    }

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }

    public void setPlayer_id(int player_id) {
        this.player_id = player_id;
    }

    public void setPlayer_date(String player_date) {
        this.player_date = player_date;
    }

    public void setScore(int score) {
        this.score = score;
    }


}
