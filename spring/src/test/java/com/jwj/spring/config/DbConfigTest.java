package com.jwj.spring.config;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.TransactionManager;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
class DbConfigTest {

	@Autowired private DataSource dataSource;
	@Autowired private TransactionManager transactionManager;
	@Autowired private JdbcTemplate jdbcTemplate;

	@Test
	void checkBean() {
		log.info("dataSource = {}", dataSource);
		log.info("transactionManager = {}", transactionManager);
		log.info("jdbcTemplate = {}", jdbcTemplate);

		assertThat(dataSource).isNotNull();
		assertThat(transactionManager).isNotNull();
		assertThat(jdbcTemplate).isNotNull();
	}
}