package ru.greatbit.plow.plugins.fake;

import ru.greatbit.plow.contract.Plugin;

/**
 * Created by azee on 02.03.16.
 */
@Plugin(contract = Fake.class)
public class FakeTwo implements Fake{

    @Override
    public int provide() {
        return 2;
    }
}
