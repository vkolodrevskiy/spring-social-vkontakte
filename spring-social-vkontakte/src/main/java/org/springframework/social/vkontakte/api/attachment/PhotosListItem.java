package org.springframework.social.vkontakte.api.attachment;

public class PhotosListItem {
    private long photoId;
    private long ownerId;

    public PhotosListItem(String ownerAndPhotoId) {
        int separator = ownerAndPhotoId.indexOf("_");
        ownerId = Long.parseLong(ownerAndPhotoId.substring(0, separator));
        photoId = Long.parseLong(ownerAndPhotoId.substring(separator + 1));
    }

    public long getPhotoId() {
        return photoId;
    }

    public long getOwnerId() {
        return ownerId;
    }
}
