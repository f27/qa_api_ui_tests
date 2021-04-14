package config;

import org.aeonbits.owner.Config;

import static org.aeonbits.owner.Config.LoadType.MERGE;

@Config.LoadPolicy(MERGE)
@Config.Sources({
        "system:properties",
        "classpath:config/testdata.properties"
})
public interface TestDataConfig extends Config {
    @Key("web.url")
    String webUrl();

    @Key("api.url")
    String apiUrl();

    @Key("user.email")
    String userEmail();

    @Key("user.password")
    String userPassword();

    @Key("items.simple.id")
    String simpleItemId();

    @Key("items.simple.name")
    String simpleItemName();

    @Key("items.simple.quantity")
    String simpleItemQuantity();

    @Key("items.withDetails.id")
    String withDetailsItemId();

    @Key("items.withDetails.name")
    String withDetailsItemName();

    @Key("items.withDetails.quantity")
    String withDetailsItemQuantity();

    @Key("items.withDetails.details")
    String[] withDetailsItemDetails();
}
