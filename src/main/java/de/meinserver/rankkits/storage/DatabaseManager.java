package de.meinserver.rankkits.storage;

import de.meinserver.rankkits.RankKitsPlugin;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

public class DatabaseManager {

    private static Connection connection;

    public static void connect() {

        try {

            File file = new File(
                    RankKitsPlugin.getInstance().getDataFolder(),
                    "kits.db"
            );

            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            connection = DriverManager.getConnection(
                    "jdbc:sqlite:" + file.getAbsolutePath()
            );

            PreparedStatement statement =
                    connection.prepareStatement(
                            """
                            CREATE TABLE IF NOT EXISTS kit_cooldowns(
                            uuid TEXT,
                            kit TEXT,
                            last_claim BIGINT,
                            PRIMARY KEY(uuid, kit)
                            )
                            """
                    );

            statement.executeUpdate();
            statement.close();

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static long getRemainingCooldown(
            UUID uuid,
            String kit
    ) {

        try {

            PreparedStatement statement =
                    connection.prepareStatement(
                            """
                            SELECT last_claim
                            FROM kit_cooldowns
                            WHERE uuid = ?
                            AND kit = ?
                            """
                    );

            statement.setString(1, uuid.toString());
            statement.setString(2, kit);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {

                long lastClaim = rs.getLong("last_claim");

                long now =
                        System.currentTimeMillis() / 1000L;

                long cooldown = 3600L;

                long remaining =
                        cooldown - (now - lastClaim);

                rs.close();
                statement.close();

                return Math.max(0, remaining);
            }

            rs.close();
            statement.close();

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return 0;
    }

    public static void updateClaim(
            UUID uuid,
            String kit
    ) {

        try {

            PreparedStatement statement =
                    connection.prepareStatement(
                            """
                            INSERT OR REPLACE
                            INTO kit_cooldowns
                            (uuid, kit, last_claim)
                            VALUES (?, ?, ?)
                            """
                    );

            statement.setString(1, uuid.toString());
            statement.setString(2, kit);
            statement.setLong(
                    3,
                    System.currentTimeMillis() / 1000L
            );

            statement.executeUpdate();
            statement.close();

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
