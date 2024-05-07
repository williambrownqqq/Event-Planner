package com.zanchenko.alex.diploma.dto;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.IOException;
import java.util.Date;

@Getter
@Setter
@JsonSerialize(using = ElectricityGeneration.ElectricityGenerationSerializer.class)
public class ElectricityGeneration extends CsvBean{

    @CsvBindByName(column = "country_name")
    private String region;

    @CsvBindByName(column = "date")
    @CsvDate("MM/dd/yyyy") // Specify the date format
    private Date date;

    @CsvBindByName(column = "parameter")
    private String parameter;

    @CsvBindByName(column = "product")
    private String product;

    @CsvBindByName(column = "value")
    private double value;

    @CsvBindByName(column = "unit")
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

