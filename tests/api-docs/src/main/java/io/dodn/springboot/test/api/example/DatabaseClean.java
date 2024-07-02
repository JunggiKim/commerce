package io.dodn.springboot.test.api.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.test.context.TestConstructor;
import org.springframework.transaction.annotation.Transactional;

import groovy.util.logging.Slf4j;
import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.EntityType;

@Component
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@Slf4j
public class DatabaseClean {

	private final Logger log = LoggerFactory.getLogger(getClass());
	private JdbcTemplate jdbcTemplate;
	private EntityManager entityManager;

	@Transactional
    public void all() {
        var tables = entityManager.getMetamodel().getEntities().stream()
                .map(EntityType::getName)
                .toList();

        tables.forEach(table -> {
      		log.info(table + " : data 삭제 중");
            jdbcTemplate.execute("TRUNCATE table " + table);
        });
    }


}
