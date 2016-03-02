package ru.greatbit.plow.plugins.demo;

import ru.greatbit.plow.contract.Plugin;

/**
 * Created by azee on 02.03.16.
 */
@Plugin(contract = Demo.class)
public class DemoB implements Demo{

    @Override
    public String getLetter() {
        return "B";
    }
}
