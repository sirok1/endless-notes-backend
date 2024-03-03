package ru.notes.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@PropertySources(value = {@PropertySource("classpath:application.yml")})
@ComponentScan(basePackages = {"ru.notes"})
@Configuration
public class AppConfig { }
