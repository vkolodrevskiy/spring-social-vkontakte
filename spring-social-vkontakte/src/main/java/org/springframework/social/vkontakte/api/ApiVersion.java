package org.springframework.social.vkontakte.api;

/**
 * Represents VK api version.
 *
 * @author vkolodrevskiy
 */
public enum ApiVersion {
    VERSION_3_0("3.0"),
    VERSION_5_7("5.7");

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
