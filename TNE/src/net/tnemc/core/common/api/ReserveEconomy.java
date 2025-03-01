package net.tnemc.core.common.api;

import net.tnemc.core.TNE;
import net.tnemc.core.common.currency.TNECurrency;
import net.tnemc.core.common.currency.TNETier;
import net.tnemc.core.common.currency.formatter.CurrencyFormatter;
import net.tnemc.core.economy.Account;
import net.tnemc.core.economy.ExtendedEconomyAPI;
import net.tnemc.core.economy.currency.Currency;
import net.tnemc.core.economy.currency.Tier;
import net.tnemc.core.economy.transaction.Transaction;
import net.tnemc.core.economy.transaction.result.TransactionResult;
import net.tnemc.core.economy.transaction.type.TransactionType;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * The New Economy Minecraft Server Plugin
 *
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/ or send a letter to
 * Creative Commons, PO Box 1866, Mountain View, CA 94042, USA.
 * Created by Daniel on 8/17/2017.
 */
public class ReserveEconomy implements ExtendedEconomyAPI {

  private TNE plugin;

  public ReserveEconomy(TNE plugin) {
    this.plugin = plugin;
  }

  public String name() {
    return "TheNewEconomy";
  }

  public String version() {
    return "0.1.3.0";
  }

  public boolean enabled() {
    return true;
  }

  public String currencyDefaultPlural() {
    return TNE.manager().currencyManager().get(TNE.instance().defaultWorld).name();
  }

  public String currencyDefaultSingular() {
    return TNE.manager().currencyManager().get(TNE.instance().defaultWorld).name();
  }

  public String currencyDefaultPlural(String world) {
    return TNE.manager().currencyManager().get(TNE.instance().defaultWorld).name();
  }

  public String currencyDefaultSingular(String world) {
    return TNE.manager().currencyManager().get(TNE.instance().defaultWorld).name();
  }

  public boolean hasCurrency(String name) {
    return TNE.instance().api().hasCurrency(name);
  }

  public boolean hasCurrency(String name, String world) {
    return TNE.instance().api().hasCurrency(name, world);
  }

  public CompletableFuture<Boolean> asyncHasCurrency(String s) {
    return null;
  }

  public CompletableFuture<Boolean> asyncHasCurrency(String s, String s1) {
    return null;
  }

  public Currency getDefault() {
    return TNE.instance().api().getDefault();
  }

  public Currency getDefault(String world) {
    return TNE.instance().api().getDefault(world);
  }

  public Set<Currency> getCurrencies() {
    return new HashSet<>(TNE.instance().getWorldManager(TNE.instance().defaultWorld).getCurrencies());
  }

  public Set<Currency> getCurrencies(String world) {
    return new HashSet<>(TNE.instance().getWorldManager(world).getCurrencies());
  }

  public Currency getCurrency(String currency) {
    for(TNECurrency cur : TNE.manager().currencyManager().getCurrencies()) {
      if(cur.name().equalsIgnoreCase(currency)) return cur;
    }
    return null;
  }

  public Currency getCurrency(String currency, String world) {
    return TNE.manager().currencyManager().get(world, currency);
  }

  public boolean hasTier(String name, Currency currency) {
    return TNE.manager().currencyManager().get(TNE.instance().defaultWorld, currency.name()).hasTier(name);
  }

  public boolean hasTier(String name, Currency currency, String world) {
    return TNE.manager().currencyManager().get(world, currency.name()).hasTier(name);
  }

  public Set<Tier> getTiers(Currency currency) {
    return TNE.manager().currencyManager().get(TNE.instance().defaultWorld, currency.name()).getTiers();
  }

  public boolean hasAccount(String identifier) {
    return TNE.instance().api().hasAccount(identifier);
  }

  public boolean hasAccount(UUID identifier) {
    return TNE.instance().api().hasAccount(identifier);
  }

  public CompletableFuture<Boolean> asyncHasAccount(String s) {
    return null;
  }

  public CompletableFuture<Boolean> asyncHasAccount(UUID uuid) {
    return null;
  }

  public Account getAccount(String identifier) {
    return TNE.instance().api().getAccount(identifier);
  }

  public Account getAccount(UUID identifier) {
    return TNE.instance().api().getAccount(identifier);
  }

