package com.zanchenko.alex.diploma.dto.monitoring;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.Date;

@Getter
@Setter
@JsonSerialize(using = ElectricityGeneration.ElectricityGenerationSerializer.class)
public class ElectricityGeneration extends CsvBean {

    private String region;

    private String date;

    private String parameter;

    private String product;

    private double value;

    private String unit;


    public static class ElectricityGenerationSerializer extends JsonSerializer<ElectricityGeneration> {

        // No need to provide a default constructor explicitly

        @Override
        public void serialize(ElectricityGeneration value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            gen.writeStartObject();
            gen.writeStringField("region", value.getRegion());
            gen.writeStringField("date", value.getDate().toString()); // Convert Date to String
            gen.writeStringField("parameter", value.getParameter());
            gen.writeStringField("product", value.getProduct());
            gen.writeNumberField("value", value.getValue());
            gen.writeStringField("unit", value.getUnit());
            gen.writeEndObject();
        }
    }
}

