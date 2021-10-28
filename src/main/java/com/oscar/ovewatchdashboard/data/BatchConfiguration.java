package com.oscar.ovewatchdashboard.data;
//watch for 10-11 in github for import might be different (double checekr)
import javax.sql.DataSource;

import com.oscar.ovewatchdashboard.model.Match;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
// import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
    private final String[] FIELD_NAMES = new String[] { "start_time", "esports_match_id", "tournament_title",
            "map_type", "map_name", "player_name", "team_name", "stat_name", "hero_name", "stat_amount"

    };

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

  @Bean
public FlatFileItemReader<MatchInput> reader() {
  return new FlatFileItemReaderBuilder<MatchInput>()
    .name("MatchItemReader")
    .resource(new ClassPathResource("match-data.csv"))
    .delimited()
    .names(FIELD_NAMES)
    .fieldSetMapper(new BeanWrapperFieldSetMapper <MatchInput>() {
        {
      setTargetType(MatchInput.class);
    }
})
    .build();
}

    @Bean
    public MatchDataProcessor processor() {
        return new MatchDataProcessor ();
    }

    /**
     * 
     * @param dataSource
     * @return
     */
@Bean
public JdbcBatchItemWriter<Match> writer(DataSource dataSource) {
  return new JdbcBatchItemWriterBuilder<Match>()
    .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
    .sql("INSERT INTO people (start_time,esports_match_id,tournament_title,map_type,map_name,player_name,team_name,stat_name,hero_name,stat_amount)"
    + 
    "VALUES (startTime,id,tournamentTitle,mapType,mapName,playerName,teamName,statName,heroName,statAmount)")
    .dataSource(dataSource)
    .build();
}
/**
 * Tells job what the steps are going to do 
 * @param listener
 * @param step1
 * @return
 */
@Bean
public Job importUserJob(JobCompletionNotificationListener listener, Step step1) {
  return jobBuilderFactory.get("importUserJob")
    .incrementer(new RunIdIncrementer())
    .listener(listener)
    .flow(step1)
    .end()
    .build();
}

@Bean
public Step step1(JdbcBatchItemWriter<Person> writer) {
  return stepBuilderFactory.get("step1")
    .<Person, Person> chunk(10)
    .reader(reader())
    .processor(processor())
    .writer(writer)
    .build();
}
}
