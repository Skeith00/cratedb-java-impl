package com.blog.api.model.converter;

import com.blog.api.model.Metadata;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.crate.shade.org.postgresql.util.PGobject;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;

import java.sql.SQLException;

public class MetadataConverter {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @WritingConverter
    public enum EntityWritingConverter implements Converter<Metadata, PGobject> {
        INSTANCE;
        @Override
        public PGobject convert(Metadata source) {
            PGobject jsonObject = new PGobject();
            jsonObject.setType("json");
            try {
                jsonObject.setValue(objectMapper.writeValueAsString(source));
            } catch (SQLException | JsonProcessingException throwables) {
                throwables.printStackTrace();
            }
            return jsonObject;
        }
    }

    @ReadingConverter
    public enum EntityReadingConverter implements Converter<PGobject, Metadata> {
        INSTANCE;
        @Override
        public Metadata convert(PGobject pgObject) {
            String source = pgObject.getValue();
            try {
                return objectMapper.readValue(source, Metadata.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
