package org.breeze.entity.v4annotation;


import beans.factory.annotation.Autowired;
import org.breeze.entity.v4annotation.entity.AccountV4;
import org.breeze.entity.v4annotation.entity.ItemV4;
import stereotype.Component;

/**
 * @author Suz1
 * @date 2020/3/23 10:52 下午
 */

@Component(value = "petStore")
public class PetStore {
    @Autowired
    private AccountV4 accountV4;
    @Autowired
    private ItemV4 itemV4;

    public AccountV4 getAccountV4() {
        return accountV4;
    }

    public ItemV4 getItemV4() {
        return itemV4;
    }
}
