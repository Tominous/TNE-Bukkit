package net.tnemc.signs;

import com.github.tnerevival.core.db.SQLDatabase;
import com.github.tnerevival.serializable.SerializableLocation;
import net.tnemc.core.TNE;
import net.tnemc.core.item.SerialItem;
import net.tnemc.signs.signs.TNESign;
import org.bukkit.Location;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The New Economy Minecraft Server Plugin
 * <p>
 * Created by Daniel on 6/3/2018.
 * <p>
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/ or send a letter to
 * Creative Commons, PO Box 1866, Mountain View, CA 94042, USA.
 * Created by creatorfromhell on 06/30/2017.
 */
public class SignsData {

  public static final String prefix = TNE.saveManager().getTNEManager().getPrefix();

  private static final String SIGNS_UPDATE_STEP = "UPDATE " + prefix + "_SIGNS SET sign_step = ? WHERE sign_location = ?";
  private static final String SIGNS_UPDATE_CHEST = "UPDATE " + prefix + "_SIGNS SET sign_chest = ? WHERE sign_location = ?";
  public static final String SIGNS_CHEST_CHECK = "SELECT sign_owner FROM " + SignsData.prefix + "_SIGNS WHERE sign_chest = ?";
  private static final String SIGNS_SAVE = "INSERT INTO " + prefix + "_SIGNS (sign_location, sign_attached, sign_owner, sign_type, sign_creator, sign_created, sign_step, sign_admin, sign_data) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?) " +
                                           "ON DUPLICATE KEY UPDATE sign_attached = ?, sign_owner = ?, sign_type = ?, sign_creator = ?, sign_created = ?, sign_step = ?, sign_admin = ?, sign_data = ?;";
  private static final String SIGNS_DELETE = "DELETE FROM " + prefix + "_SIGNS WHERE sign_location = ?";

  //Item Sign Queries
  public static final String ITEM_OFFER_UPDATE = "UPDATE " + SignsData.prefix + "_SIGNS_ITEMS SET item_offer = ?, item_amount = ?, item_selling = ? WHERE sign_location = ?";
  public static final String ITEM_OFFER_ADD = "INSERT INTO " + SignsData.prefix + "_SIGNS_ITEMS (item_offer, item_trade, item_amount, item_selling, sign_location) VALUES(?, ?, ?, ?, ?) " +
          "ON DUPLICATE KEY UPDATE item_offer = VALUES(item_offer), item_trade = VALUES(item_trade), item_amount = VALUES(item_amount), item_selling = VALUES(item_selling), sign_location = VALUES(sign_location)";
  public static final String ITEM_TRADE_UPDATE = "UPDATE " + SignsData.prefix + "_SIGNS_ITEMS SET item_currency = ?, item_cost = ?, item_trade = ? WHERE sign_location = ?";
  private static final String ITEM_SIGN_DELETE = "DELETE FROM " + SignsData.prefix + "_SIGNS_ITEMS WHERE sign_location = ?";

  public static final ExecutorService POOL = Executors.newCachedThreadPool();

  public static void saveSign(TNESign sign) throws SQLException {
    SignsModule.instance().getSignDataStore().setSignData(sign.getLocation(), sign);
    POOL.execute(() -> {
      try {
        SQLDatabase.executePreparedUpdate(SIGNS_SAVE, new Object[] {
                new SerializableLocation(sign.getLocation()).toString(),
                new SerializableLocation(sign.getAttached()).toString(),
                sign.getOwner().toString(),
                sign.getType(),
                sign.getCreator().toString(),
                sign.getCreationDate(),
                sign.getStep(),
                sign.isAdmin(),
                sign.saveExtraData(),
                new SerializableLocation(sign.getAttached()).toString(),
                sign.getOwner().toString(),
                sign.getType(),
                sign.getCreator().toString(),
                sign.getCreationDate(),
                sign.getStep(),
                sign.isAdmin(),
                sign.saveExtraData()
        });
      } catch (Exception e) {
        e.printStackTrace();
      }
    });
  }

  public static void updateStep(final Location location, final int step) throws SQLException {
    loadSign(location).setStep(step);
    POOL.execute(() -> {
      try {
        SQLDatabase.executePreparedUpdate(SIGNS_UPDATE_STEP, new Object[] {
                step,
                new SerializableLocation(location).toString()
        });
      } catch (Exception e) {
        e.printStackTrace();;
      }
    });
  }

  public static void updateChest(final Location location, final Location chest) throws SQLException {
    loadSign(location).setChest((Chest) chest.getBlock().getState());
    POOL.execute(() -> {
      try {
        SQLDatabase.executePreparedUpdate(SIGNS_UPDATE_CHEST, new Object[] {
                new SerializableLocation(chest).toString(),
                new SerializableLocation(location).toString()
        });
      } catch (Exception e) {
        e.printStackTrace();
      }
    });
  }

  public static TNESign loadSign(Location location) {
    return SignsModule.instance().getSignDataStore().getSign(location);
  }

