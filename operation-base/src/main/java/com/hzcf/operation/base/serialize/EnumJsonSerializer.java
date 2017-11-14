package com.hzcf.operation.base.serialize;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.hzcf.operation.base.enums.EnumType;

/**
 * Create by hanlin on 2017年11月8日
 **/
public class EnumJsonSerializer extends JsonSerializer<EnumType>{  
    @Override  
    public void serialize(EnumType obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {  
        jsonGenerator.writeStartObject();
        jsonGenerator.writeFieldName("code");
        jsonGenerator.writeNumber(obj.code());
        jsonGenerator.writeFieldName("name");
        jsonGenerator.writeString(obj.name());
        jsonGenerator.writeFieldName("text");
        jsonGenerator.writeString(obj.text());
        jsonGenerator.writeEndObject();
    }  
}  
