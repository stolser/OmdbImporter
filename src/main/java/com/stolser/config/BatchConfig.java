package com.stolser.config;

import com.stolser.batch.VideoIdProcessor;
import com.stolser.batch.VideoIdReader;
import com.stolser.batch.VideoWriter;
import com.stolser.entity.Video;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @StepScope
    @Bean
    public ItemReader reader() {
        return new VideoIdReader();
    }

    @Bean
    public ItemProcessor processor() {
        return new VideoIdProcessor();
    }

    @Bean
    public ItemWriter writer() {
        return new VideoWriter();
    }

    @Bean
    public Job importVideo() {
        return jobBuilderFactory.get("importVideo")
                .incrementer(new RunIdIncrementer())
                .flow(step1())
                .end()
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<String, List<Video>> chunk(1)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }
}
