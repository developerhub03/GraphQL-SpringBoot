package com.graphql.demo;

import com.oembedler.moon.graphql.boot.GraphQLServletProperties;
import graphql.execution.instrumentation.tracing.TracingInstrumentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(value = "graphql.servlet.enabled", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties({GraphQLServletProperties.class})
public class GraphQLInstrumentationAutoConfiguration {

  @Value("${graphql.servlet.maxQueryComplexity:#{null}}")
  private Integer maxQueryComplexity;

  @Value("${graphql.servlet.maxQueryDepth:#{null}}")
  private Integer maxQueryDepth;

  @Value("${graphql.servlet.tracing-enabled:#{false}}")
  private Boolean tracingEnabled;

  @Bean
  @ConditionalOnMissingBean({TracingInstrumentation.class})
  @ConditionalOnProperty(value = "graphql.servlet.tracing-enabled", havingValue = "true")
  public TracingInstrumentation tracingInstrumentation() {
    return new TracingInstrumentation();
  }

}


