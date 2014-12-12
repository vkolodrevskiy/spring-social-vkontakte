package org.springframework.social.vkontakte.api.attachment;

import java.util.List;

public class PhotosListAttachment extends Attachment {
    public List<PhotosListItem> getPhotosList() {
        return photosList;
    }

    private List<PhotosListItem> photosList;
}
