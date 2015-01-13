package org.springframework.social.vkontakte.api.attachment;

/**
 * TODO: add description
 *
 * @author vkolodrevskiy
 */
public class Poll {
    private long pollId;
    private String question;

    public long getPollId() {
        return pollId;
    }

    public String getQuestion() {
        return question;
    }
}
