package fst.demo;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableBatchProcessing
@Configuration
public class BatchConfig {

    private final JobBuilderFactory jobBuilderFactory;

    private final StepBuilderFactory stepBuilderFactory;

    private final HwTasklet helloWorldTasklet;

    public BatchConfig(JobBuilderFactory jobBuilderFactory,
                       StepBuilderFactory stepBuilderFactory,
                       HwTasklet helloWorldTasklet) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.helloWorldTasklet = helloWorldTasklet;
    }

    @Bean
    public Job helloWorldJob(Step helloWorldStep) {
        return jobBuilderFactory.get("helloWorldJob") //Job名を指定
                .flow(helloWorldStep) //実行するStepを指定
                .end()
                .build();
    }

    @Bean
    public Step helloWorldStep() {
        return stepBuilderFactory.get("helloWorldStep") //Step名を指定
                .tasklet(helloWorldTasklet) //実行するTaskletを指定
                .build();
    }
}