  public static Map<Location, TNESign> loadSignsFromDatabase(List<Location> locations) {
    Map<Location, TNESign> signs = new HashMap<>();
    try(Connection connection = SQLDatabase.getDataSource().getConnection()) {
      StringBuilder builder = new StringBuilder()
              .append("SELECT signs.sign_location, signs.sign_chest, signs.sign_attached, signs.sign_owner, signs.sign_type, signs.sign_creator, ")
              .append("signs.sign_created, signs.sign_step, signs.sign_admin, signs.sign_data, signs_items.item_offer, signs_items.item_amount, ")
              .append("signs_items.item_trade, signs_items.item_currency, signs_items.item_cost, signs_items.item_selling ")
              .append("FROM ").append(prefix).append("_SIGNS signs LEFT JOIN ").append(prefix).append("_SIGNS_ITEMS signs_items ON signs.sign_location = signs_items.sign_location ")
              .append("WHERE");
      for(int i=0; i < locations.size(); i++) {
        builder.append(" ");
        if(i != 0) {
          builder.append("OR ");
        }

        builder.append("signs.sign_location = '").append(new SerializableLocation(locations.get(i)).toString()).append("'");
      }

      ResultSet resultSet = connection.prepareStatement(builder.toString()).executeQuery();
      while(resultSet.next()) {
        Location location = SerializableLocation.fromString(resultSet.getString("sign_location")).getLocation();
        TNESign sign = new TNESign(
                location,
                SerializableLocation.fromString(resultSet.getString("sign_attached")).getLocation(),
                resultSet.getString("sign_type"),
                UUID.fromString(resultSet.getString("sign_owner")),
                UUID.fromString(resultSet.getString("sign_creator")),
                resultSet.getLong("sign_created"),
                resultSet.getBoolean("sign_admin"),
                resultSet.getInt("sign_step")
        );
        sign.loadExtraData(resultSet.getString("sign_data"));

        String chestLocation = resultSet.getString("sign_chest");
        if(chestLocation != null && chestLocation.length() > 0) {
          BlockState state = SerializableLocation.fromString(chestLocation).getLocation().getBlock().getState();
          if(state instanceof Chest) {
            sign.setChest((Chest) state);
          }
        }

        String itemTrade = resultSet.getString("item_trade");
        if(itemTrade != null && itemTrade.length() > 0 && itemTrade.charAt(0) == '{') {
          SerialItem item = SerialItem.fromJSON((JSONObject) new JSONParser().parse(itemTrade));
          if(item != null) {
            sign.setTrade(item.getStack());
          }
        }

        String itemOffer = resultSet.getString("item_offer");
        if(itemOffer != null && itemOffer.length() > 0 && itemOffer.charAt(0) == '{') {
          SerialItem item = SerialItem.fromJSON((JSONObject) new JSONParser().parse(itemOffer));
          if(item != null) {
            sign.setOffer(item.getStack());
          }
        }

        sign.setCost(resultSet.getBigDecimal("item_cost"));
        sign.setCurrency(resultSet.getBoolean("item_currency"));
        sign.setSelling(resultSet.getBoolean("item_selling"));

        signs.put(location, sign);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return signs;
  }

  public static TNESign loadSignAttached(Location location) throws SQLException {
    return loadSign(location);
  }

  public static Collection<TNESign> loadSignsCreator(String creator, String type) throws SQLException {
    List<TNESign> applicable = new ArrayList<>();
    for(TNESign sign : SignsModule.instance().getSignDataStore().getSigns()) {
      if(sign != null &&
              sign.getCreator() != null &&
              sign.getType() != null &&
              sign.getCreator().toString().equals(creator) &&
              sign.getType().equals(type)) {
        applicable.add(sign);
      }
    }
    return applicable;
  }

  public static Collection<TNESign> loadSigns(String owner, String type) throws SQLException {
    List<TNESign> applicable = new ArrayList<>();
    for(TNESign sign : SignsModule.instance().getSignDataStore().getSigns()) {
      if(sign.getOwner().toString().equals(owner) &&
              sign.getType().equals(type)) {
        applicable.add(sign);
      }
    }
    return applicable;
  }

  public static Chest chest(Location location) throws SQLException {
    return SignsModule.instance().getSignDataStore().getSign(location).getChest();
  }

  public static void deleteSign(Location location) throws SQLException {
    SignsModule.instance().getSignDataStore().unloadSign(location);
    POOL.execute(() -> {
      try(Connection connection = SQLDatabase.getDataSource().getConnection()) {
        String serializedLocation = new SerializableLocation(location).toString();
        {
          PreparedStatement statement = connection.prepareStatement(SIGNS_DELETE);
          statement.setString(1, serializedLocation);
          statement.execute();
        }
        {
          PreparedStatement statement = connection.prepareStatement(ITEM_SIGN_DELETE);
          statement.setString(1, serializedLocation);
          statement.execute();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    });
  }
}