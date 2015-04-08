package org.springframework.social.vkontakte.api.attachment;

/**
 * Sticker attachment
 *
 * @author dIsoVi
 */
public class Sticker {
    private long stickerId;
    private long productId;
    private String photo64;
    private String photo128;
    private String photo256;
    private String photo352;
    private long width;
    private long height;

    public long getStickerId() {
        return stickerId;
    }

    public long getProductId() {
        return productId;
    }

    public String getPhoto64() {
        return photo64;
    }

    public String getPhoto128() {
        return photo128;
    }

    public String getPhoto256() {
        return photo256;
    }

    public String getPhoto352() {
        return photo352;
    }

    public long getWidth() {
        return width;
    }

    public long getHeight() {
        return height;
    }
}
