package com.infosys.Employee_final.batch;


import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.infosys.Employee_final.entity.AddressBatchEntity;
import com.infosys.Employee_final.repository.AddressBatchRepository;

import java.util.List;

@Component
public class DBWriter implements ItemWriter<AddressBatchEntity> {

    @Autowired
    private AddressBatchRepository addressBatchRepository;

    @Override
    public void write(List<? extends AddressBatchEntity> addressBatchEntity) throws Exception {
        addressBatchRepository.saveAll(addressBatchEntity);
    }
}
