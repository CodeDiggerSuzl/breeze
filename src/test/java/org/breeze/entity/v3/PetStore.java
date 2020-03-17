package org.breeze.entity.v3;

/**
 * Achieve constructor injection.
 *
 * @author Suz1
 * @date 2020/3/17 10:40 下午
 */
public class PetStore {
    private Account account;
    private Item item;
    private int year;

    public PetStore(Account account, Item item, int year) {
        this.account = account;
        this.item = item;
        this.year = year;
    }

    public PetStore(Account account, Item item) {
        this.account = account;
        this.item = item;
        this.year = -1;
    }

    public Account getAccount() {
        return account;
    }

    public Item getItem() {
        return item;
    }

    public int getYear() {
        return year;
    }
}
