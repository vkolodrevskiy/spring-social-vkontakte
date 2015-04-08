/*
 * Copyright 2011-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.social.vkontakte.api.impl.json;

import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.social.vkontakte.api.*;
import org.springframework.social.vkontakte.api.attachment.*;
import org.springframework.social.vkontakte.api.attachment.Audio;
import org.springframework.social.vkontakte.api.impl.json.attachment.*;

/**
 * Jackson module for setting up mixin annotations on VKontakte model types.
 * This enables the use of Jackson annotations without
 * directly annotating the model classes themselves.
 * @author vkolodrevskiy
 */
public class VKontakteModule extends SimpleModule {
    private static final long serialVersionUID = -4982240047547778595L;

    public VKontakteModule() {
        super("VKontakteModule");
    }

    @Override
    public void setupModule(SetupContext context) {
        context.setMixInAnnotations(VKontakteProfile.class, VKontakteProfileMixin.class);
        context.setMixInAnnotations(VKontakteProfiles.class, VKontakteProfilesMixin.class);
        context.setMixInAnnotations(Post.class, PostMixin.class);
        context.setMixInAnnotations(PostStatusResponse.class, PostStatusResponseMixin.class);
        context.setMixInAnnotations(PostStatus.class, PostStatusMixin.class);
        context.setMixInAnnotations(VKResponse.class, VKResponseMixin.class);
        context.setMixInAnnotations(VKGenericResponse.class, VKGenericResponseMixin.class);
        context.setMixInAnnotations(org.springframework.social.vkontakte.api.Error.class, ErrorMixin.class);

        context.setMixInAnnotations(Post.Likes.class, PostLikesMixin.class);
        context.setMixInAnnotations(Post.Reposts.class, PostRepostsMixin.class);
        context.setMixInAnnotations(Post.Comments.class, PostCommentsMixin.class);
        context.setMixInAnnotations(Post.Geo.class, PostGeoMixin.class);
        context.setMixInAnnotations(Place.class, PostGeoPlaceMixin.class);

        context.setMixInAnnotations(Group.class, GroupMixin.class);

        context.setMixInAnnotations(Attachment.class, AttachmentMixin.class);
        context.setMixInAnnotations(Link.class, LinkAttachmentMixin.class);
        context.setMixInAnnotations(Photo.class, PhotoAttachmentMixin.class);
        context.setMixInAnnotations(PhotosListAttachment.class, PhotosListAttachmentMixin.class);
        context.setMixInAnnotations(PostedPhotoAttachment.class, PostedPhotoAttachmentMixin.class);
        context.setMixInAnnotations(Album.class, AlbumAttachmentMixin.class);
        context.setMixInAnnotations(Video.class, VideoAttachmentMixin.class);
        context.setMixInAnnotations(Audio.class, AudioAttachmentMixin.class);
        context.setMixInAnnotations(Document.class, DocumentAttachmentMixin.class);
        context.setMixInAnnotations(Note.class, NoteAttachmentMixin.class);
        context.setMixInAnnotations(Graffiti.class, GraffitiAttachmentMixin.class);
        context.setMixInAnnotations(Sticker.class, StickerAttachmentMixin.class);
        context.setMixInAnnotations(Application.class, ApplicationAttachmentMixin.class);
        context.setMixInAnnotations(Poll.class, PollAttachmentMixin.class);
        context.setMixInAnnotations(Page.class, PageAttachmentMixin.class);
        context.setMixInAnnotations(City.class, CityMixin.class);
        context.setMixInAnnotations(Country.class, CountryMixin.class);
        context.setMixInAnnotations(LastSeen.class, LastSeenMixin.class);
        context.setMixInAnnotations(Occupation.class, OccupationMixin.class);
        context.setMixInAnnotations(Personal.class, PersonalMixin.class);
        context.setMixInAnnotations(University.class, UniversityMixin.class);
        context.setMixInAnnotations(School.class, SchoolMixin.class);
        context.setMixInAnnotations(Relative.class, RelativeMixin.class);
	    context.setMixInAnnotations(CityList.class, CityListMixin.class);
    }
}