  public boolean createAccount(String identifier) {
    return TNE.instance().api().createAccount(identifier);
  }

  public boolean createAccount(UUID identifier) {
    return TNE.instance().api().createAccount(identifier);
  }

  public CompletableFuture<Boolean> asyncCreateAccount(String s) {
    return null;
  }

  public CompletableFuture<Boolean> asyncCreateAccount(UUID uuid) {
    return null;
  }

  public boolean deleteAccount(String identifier) {
    return TNE.manager().deleteAccount(IDFinder.getID(identifier));
  }

  public boolean deleteAccount(UUID identifier) {
    return TNE.manager().deleteAccount(identifier);
  }

  public CompletableFuture<Boolean> asyncDeleteAccount(String s) {
    return null;
  }

  public CompletableFuture<Boolean> asyncDeleteAccount(UUID uuid) {
    return null;
  }

  public CompletableFuture<Boolean> asyncCanWithdraw(String s, String s1) {
    return null;
  }

  public CompletableFuture<Boolean> asyncCanWithdraw(String s, UUID uuid) {
    return null;
  }

  public CompletableFuture<Boolean> asyncCanWithdraw(UUID uuid, String s) {
    return null;
  }

  public CompletableFuture<Boolean> asyncCanWithdraw(UUID uuid, UUID uuid1) {
    return null;
  }

  public CompletableFuture<Boolean> asyncCanDeposit(String s, String s1) {
    return null;
  }

  public CompletableFuture<Boolean> asyncCanDeposit(String s, UUID uuid) {
    return null;
  }

  public CompletableFuture<Boolean> asyncCanDeposit(UUID uuid, String s) {
    return null;
  }

  public CompletableFuture<Boolean> asyncCanDeposit(UUID uuid, UUID uuid1) {
    return null;
  }

  public CompletableFuture<BigDecimal> asyncGetHoldings(String s) {
    return null;
  }

  public CompletableFuture<BigDecimal> asyncGetHoldings(UUID uuid) {
    return null;
  }

  public CompletableFuture<BigDecimal> asyncGetHoldings(String s, String s1) {
    return null;
  }

  public CompletableFuture<BigDecimal> asyncGetHoldings(UUID uuid, String s) {
    return null;
  }

  public CompletableFuture<BigDecimal> asyncGetHoldings(String s, String s1, String s2) {
    return null;
  }

  public CompletableFuture<BigDecimal> asyncGetHoldings(UUID uuid, String s, String s1) {
    return null;
  }

  public CompletableFuture<Boolean> asyncHasHoldings(String s, BigDecimal bigDecimal) {
    return null;
  }

  public CompletableFuture<Boolean> asyncHasHoldings(UUID uuid, BigDecimal bigDecimal) {
    return null;
  }

  public CompletableFuture<Boolean> asyncHasHoldings(String s, BigDecimal bigDecimal, String s1) {
    return null;
  }

  public CompletableFuture<Boolean> asyncHasHoldings(UUID uuid, BigDecimal bigDecimal, String s) {
    return null;
  }

  public CompletableFuture<Boolean> asyncHasHoldings(String s, BigDecimal bigDecimal, String s1, String s2) {
    return null;
  }

  public CompletableFuture<Boolean> asyncHasHoldings(UUID uuid, BigDecimal bigDecimal, String s, String s1) {
    return null;
  }

  public CompletableFuture<Boolean> asyncSetHoldings(String s, BigDecimal bigDecimal) {
    return null;
  }

  public CompletableFuture<Boolean> asyncSetHoldings(UUID uuid, BigDecimal bigDecimal) {
    return null;
  }

  public CompletableFuture<Boolean> asyncSetHoldings(String s, BigDecimal bigDecimal, String s1) {
    return null;
  }

  public CompletableFuture<Boolean> asyncSetHoldings(UUID uuid, BigDecimal bigDecimal, String s) {
    return null;
  }

  public CompletableFuture<Boolean> asyncSetHoldings(String s, BigDecimal bigDecimal, String s1, String s2) {
    return null;
  }

  public CompletableFuture<Boolean> asyncSetHoldings(UUID uuid, BigDecimal bigDecimal, String s, String s1) {
    return null;
  }

