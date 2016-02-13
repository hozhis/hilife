package cn.dolphinsoft.hilife.common.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import cn.dolphinsoft.hilife.common.enumeration.PageEnum;

/**
 * Class Name: PageEnumSerializer
 * 
 * Description: TODO
 * 
 * @author hozhis
 * 
 */

public class PageEnumSerializer extends JsonSerializer<PageEnum> {

    @Override
    public void serialize(PageEnum value, JsonGenerator generator, SerializerProvider provider) throws IOException {
        generator.writeStartObject();
        generator.writeFieldName("code");
        generator.writeString(value.getCode());
        generator.writeFieldName("text");
        generator.writeString(value.getText());
        generator.writeFieldName("name");
        generator.writeString(value.name());
        generator.writeEndObject();
    }

    @Override
    public Class<PageEnum> handledType() {
        return PageEnum.class;
    }
}
