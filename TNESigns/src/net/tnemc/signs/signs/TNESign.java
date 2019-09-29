package net.tnemc.signs.signs;

import org.bukkit.Location;
import org.bukkit.block.Chest;
import org.bukkit.inventory.ItemStack;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * The New Economy Minecraft Server Plugin
 * <p>
 * Created by Daniel on 5/28/2018.
 * <p>
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/ or send a letter to
 * Creative Commons, PO Box 1866, Mountain View, CA 94042, USA.
 * Created by creatorfromhell on 06/30/2017.
 */
public class TNESign {

  private Location location;
  private Location attached;
  private Chest chest;
  private String type;
  private UUID owner;
  private UUID creator;
  private Long creationDate;
  private boolean admin;
  private int step;
  private ItemStack trade;
  private boolean currency;
  private BigDecimal cost;
  private boolean selling;
  private ItemStack offer;

  public TNESign(Location location, Location attached, String type, UUID owner, UUID creator, long creationDate, boolean admin, int step) {
    this.location = location;
    this.attached = attached;
    this.type = type;
    this.owner = owner;
    this.creator = creator;
    this.creationDate = creationDate;
    this.admin = admin;
    this.step = step;
  }

  public TNESign(Location location, Location attached, String type, UUID owner, UUID creator, long creationDate, boolean admin, int step, String data) {
    this.location = location;
    this.attached = attached;
    this.type = type;
    this.owner = owner;
    this.creator = creator;
    this.creationDate = creationDate;
    this.admin = admin;
    this.step = step;
    loadExtraData(data);
  }

  public ItemStack getOffer() {
    return offer;
  }

  public void setOffer(ItemStack offer) {
    this.offer = offer;
  }

  public boolean isSelling() {
    return selling;
  }

  public void setSelling(boolean selling) {
    this.selling = selling;
  }

  public boolean isCurrency() {
    return currency;
  }

  public void setCurrency(boolean currency) {
    this.currency = currency;
  }

  public BigDecimal getCost() {
    return cost;
  }

  public void setCost(BigDecimal cost) {
    this.cost = cost;
  }

  public ItemStack getTrade() {
    if(trade == null) {
      return trade;
    }
    return trade.clone();
  }

  public void setTrade(ItemStack trade) {
    this.trade = trade;
  }

  public void setChest(Chest chest) {
    this.chest = chest;
  }

  public Chest getChest() {
    return chest;
  }

  public Location getLocation() {
    return location;
  }

  public void setLocation(Location location) {
    this.location = location;
  }

  public Location getAttached() {
    return attached;
  }

  public void setAttached(Location attached) {
    this.attached = attached;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public UUID getOwner() {
    return owner;
  }

  public void setOwner(UUID owner) {
    this.owner = owner;
  }

  public UUID getCreator() {
    return creator;
  }

  public void setCreator(UUID creator) {
    this.creator = creator;
  }

  public Long getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(Long creationDate) {
    this.creationDate = creationDate;
  }

  public boolean isAdmin() {
    return admin;
  }

  public void setAdmin(boolean admin) {
    this.admin = admin;
  }

  public int getStep() {
    return step;
  }

  public void setStep(int step) {
    this.step = step;
  }

  public String saveExtraData() {
    return "";
  }

  public void loadExtraData(String data) {
  }

  @Override
  public String toString() {
    return "TNESign{" +
            "location=" + location +
            ", attached=" + attached +
            ", chest=" + chest +
            ", type='" + type + '\'' +
            ", owner=" + owner +
            ", creator=" + creator +
            ", creationDate=" + creationDate +
            ", admin=" + admin +
            ", step=" + step +
            ", trade=" + trade +
            '}';
  }

}