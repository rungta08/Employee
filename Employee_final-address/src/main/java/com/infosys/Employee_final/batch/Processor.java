package com.infosys.Employee_final.batch;


import com.infosys.Employee_final.entity.AddressBatchEntity;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

@Component
public class Processor implements ItemProcessor<AddressBatchEntity, AddressBatchEntity>  {
	
	private static final Map<String, String> CITY_NAMES =
            new HashMap<>();

    public Processor() {
    	CITY_NAMES.put("1", "Pune");
    	CITY_NAMES.put("2", "Mumbai");
    	CITY_NAMES.put("3", "Bangalore");
    	CITY_NAMES.put("4", "Jaipur");
    }

    @Override
    public AddressBatchEntity process(AddressBatchEntity addressBatchEntity) throws Exception {
        String cityCode = addressBatchEntity.getCity();
        String city = CITY_NAMES.get(cityCode);
        addressBatchEntity.setCity(city);
        return addressBatchEntity;
    }
}
