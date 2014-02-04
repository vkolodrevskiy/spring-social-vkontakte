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

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.fasterxml.jackson.databind.jsontype.impl.AsWrapperTypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.impl.StdTypeResolverBuilder;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.social.vkontakte.api.*;
import org.springframework.social.vkontakte.api.attachment.ApplicationAttachment;
import org.springframework.social.vkontakte.api.attachment.Attachment;
import org.springframework.social.vkontakte.api.attachment.AudioAttachment;
import org.springframework.social.vkontakte.api.attachment.DocumentAttachment;
import org.springframework.social.vkontakte.api.attachment.GraffitiAttachment;
import org.springframework.social.vkontakte.api.attachment.LinkAttachment;
import org.springframework.social.vkontakte.api.attachment.NoteAttachment;
import org.springframework.social.vkontakte.api.attachment.PageAttachment;
import org.springframework.social.vkontakte.api.attachment.PhotoAttachment;
import org.springframework.social.vkontakte.api.attachment.PollAttachment;
import org.springframework.social.vkontakte.api.attachment.VideoAttachment;
import org.springframework.social.vkontakte.api.impl.json.attachment.ApplicationAttachmentMixin;
import org.springframework.social.vkontakte.api.impl.json.attachment.AttachmentMixin;
import org.springframework.social.vkontakte.api.impl.json.attachment.AudioAttachmentMixin;
import org.springframework.social.vkontakte.api.impl.json.attachment.DocumentAttachmentMixin;
import org.springframework.social.vkontakte.api.impl.json.attachment.GraffitiAttachmentMixin;
import org.springframework.social.vkontakte.api.impl.json.attachment.LinkAttachmentMixin;
import org.springframework.social.vkontakte.api.impl.json.attachment.NoteAttachmentMixin;
import org.springframework.social.vkontakte.api.impl.json.attachment.PageAttachmentMixin;
import org.springframework.social.vkontakte.api.impl.json.attachment.PhotoAttachmentMixin;
import org.springframework.social.vkontakte.api.impl.json.attachment.PollAttachmentMixin;
import org.springframework.social.vkontakte.api.impl.json.attachment.VideoAttachmentMixin;

import java.io.IOException;
import java.util.Collection;

/**
 * Jackson module for setting up mixin annotations on VKontakte model types.
 * This enables the use of Jackson annotations without
 * directly annotating the model classes themselves.
 * @author vkolodrevskiy
 */
public class VKontakteModule extends SimpleModule {

    public VKontakteModule() {
        super("VKontakteModule");
    }

    @Override
    public void setupModule(SetupContext context) {
        context.insertAnnotationIntrospector(new JacksonAnnotationIntrospector() {

            @Override
            protected StdTypeResolverBuilder _constructStdTypeResolverBuilder() {
                return new StdTypeResolverBuilder() {
                    @Override
                    public TypeDeserializer buildTypeDeserializer(DeserializationConfig config, JavaType baseType, Collection<NamedType> subtypes) {
                        if (Attachment.class.isAssignableFrom(baseType.getRawClass())) {
                            TypeIdResolver idRes = idResolver(config, baseType, subtypes, false, true);
                            return new AsWrapperTypeDeserializer(baseType, idRes, baseType.toString(), true, _defaultImpl) {

                                @Override
                                public Object deserializeTypedFromAny(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
                                    return deserializeTypedFromObject(jp, ctxt);
                                }

                                @Override
                                public Object deserializeTypedFromObject(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
                                    // first, sanity checks
                                    if (jp.getCurrentToken() != JsonToken.START_OBJECT) {
                                        throw ctxt.wrongTokenException(jp, JsonToken.START_OBJECT,
                                                "need JSON Object to contain As.WRAPPER_OBJECT type information for class " + baseTypeName());
                                    }
                                    // should always get field name, but just in case...
                                    if (jp.nextToken() != JsonToken.FIELD_NAME) {
                                        throw ctxt.wrongTokenException(jp, JsonToken.FIELD_NAME,
                                                "need JSON String that contains type id (for subtype of " + baseTypeName() + ")");
                                    }
                                    String fieldName = jp.getText();
                                    if (!fieldName.equals("type")) {
                                        throw ctxt.wrongTokenException(jp, JsonToken.FIELD_NAME,
                                                "expected field 'type', got '" + fieldName + "')");
                                    }
                                    if (jp.nextToken() != JsonToken.VALUE_STRING) {
                                        throw ctxt.wrongTokenException(jp, JsonToken.VALUE_STRING,
                                                "need JSON String that contains type id (for subtype of " + baseTypeName() + ")");
                                    }
                                    String typeId = jp.getText();
                                    if (jp.nextToken() != JsonToken.FIELD_NAME) {
                                        throw ctxt.wrongTokenException(jp, JsonToken.FIELD_NAME,
                                                "expected object container (" + typeId + ")");
                                    }
                                    fieldName = jp.getText();
                                    if (!typeId.equals(fieldName)) {
                                        throw ctxt.wrongTokenException(jp, JsonToken.FIELD_NAME,
                                                "expected field '" + typeId + "', got '" + fieldName + "')");
                                    }
                                    if (jp.nextToken() != JsonToken.START_OBJECT) {
                                        throw ctxt.wrongTokenException(jp, JsonToken.START_OBJECT,
                                                "need JSON Object to contain As.WRAPPER_OBJECT type information for class " + baseTypeName());
                                    }
                                    JsonDeserializer<Object> deser = _findDeserializer(ctxt, typeId);
                                    jp.nextToken();
                                    Object value = deser.deserialize(jp, ctxt);
                                    // And then need the closing END_OBJECT
                                    if (jp.nextToken() != JsonToken.END_OBJECT) {
                                        throw ctxt.wrongTokenException(jp, JsonToken.END_OBJECT,
                                                "expected closing END_OBJECT after type information and deserialized value");
                                    }
                                    return value;
                                }
                            };
                        } else {
                            return super.buildTypeDeserializer(config, baseType, subtypes);
                        }
                    }
                };
            }

        });

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

        context.setMixInAnnotations(Attachment.class, AttachmentMixin.class);
        context.setMixInAnnotations(LinkAttachment.class, LinkAttachmentMixin.class);
        context.setMixInAnnotations(PhotoAttachment.class, PhotoAttachmentMixin.class);
        context.setMixInAnnotations(VideoAttachment.class, VideoAttachmentMixin.class);
        context.setMixInAnnotations(AudioAttachment.class, AudioAttachmentMixin.class);
        context.setMixInAnnotations(DocumentAttachment.class, DocumentAttachmentMixin.class);
        context.setMixInAnnotations(NoteAttachment.class, NoteAttachmentMixin.class);
        context.setMixInAnnotations(GraffitiAttachment.class, GraffitiAttachmentMixin.class);
        context.setMixInAnnotations(ApplicationAttachment.class, ApplicationAttachmentMixin.class);
        context.setMixInAnnotations(PollAttachment.class, PollAttachmentMixin.class);
        context.setMixInAnnotations(PageAttachment.class, PageAttachmentMixin.class);
    }
}
