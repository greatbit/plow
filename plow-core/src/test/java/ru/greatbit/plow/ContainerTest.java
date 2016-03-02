package ru.greatbit.plow;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.greatbit.plow.plugins.demo.Demo;
import ru.greatbit.plow.plugins.fake.Fake;

import java.util.Map;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by azee on 02.03.16.
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:test-context.xml")
public class ContainerTest {

    @Autowired
    PluginsContainer container;

    @Test
    public void ContainerTest(){
        assertThat(container.getPlugins().size(), is(2));
        assertThat(container.getPluginsTypes(), containsInAnyOrder(Fake.class.getSimpleName(), Demo.class.getSimpleName()));

        Map<String, Fake> fakes = container.getPlugins(Fake.class);
        assertThat(fakes.size(), is(2));
        assertThat(fakes.keySet(), containsInAnyOrder("FirstFake", "faketwo"));
        assertThat(fakes.get("FirstFake").provide(), is(1));
        assertThat(fakes.get("faketwo").provide(), is(2));

        Map<String, Demo> demos = container.getPlugins(Demo.class);
        assertThat(demos.size(), is(2));
        assertThat(demos.keySet(), containsInAnyOrder("a", "demob"));
        assertThat(demos.get("a").getLetter(), is("A"));
        assertThat(demos.get("demob").getLetter(), is("B"));

        assertThat(container.getPlugin(Fake.class, "FirstFake").provide(), is(1));
        assertThat(container.getPlugin(Fake.class, "faketwo").provide(), is(2));

        assertThat(container.getPlugin(Demo.class, "a").getLetter(), is("A"));
        assertThat(container.getPlugin(Demo.class, "demob").getLetter(), is("B"));

        assertThat(container.getPluginsNamesList(Fake.class), containsInAnyOrder("FirstFake", "faketwo"));
        assertThat(container.getPluginsNamesList(Demo.class), containsInAnyOrder("a", "demob"));
    }
}
