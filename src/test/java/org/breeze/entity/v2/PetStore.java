package org.breeze.entity.v2;

public class PetStore {
    private Account account;
    private Item item;
    private String boss;

    // can not have constructor with args.
    // public PetStoreService(Account account, Item item) {
    //    this.account = account;
    //    this.item = item;
    // }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getBoss() {
        return boss;
    }

    public void setBoss(String boss) {
        this.boss = boss;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
