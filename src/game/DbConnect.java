package game;

import java.sql.*;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

public class DbConnect {

    private static Connection connection = null;
    private static Statement statement = null;

    public static void dbConnect() throws SQLException {
        String dbUrl = "jdbc:oracle:thin:@ 199.212.26.208:1521:SQLD";
        String username = "COMP228_F23_dah_20";
        String password = "password";
        connection = DriverManager.getConnection(dbUrl, username, password);
        System.out.println("Database connected");
        statement = connection.createStatement();
    }

    public static void dbDisconnect() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
            System.out.println("Database closed");
        }
    }

    public static void dropTable(String tableName) throws SQLException {
        dbConnect();

        String sql = "DROP TABLE " + tableName;
        statement.execute(sql);
        System.out.println("Table " + tableName + " is dropped!");

        if (statement != null) {
            statement.close();
        }
        dbDisconnect();
    }


    public static void createGameTable() throws SQLException {
        dbConnect();

        String sql = "CREATE TABLE  Game (game_id INTEGER PRIMARY KEY";
        sql += ",game_title VARCHAR(100))";
        System.out.println(sql);
        statement.execute(sql);
        System.out.println("Game Table  created");

        if (statement != null) {
            statement.close();
        }
        dbDisconnect();
    }

    public static void insertGameData(Game game) throws SQLException {

        dbConnect();

        String sql = "INSERT INTO Game  VALUES(" + game.getGame_id();
        sql += ",'" + game.getGame_title() + "')";
        System.out.println("inserting into player table" + sql);

        statement.executeUpdate(sql);
        System.out.println("One row inserted");

        if (statement != null) {
            statement.close();
        }
        dbDisconnect();

    }

    public static void createPlayerTable() throws SQLException {
        dbConnect();

        String sql = "CREATE TABLE  Player  (player_id INTEGER PRIMARY KEY";
        sql += ", first_name VARCHAR2(100)";
        sql += ",last_name VARCHAR2(100)";
        sql += ",address VARCHAR(100)";
        sql += ",postal_code VARCHAR(100)";
        sql += ",province VARCHAR(100)";
        sql += ",phone_number VARCHAR(100))";
        System.out.println(sql);
        statement.execute(sql);
        System.out.println("Player Table created");

        if (statement != null) {
            statement.close();
        }
        dbDisconnect();
    }

    public static void insertPlayerData(Player player) throws SQLException {

        dbConnect();

        String sql = "INSERT INTO Player  VALUES(" + player.getPlayer_id();
        sql += ",'" + player.getFirst_name() + "'";
        sql += ",'" + player.getLast_name() + "'";
        sql += ",'" + player.getAddress() + "'";
        sql += ",'" + player.getPostal_code() + "'";
        sql += ",'" + player.getProvince() + "'";
        sql += ",'" + player.getPhone_number() + "')";
        System.out.println("inserting into player table" + sql);

        statement.executeUpdate(sql);
        System.out.println("One row inserted!");

        if (statement != null) {
            statement.close();
        }
        dbDisconnect();

    }

    public static void updatePlayerData(Player player) throws SQLException {
        String dbUrl = "jdbc:oracle:thin:@ 199.212.26.208:1521:SQLD";
        String username = "COMP228_m22_sy_13";
        String password = "password";

        Connection con = DriverManager.getConnection(dbUrl, username, password);
        System.out.println("Database is connected!");
        PreparedStatement stmt = con.prepareStatement("UPDATE Player SET first_name = ?, last_name =?, address = ?, postal_code=?, province = ?, Phone_number=? WHERE player_id = ?");

        stmt.setString(1, player.getFirst_name());
        stmt.setString(2, player.getLast_name());
        stmt.setString(3, player.getAddress());
        stmt.setString(4, player.getPostal_code());
        stmt.setString(5, player.getProvince());
        stmt.setString(6, player.getPhone_number());
        stmt.setInt(7, player.getPlayer_id());

        stmt.executeUpdate();

        System.out.println("Row updated");
        System.out.println("Database is closed!");
        stmt.close();
        con.close();

    }

    public static void createPlayerAndGameTable() throws SQLException {
        dbConnect();

        String sql = "CREATE TABLE  PlayerAndGame  (player_game_id INTEGER PRIMARY KEY";
        sql += ", game_id INTEGER";
        sql += ",player_id INTEGER";
        sql += ",player_date VARCHAR(100)";
        sql += ",score INTEGER)";

        System.out.println(sql);
        statement.execute(sql);
        System.out.println("Table PlayerAndGame is created");

        if (statement != null) {
            statement.close();
        }
        dbDisconnect();
    }

    public static void insertPlayerAndGameData(PlayerAndGame pag) throws SQLException {

        dbConnect();

        String sql = "INSERT INTO PlayerAndGame  VALUES(" + pag.getPlayer_game_id();
        sql += "," + pag.getGame_id();
        sql += "," + pag.getPlayer_id();
        sql += ",'" + pag.getPlayer_date() + "'";
        sql += "," + pag.getScore() + ")";

        System.out.println("inserting into Player and Game database" + sql);

        statement.executeUpdate(sql);
        System.out.println("One row is inserted");

        if (statement != null) {
            statement.close();
        }
        dbDisconnect();

    }

    public static ResultSet query(String tableName, String sql) throws SQLException {
        CachedRowSet crs = RowSetProvider.newFactory().createCachedRowSet();

        dbConnect();
        ResultSet resultSet = null;
        resultSet = statement.executeQuery(sql + " " + tableName);
        crs.populate(resultSet);

        if (statement != null) {
            statement.close();
        }
        dbDisconnect();

        return crs;
    }

    public static void displayRow(String tableName, Integer s_id) throws SQLException {
        dbConnect();
        String sql = "Select * FROM " + tableName + " WHERE s_id =" + s_id;
        statement.executeUpdate(sql);
        System.out.println("Data  displayed");

        if (statement != null) {
            statement.close();
        }
        dbDisconnect();

    }

    public static void delete(String tableName, Integer s_id) throws SQLException {
        dbConnect();
        String sql = "DELETE FROM " + tableName + " WHERE s_id =" + s_id;
        statement.executeUpdate(sql);
        System.out.println("Data  deleted!");

        if (statement != null) {
            statement.close();
        }
        dbDisconnect();

    }

    public static void main(String[] arg) throws SQLException {

        DbConnect.dbConnect();
        DbConnect.dbDisconnect();
        try {
            DbConnect.dropTable("Game");
        } catch (SQLException e) {
        }
        try {
            DbConnect.dropTable("Player");
        } catch (SQLException e) {
        }
        try {
            DbConnect.dropTable("PlayerAndGame");
        } catch (SQLException e) {
        }
        DbConnect.createGameTable();
        DbConnect.createPlayerTable();
        DbConnect.createPlayerAndGameTable();

    }

}
