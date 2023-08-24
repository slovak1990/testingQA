package testovoe08;

import lombok.Getter;
import ru.yandex.qatools.properties.PropertyLoader;
import ru.yandex.qatools.properties.annotations.Property;
import ru.yandex.qatools.properties.annotations.Resource;

@Getter
@Resource.Classpath("test-properties/release.properties")
public class Properties {
    private static Properties properties;
    @Property("login1")
    private String login1;
    @Property("password1")
    private String password1;

    @Property("header.desktop")
    private String headerDesktop;
    @Property("title.desktop")
    private String titleDesktop;

    private Properties() {
        PropertyLoader.populate(this);
    }

    public static Properties getInstance() {
        if (properties == null) {
            properties = new Properties();
        }
        return properties;
    }
}