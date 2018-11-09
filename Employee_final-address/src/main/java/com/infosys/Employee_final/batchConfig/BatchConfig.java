package com.infosys.Employee_final.batchConfig;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import com.infosys.Employee_final.entity.AddressBatchEntity;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
	
	@Bean
	public Job job(JobBuilderFactory jobBuilderFactory,
					StepBuilderFactory stepBuilderFactory,
					ItemReader<AddressBatchEntity> itemReader,
					ItemProcessor<AddressBatchEntity, AddressBatchEntity> itemProcessor,
					ItemWriter<AddressBatchEntity> itemWriter) {
		
		Step step = stepBuilderFactory.get("ETL-File-Load")
					.<AddressBatchEntity, AddressBatchEntity>chunk(100)
					.reader(itemReader)
					.processor(itemProcessor)
					.writer(itemWriter)
					.build();
		return jobBuilderFactory.get("ETL-Load")
			.incrementer(new RunIdIncrementer())
			.start(step )
			.build();

	}
	
	@Bean
	public FlatFileItemReader<AddressBatchEntity> itemReader(@Value("${input}") Resource resource) {
	
	    FlatFileItemReader<AddressBatchEntity> flatFileItemReader = new FlatFileItemReader<>();
	    flatFileItemReader.setResource(resource);
	    flatFileItemReader.setName("CSV-Reader");
	    flatFileItemReader.setLinesToSkip(1);
	    flatFileItemReader.setLineMapper(lineMapper());
	    return flatFileItemReader;
	 }
	 
	 @Bean
     public LineMapper<AddressBatchEntity> lineMapper() {

        DefaultLineMapper<AddressBatchEntity> defaultLineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();

        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames(new String[]{"id", "city", "country", "zipcode"});

        BeanWrapperFieldSetMapper<AddressBatchEntity> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(AddressBatchEntity.class);

        defaultLineMapper.setLineTokenizer(lineTokenizer);
        defaultLineMapper.setFieldSetMapper(fieldSetMapper);

        return defaultLineMapper;
     }

}
