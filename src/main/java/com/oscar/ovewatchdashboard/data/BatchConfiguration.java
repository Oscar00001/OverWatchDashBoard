package com.oscar.ovewatchdashboard.data;
import javax.sql.DataSource;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import com.oscar.ovewatchdashboard.model.Match;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
    private final String[] FIELD_NAMES = new String[] {  "round_start_time", "round_end_time", "stage", "match_id", "game_number",
     "match_winner", "map_winner", "map_loser", "map_name", "map_round", "winning_team_final_map_score", "losing_team_final_map_score", 
     "control_round_name", "attacker", "defender", "team_one_name", "team_two_name", "attacker_payload_distance", "defender_payload_distance", 
     "attacker_time_banked", "defender_time_banked", "attacker_control_perecent",
    "defender_control_perecent", "attacker_round_end_score", "defender_round_end_score" };
    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public FlatFileItemReader<MatchInput> reader() {
        return new FlatFileItemReaderBuilder<MatchInput>().name("MatchItemReader")
                .resource(new ClassPathResource("match_map_stats.csv")).delimited().names(FIELD_NAMES).linesToSkip(10)
                .fieldSetMapper(new BeanWrapperFieldSetMapper<MatchInput>() {
                    {
                        setTargetType(MatchInput.class);
                    }
                }).build();
    }
    @Bean
    public MatchDataProcessor processor() {
        return new MatchDataProcessor();
    }
    @Bean
    public JdbcBatchItemWriter<Match> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Match>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO match ( round_start_time, match_id, game_number, match_winner, map_winner, map_loser, map_name, map_round, winning_team_final_map_score, losing_team_final_map_score, control_round_name, attacker, defender, attacker_payload_distance) " 
                + 
                " VALUES (:roundStartTime, :matchId, :gameNumber, :matchWinner, :mapWinner, :mapLoser, :mapName, :mapRound, :winningTeamFinalMapScore, :losingTeamFinalMapScore, :controlRoundName, :attacker, :defender, :attackerPayloadDistance)"
                )
                .dataSource(dataSource).build();
    }
    @Bean
    public Job importUserJob(JobCompletionNotificationListener listener, Step step1) {
        return jobBuilderFactory.get("importUserJob")
            .incrementer(new RunIdIncrementer()).listener(listener)
            .flow(step1).end().build();
    }
    @Bean
    public Step step1(JdbcBatchItemWriter<Match> writer) {
        return stepBuilderFactory
            .get("step1").<MatchInput, Match>chunk(1).reader(reader())
            .processor(processor()).writer(writer).build();
    }
}

