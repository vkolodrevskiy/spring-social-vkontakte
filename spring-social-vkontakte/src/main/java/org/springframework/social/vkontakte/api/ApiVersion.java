package org.springframework.social.vkontakte.api;

/**
 * Represents VK api version.
 *
 * @author vkolodrevskiy
 */
public enum ApiVersion {
    VERSION_3_0("3.0"),
    VERSION_5_7("5.7"),
    VERSION_5_8("5.8"),
    VERSION_5_21("5.21"),
    VERSION_5_27("5.27");

    private String version;

    private ApiVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return version;
    }

    public String getVersion() {
        return version;
    }
}
