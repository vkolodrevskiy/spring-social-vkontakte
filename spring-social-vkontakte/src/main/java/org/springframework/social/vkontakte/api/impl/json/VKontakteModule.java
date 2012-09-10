/*
 * Copyright 2011 the original author or authors.
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

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.*;
import org.codehaus.jackson.map.introspect.AnnotatedClass;
import org.codehaus.jackson.map.introspect.JacksonAnnotationIntrospector;
import org.codehaus.jackson.map.jsontype.NamedType;
import org.codehaus.jackson.map.jsontype.TypeIdResolver;
import org.codehaus.jackson.map.jsontype.impl.AsWrapperTypeDeserializer;
import org.codehaus.jackson.map.jsontype.impl.StdTypeResolverBuilder;
import org.codehaus.jackson.map.module.SimpleDeserializers;
import org.codehaus.jackson.map.module.SimpleModule;
import org.codehaus.jackson.type.JavaType;
import org.springframework.social.vkontakte.api.*;

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
        super("VKontakteModule", new Version(1, 0, 0, null));
    }

    @Override
    public void setupModule(SetupContext context) {
        context.insertAnnotationIntrospector(new JacksonAnnotationIntrospector() {

            @Override
            protected StdTypeResolverBuilder _constructStdTypeResolverBuilder() {
                return new StdTypeResolverBuilder() {


                    @Override
                    public TypeDeserializer buildTypeDeserializer(DeserializationConfig config, JavaType baseType, Collection<NamedType> subtypes, BeanProperty property) {
                        if (Attachment.class.isAssignableFrom(baseType.getRawClass())) {
                            TypeIdResolver idRes = idResolver(config, baseType, subtypes, false, true);
                            return new AsWrapperTypeDeserializer(baseType, idRes, property, _defaultImpl) {

                                @Override
                                public Object deserializeTypedFromAny(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
                                    return deserializeTypedFromObject(jp, ctxt);    //To change body of overridden methods use File | Settings | File Templates.
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
                            return super.buildTypeDeserializer(config, baseType, subtypes, property);    //To change body of overridden methods use File | Settings | File Templates.
                        }
                    }
                };
            }

        });

        SimpleDeserializers deserializers = new SimpleDeserializers();
        deserializers.addDeserializer(Attachment.class, new JsonDeserializer<Attachment>() {
            @Override
            public Attachment deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
                System.err.println(ctxt.getConfig().getAnnotationIntrospector().findTypeResolver(ctxt.getConfig(), AnnotatedClass.construct(Attachment.class, ctxt.getConfig().getAnnotationIntrospector(), ctxt.getConfig()),
                        ctxt.getTypeFactory().constructType(Attachment.class)));
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }
        });
        context.addDeserializers(deserializers);

        context.setMixInAnnotations(VKontakteProfile.class, VKontakteProfileMixin.class);
        context.setMixInAnnotations(VKontakteProfiles.class, VKontakteProfilesMixin.class);
        context.setMixInAnnotations(Post.class, PostMixin.class);
        context.setMixInAnnotations(NewsPost.class, NewsPostMixin.class);
        context.setMixInAnnotations(PostStatusResponse.class, PostStatusResponseMixin.class);
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
    }
}
