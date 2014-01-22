package org.springframework.social.vkontakte.config.support;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.xml.ApiHelper;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.vkontakte.api.VKontakte;

/**
 * Support class for JavaConfig and XML configuration support.
 * Creates an API binding instance for the current user's connection.
 * @author vkolodrevskiy
 */
public class VKontakteApiHelper implements ApiHelper<VKontakte> {

    private final UsersConnectionRepository usersConnectionRepository;

    private final UserIdSource userIdSource;

    private VKontakteApiHelper(UsersConnectionRepository usersConnectionRepository, UserIdSource userIdSource) {
        this.usersConnectionRepository = usersConnectionRepository;
        this.userIdSource = userIdSource;
    }

    public VKontakte getApi() {
        if (logger.isDebugEnabled()) {
            logger.debug("Getting API binding instance for VKontakte");
        }

        Connection<VKontakte> connection = usersConnectionRepository.createConnectionRepository(userIdSource.getUserId()).findPrimaryConnection(VKontakte.class);
        if (logger.isDebugEnabled() && connection == null) {
            logger.debug("No current connection; Returning default VKontakteTemplate instance.");
        }
        return connection != null ? connection.getApi() : null;
    }

    private final static Log logger = LogFactory.getLog(VKontakteApiHelper.class);

}