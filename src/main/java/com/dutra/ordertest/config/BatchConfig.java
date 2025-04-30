package com.dutra.ordertest.config;

import com.dutra.ordertest.model.Order;
import com.dutra.ordertest.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Collections;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    private static final Logger logger = LoggerFactory.getLogger(BatchConfig.class);
    private final OrderRepository orderRepository;
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    public BatchConfig(OrderRepository orderRepository, JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        this.orderRepository = orderRepository;
        this.jobRepository = jobRepository;
        this.transactionManager = transactionManager;
    }

    @Bean
    public Job processOrderJob(Step orderStep) {
        return new JobBuilder("processOrderJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(orderStep)
                .build();
    }

    @Bean
    public Step orderStep(ItemReader<Order> orderReader,
                          ItemProcessor<Order, Order> orderProcessor,
                          ItemWriter<Order> orderWriter) {
        return new StepBuilder("orderStep", jobRepository)
                .<Order, Order>chunk(10, transactionManager)
                .reader(orderReader)
                .processor(orderProcessor)
                .writer(orderWriter)
                .build();
    }

    @Bean
    public ItemReader<Order> orderReader() {
        RepositoryItemReader<Order> reader = new RepositoryItemReader<>();
        reader.setRepository(orderRepository);
        reader.setMethodName("findAll");
        reader.setPageSize(10);
        reader.setSort(Collections.singletonMap("id", Sort.Direction.ASC));
        return reader;
    }

    @Bean
    public ItemProcessor<Order, Order> orderProcessor() {
        return order -> {
            logger.info("processing order ID: {}", order.getId());
            order.setStatus("PROCESSING");
            return order;
        };
    }

    @Bean
    public ItemWriter<Order> orderWriter() {
        return items -> {
            for (Order order : items) {
                logger.info("recordind order ID: {}", order.getId());
            }
        };
    }
}
