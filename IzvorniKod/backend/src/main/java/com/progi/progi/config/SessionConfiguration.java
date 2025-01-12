package com.progi.progi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;
import javax.sql.DataSource;

@Configuration(proxyBeanMethods = false)
@EnableJdbcHttpSession
public class SessionConfiguration {
}