  public CompletableFuture<Boolean> asyncAddHoldings(String s, BigDecimal bigDecimal) {
    return null;
  }

  public CompletableFuture<Boolean> asyncAddHoldings(UUID uuid, BigDecimal bigDecimal) {
    return null;
  }

  public CompletableFuture<Boolean> asyncAddHoldings(String s, BigDecimal bigDecimal, String s1) {
    return null;
  }

  public CompletableFuture<Boolean> asyncAddHoldings(UUID uuid, BigDecimal bigDecimal, String s) {
    return null;
  }

  public CompletableFuture<Boolean> asyncAddHoldings(String s, BigDecimal bigDecimal, String s1, String s2) {
    return null;
  }

  public CompletableFuture<Boolean> asyncAddHoldings(UUID uuid, BigDecimal bigDecimal, String s, String s1) {
    return null;
  }

  public CompletableFuture<Boolean> asyncCanAddHoldings(String s, BigDecimal bigDecimal) {
    return null;
  }

  public CompletableFuture<Boolean> asyncCanAddHoldings(UUID uuid, BigDecimal bigDecimal) {
    return null;
  }

  public CompletableFuture<Boolean> asyncCanAddHoldings(String s, BigDecimal bigDecimal, String s1) {
    return null;
  }

  public CompletableFuture<Boolean> asyncCanAddHoldings(UUID uuid, BigDecimal bigDecimal, String s) {
    return null;
  }

  public CompletableFuture<Boolean> asyncCanAddHoldings(String s, BigDecimal bigDecimal, String s1, String s2) {
    return null;
  }

  public CompletableFuture<Boolean> asyncCanAddHoldings(UUID uuid, BigDecimal bigDecimal, String s, String s1) {
    return null;
  }

  public CompletableFuture<Boolean> asyncRemoveHoldings(String s, BigDecimal bigDecimal) {
    return null;
  }

  public CompletableFuture<Boolean> asyncRemoveHoldings(UUID uuid, BigDecimal bigDecimal) {
    return null;
  }

  public CompletableFuture<Boolean> asyncRemoveHoldings(String s, BigDecimal bigDecimal, String s1) {
    return null;
  }

  public CompletableFuture<Boolean> asyncRemoveHoldings(UUID uuid, BigDecimal bigDecimal, String s) {
    return null;
  }

  public CompletableFuture<Boolean> asyncRemoveHoldings(String s, BigDecimal bigDecimal, String s1, String s2) {
    return null;
  }

  public CompletableFuture<Boolean> asyncRemoveHoldings(UUID uuid, BigDecimal bigDecimal, String s, String s1) {
    return null;
  }

  public CompletableFuture<Boolean> asyncCanRemoveHoldings(String s, BigDecimal bigDecimal) {
    return null;
  }

  public CompletableFuture<Boolean> asyncCanRemoveHoldings(UUID uuid, BigDecimal bigDecimal) {
    return null;
  }

  public CompletableFuture<Boolean> asyncCanRemoveHoldings(String s, BigDecimal bigDecimal, String s1) {
    return null;
  }

  public CompletableFuture<Boolean> asyncCanRemoveHoldings(UUID uuid, BigDecimal bigDecimal, String s) {
    return null;
  }

  public CompletableFuture<Boolean> asyncCanRemoveHoldings(String s, BigDecimal bigDecimal, String s1, String s2) {
    return null;
  }

  public CompletableFuture<Boolean> asyncCanRemoveHoldings(UUID uuid, BigDecimal bigDecimal, String s, String s1) {
    return null;
  }

  public CompletableFuture<Boolean> asyncTransferHoldings(String s, String s1, BigDecimal bigDecimal) {
    return null;
  }

  public CompletableFuture<Boolean> asyncTransferHoldings(String s, String s1, BigDecimal bigDecimal, String s2) {
    return null;
  }

  public CompletableFuture<Boolean> asyncTransferHoldings(String s, String s1, BigDecimal bigDecimal, String s2, String s3) {
    return null;
  }

  public CompletableFuture<Boolean> asyncTransferHoldings(UUID uuid, UUID uuid1, BigDecimal bigDecimal) {
    return null;
  }

