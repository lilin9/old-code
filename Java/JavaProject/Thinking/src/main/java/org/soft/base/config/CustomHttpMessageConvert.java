package org.soft.base.config;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Created by LILIN on 2023/8/10/09:18:02
 * restTemplate 自定义消息序列化器
 */
@Component
public class CustomHttpMessageConvert extends AbstractHttpMessageConverter<Object> {
    public CustomHttpMessageConvert() {
        super(StandardCharsets.UTF_8, MediaType.ALL);
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return true;
    }

    @Override
    protected Object readInternal(Class<?> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return JSONObject.parseObject(StreamUtils.copyToString(inputMessage.getBody(), StandardCharsets.UTF_8), clazz);
    }

    @Override
    protected void writeInternal(Object o, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        outputMessage.getBody().write(JSONObject.toJSONBytes(o));
    }
}











