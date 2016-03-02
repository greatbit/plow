package ru.greatbit.plow.plugins.fake;

import ru.greatbit.plow.contract.Plugin;

/**
 * Created by azee on 02.03.16.
 */
@Plugin(name = "FirstFake", contract = Fake.class)
public class FakeOne implements Fake{

    @Override
    public int provide() {
        return 1;
    }
}