  public CompletableFuture<Boolean> asyncTransferHoldings(UUID uuid, UUID uuid1, BigDecimal bigDecimal, String s) {
    return null;
  }

  public CompletableFuture<Boolean> asyncTransferHoldings(UUID uuid, UUID uuid1, BigDecimal bigDecimal, String s, String s1) {
    return null;
  }

  public CompletableFuture<Boolean> asyncCanTransferHoldings(String s, String s1, BigDecimal bigDecimal) {
    return null;
  }

  public CompletableFuture<Boolean> asyncCanTransferHoldings(String s, String s1, BigDecimal bigDecimal, String s2) {
    return null;
  }

  public CompletableFuture<Boolean> asyncCanTransferHoldings(String s, String s1, BigDecimal bigDecimal, String s2, String s3) {
    return null;
  }

  public CompletableFuture<Boolean> asyncCanTransferHoldings(UUID uuid, UUID uuid1, BigDecimal bigDecimal) {
    return null;
  }

  public CompletableFuture<Boolean> asyncCanTransferHoldings(UUID uuid, UUID uuid1, BigDecimal bigDecimal, String s) {
    return null;
  }

  public CompletableFuture<Boolean> asyncCanTransferHoldings(UUID uuid, UUID uuid1, BigDecimal bigDecimal, String s, String s1) {
    return null;
  }

  public List<String> getBanks() {
    return null;
  }

  public List<String> getBanks(String s) {
    return null;
  }

  public CompletableFuture<List<String>> asyncGetBanks() {
    return null;
  }

  public CompletableFuture<List<String>> asyncGetBanks(String s) {
    return null;
  }

  public List<String> acceptedBankCurrencies() {
    return null;
  }

  public List<String> acceptedBankCurrencies(String s) {
    return null;
  }

  public List<String> acceptedBankCurrencies(String s, String s1) {
    return null;
  }

  public CompletableFuture<List<String>> asyncAcceptedBankCurrencies() {
    return null;
  }

  public CompletableFuture<List<String>> asyncAcceptedBankCurrencies(String s) {
    return null;
  }

  public CompletableFuture<List<String>> asyncAcceptedBankCurrencies(String s, String s1) {
    return null;
  }

  public List<UUID> availableBankAccounts(UUID uuid) {
    return null;
  }

  public List<UUID> availableBankAccounts(UUID uuid, String s) {
    return null;
  }

  public List<UUID> availableBankAccounts(UUID uuid, String s, String s1) {
    return null;
  }

  public CompletableFuture<List<UUID>> asyncAvailableBankAccounts(UUID uuid) {
    return null;
  }

  public CompletableFuture<List<UUID>> asyncAvailableBankAccounts(UUID uuid, String s) {
    return null;
  }

  public CompletableFuture<List<UUID>> asyncAvailableBankAccounts(UUID uuid, String s, String s1) {
    return null;
  }

  public boolean isBankOwner(UUID uuid, String s) {
    return false;
  }

  public boolean isBankOwner(UUID uuid, String s, String s1) {
    return false;
  }

  public CompletableFuture<Boolean> asyncIsBankOwner(UUID uuid, String s) {
    return null;
  }

  public CompletableFuture<Boolean> asyncIsBankOwner(UUID uuid, String s, String s1) {
    return null;
  }

  public Optional<UUID> createBankAccount(UUID uuid) {
    return Optional.empty();
  }

  public Optional<UUID> createBankAccount(UUID uuid, String s) {
    return Optional.empty();
  }

  public Optional<UUID> createBankAccount(UUID uuid, String s, String s1) {
    return Optional.empty();
  }

  public CompletableFuture<Optional<UUID>> asyncCreateBankAccount(UUID uuid) {
    return null;
  }

  public CompletableFuture<Optional<UUID>> asyncCreateBankAccount(UUID uuid, String s) {
    return null;
  }

  public CompletableFuture<Optional<UUID>> asyncCreateBankAccount(UUID uuid, String s, String s1) {
    return null;
  }

  public boolean hasBankAccount(UUID uuid) {
    return false;
  }

  public boolean hasBankAccount(UUID uuid, String s) {
    return false;
  }

  public boolean hasBankAccount(UUID uuid, String s, String s1) {
    return false;
  }

