package com.zanchenko.alex.diploma.service;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.zanchenko.alex.diploma.dto.ElectricityGeneration;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MonitoringServiceImpl implements MonitoringService{

    private final ResourceLoader resourceLoader;

    @Override
    public List<ElectricityGeneration> getData() {
        Resource resource = resourceLoader.getResource("classpath:csv/electricity_production_data.csv");
        List<ElectricityGeneration> records = new ArrayList<>();

        try (Reader reader = new InputStreamReader(resource.getInputStream())) {
            CsvToBean<ElectricityGeneration> csvToBean = new CsvToBeanBuilder<ElectricityGeneration>(reader)
                    .withType(ElectricityGeneration.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            Iterator<ElectricityGeneration> csvRecordIterator = csvToBean.iterator();
            int count = 0;
            while (csvRecordIterator.hasNext() && count < 100) {
                records.add(csvRecordIterator.next());
                count++;
            }
//            return csvToBean.parse();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return records;
    }

}
