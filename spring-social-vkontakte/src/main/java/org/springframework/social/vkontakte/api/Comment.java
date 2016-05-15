package org.springframework.social.vkontakte.api;

import org.springframework.social.vkontakte.api.attachment.Attachment;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * Model class representing a comment on a post on a user wall or community wall.
 *
 * @see <a href="https://vk.com/dev/comment_object">Comment object | Developers | VK</a>
 */
public class Comment {
    private long id;
    private long fromId;
    private Date date;
    private String text;
    private long replyToUser;
    private long replyToComment;
    private Post.Likes likes;
    private List<? extends Attachment> attachments;

    public long getFromId() {
        return fromId;
    }

    public Date getDate() {
        return date;
    }

    public String getText() {
        return text;
    }

    public long getId() {
        return id;
    }

    public long getReplyToUser() {
        return replyToUser;
    }

    public long getReplyToComment() {
        return replyToComment;
    }

    public Post.Likes getLikes() {
        return likes;
    }

    public List<? extends Attachment> getAttachments() {
        return attachments;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id='" + id + '\'' +
                ", date=" + date +
                ", text='" + text + '\'' +
                ", likes=" + likes +
                ", attachments=" + (attachments == null ? null : StringUtils.collectionToDelimitedString(attachments, ",\n")) +
                '}';
    }
}