  public CompletableFuture<Boolean> asyncHasBankAccount(UUID uuid) {
    return null;
  }

  public CompletableFuture<Boolean> asyncHasBankAccount(UUID uuid, String s) {
    return null;
  }

  public CompletableFuture<Boolean> asyncHasBankAccount(UUID uuid, String s, String s1) {
    return null;
  }

  public boolean isBankAccountOwner(UUID uuid, UUID uuid1) {
    return false;
  }

  public CompletableFuture<Boolean> asyncIsBankAccountOwner(UUID uuid, UUID uuid1) {
    return null;
  }

  public boolean isBankAccountMember(UUID uuid, UUID uuid1) {
    return false;
  }

  public CompletableFuture<Boolean> asyncIsBankAccountMember(UUID uuid, UUID uuid1) {
    return null;
  }

  public BigDecimal getBankHoldings(UUID uuid) {
    return null;
  }

  public BigDecimal getBankHoldings(UUID uuid, String s) {
    return null;
  }

  public BigDecimal getBankHoldings(UUID uuid, String s, String s1) {
    return null;
  }

  public BigDecimal getBankHoldings(UUID uuid, String s, String s1, String s2) {
    return null;
  }

  public CompletableFuture<BigDecimal> asyncGetBankHoldings(UUID uuid) {
    return null;
  }

  public CompletableFuture<BigDecimal> asyncGetBankHoldings(UUID uuid, String s) {
    return null;
  }

  public CompletableFuture<BigDecimal> asyncGetBankHoldings(UUID uuid, String s, String s1) {
    return null;
  }

  public CompletableFuture<BigDecimal> asyncGetBankHoldings(UUID uuid, String s, String s1, String s2) {
    return null;
  }

  public boolean bankAddHoldings(UUID uuid, UUID uuid1, BigDecimal bigDecimal) {
    return false;
  }

  public boolean bankAddHoldings(UUID uuid, UUID uuid1, BigDecimal bigDecimal, String s) {
    return false;
  }

  public boolean bankAddHoldings(UUID uuid, UUID uuid1, BigDecimal bigDecimal, String s, String s1) {
    return false;
  }

  public boolean bankAddHoldings(UUID uuid, UUID uuid1, BigDecimal bigDecimal, String s, String s1, String s2) {
    return false;
  }

  public CompletableFuture<Boolean> asyncBankAddHoldings(UUID uuid, UUID uuid1, BigDecimal bigDecimal) {
    return null;
  }

  public CompletableFuture<Boolean> asyncBankAddHoldings(UUID uuid, UUID uuid1, BigDecimal bigDecimal, String s) {
    return null;
  }

  public CompletableFuture<Boolean> asyncBankAddHoldings(UUID uuid, UUID uuid1, BigDecimal bigDecimal, String s, String s1) {
    return null;
  }

  public CompletableFuture<Boolean> asyncBankAddHoldings(UUID uuid, UUID uuid1, BigDecimal bigDecimal, String s, String s1, String s2) {
    return null;
  }

  public boolean bankRemoveHoldings(UUID uuid, UUID uuid1, BigDecimal bigDecimal) {
    return false;
  }

  public boolean bankRemoveHoldings(UUID uuid, UUID uuid1, BigDecimal bigDecimal, String s) {
    return false;
  }

  public boolean bankRemoveHoldings(UUID uuid, UUID uuid1, BigDecimal bigDecimal, String s, String s1) {
    return false;
  }

  public boolean bankRemoveHoldings(UUID uuid, UUID uuid1, BigDecimal bigDecimal, String s, String s1, String s2) {
    return false;
  }

  public CompletableFuture<Boolean> asyncBankRemoveHoldings(UUID uuid, UUID uuid1, BigDecimal bigDecimal) {
    return null;
  }

  public CompletableFuture<Boolean> asyncBankRemoveHoldings(UUID uuid, UUID uuid1, BigDecimal bigDecimal, String s) {
    return null;
  }

  public CompletableFuture<Boolean> asyncBankRemoveHoldings(UUID uuid, UUID uuid1, BigDecimal bigDecimal, String s, String s1) {
    return null;
  }

  public CompletableFuture<Boolean> asyncBankRemoveHoldings(UUID uuid, UUID uuid1, BigDecimal bigDecimal, String s, String s1, String s2) {
    return null;
  }

  public boolean bankSetHoldings(UUID uuid, BigDecimal bigDecimal) {
    return false;
  }

  public boolean bankSetHoldings(UUID uuid, BigDecimal bigDecimal, String s) {
    return false;
  }

  public boolean bankSetHoldings(UUID uuid, BigDecimal bigDecimal, String s, String s1) {
    return false;
  }

  public boolean bankSetHoldings(UUID uuid, BigDecimal bigDecimal, String s, String s1, String s2) {
    return false;
  }

  public CompletableFuture<Boolean> asyncBankSetHoldings(UUID uuid, BigDecimal bigDecimal) {
    return null;
  }

  public CompletableFuture<Boolean> asyncBankSetHoldings(UUID uuid, BigDecimal bigDecimal, String s) {
    return null;
  }

  public CompletableFuture<Boolean> asyncBankSetHoldings(UUID uuid, BigDecimal bigDecimal, String s, String s1) {
    return null;
  }

  public CompletableFuture<Boolean> asyncBankSetHoldings(UUID uuid, BigDecimal bigDecimal, String s, String s1, String s2) {
    return null;
  }

  public Account createIfNotExists(String identifier) {
    if(!hasAccount(identifier)) createAccount(identifier);
    return getAccount(identifier);
  }

  public Account createIfNotExists(UUID uuid) {
    if(!hasAccount(uuid)) createAccount(uuid);
    return getAccount(uuid);
  }

  public String format(BigDecimal amount) {
    return TNE.instance().api().format(amount, TNE.instance().defaultWorld);
  }

  public String format(BigDecimal amount, String world) {
    return TNE.instance().api().format(amount, world);
  }

  public boolean purgeAccounts() {
    return false;
  }

  public boolean purgeAccountsUnder(BigDecimal bigDecimal) {
    return false;
  }

  public CompletableFuture<Boolean> asyncPurgeAccounts() {
    return null;
  }

  public CompletableFuture<Boolean> asyncPurgeAccountsUnder(BigDecimal bigDecimal) {
    return null;
  }

  public String format(BigDecimal amount, Currency currency) {
    return CurrencyFormatter.format(TNECurrency.fromReserve(currency), TNE.instance().defaultWorld, amount, "");
  }

  public String format(BigDecimal amount, Currency currency, String world) {
    return CurrencyFormatter.format(TNECurrency.fromReserve(currency), world, amount, "");
  }

  public boolean supportTransactions() {
    return true;
  }

  public TransactionResult performTransaction(Transaction transaction) {
    return null;
  }

  public boolean voidTransaction(UUID uuid) {
    return TNE.instance().api().voidTransaction(uuid);
  }

  public Set<TransactionType> getTransactionTypes() {
    return TNE.instance().api().getTransactionTypes();
  }

  public boolean registerTransactionType(TransactionType transactionType) {
    return TNE.instance().api().registerTransactionType(transactionType);
  }

  public boolean registerTransactionResult(TransactionResult transactionResult) {
    return TNE.instance().api().registerTransactionResult(transactionResult);
  }

  public boolean registerCurrency(Currency currency) {
    return TNE.instance().api().registerCurrency(TNECurrency.fromReserve(currency));
  }

  public boolean registerCurrency(Currency currency, String world) {
    return TNE.instance().api().registerCurrency(TNECurrency.fromReserve(currency), world);
  }

  public boolean registerTier(Tier tier, Currency currency) {
    return TNE.instance().api().registerTier(TNETier.fromReserve(tier), TNECurrency.fromReserve(currency));
  }

  public boolean registerTier(Tier tier, Currency currency, String world) {
    return TNE.instance().api().registerTier(TNETier.fromReserve(tier), TNECurrency.fromReserve(currency), world);
  }

  public Map<UUID, Transaction> getTransactions(String identifier) {
    return TNE.instance().api().getTransactions(identifier);
  }

  public Map<UUID, Transaction> getTransactions() {
    return TNE.instance().api().getTransactions();
  }

  public Optional<Transaction> getTransaction(UUID uuid) {
    return TNE.instance().api().getTransaction(uuid);
  }


}