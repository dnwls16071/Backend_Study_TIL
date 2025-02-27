package com.jwj.db2_start.jdbctemplate;

import com.jwj.db2_start.domain.Item;
import com.jwj.db2_start.repository.ItemRepository;
import com.jwj.db2_start.repository.ItemSearchCond;
import com.jwj.db2_start.repository.ItemUpdateDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Repository
public class JdbcTemplateItemRepositoryV3 implements ItemRepository {

	private final NamedParameterJdbcTemplate template;
	private final SimpleJdbcInsert jdbcInsert;

	public JdbcTemplateItemRepositoryV3(DataSource dataSource) {
		this.template = new NamedParameterJdbcTemplate(dataSource);
		this.jdbcInsert = new SimpleJdbcInsert(dataSource)
				.withTableName("item")
				.usingGeneratedKeyColumns("id");
	}

	@Override
	public Item save(Item item) {
		String sql = "insert into item (item_name, price, quantity) " +
				"values (:itemName, :price, :quantity)";
		SqlParameterSource param = new BeanPropertySqlParameterSource(item);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		template.update(sql, param, keyHolder);
		Long key = keyHolder.getKey().longValue();
		item.setId(key);
		return item;
	}

	@Override
	public void update(Long itemId, ItemUpdateDto updateParam) {
		String sql = "update item " +
				"set item_name=:itemName, price=:price, quantity=:quantity " +
				"where id=:id";
		SqlParameterSource param = new MapSqlParameterSource()
				.addValue("itemName", updateParam.getItemName())
				.addValue("price", updateParam.getPrice())
				.addValue("quantity", updateParam.getQuantity())
				.addValue("id", itemId); //이 부분이 별도로 필요하다.
		template.update(sql, param);
	}

	@Override
	public Optional<Item> findById(Long id) {
		String sql = "select id, item_name, price, quantity from item where id = :id";
		try {
			Map<String, Object> param = Map.of("id", id);
			Item item = template.queryForObject(sql, param, itemRowMapper());
			return Optional.of(item);
		} catch (EmptyResultDataAccessException e) {
			return Optional.empty();
		}
	}

	@Override
	public List<Item> findAll(ItemSearchCond cond) {
		Integer maxPrice = cond.getMaxPrice();
		String itemName = cond.getItemName();
		SqlParameterSource param = new BeanPropertySqlParameterSource(cond);
		String sql = "select id, item_name, price, quantity from item";
		//동적 쿼리
		if (StringUtils.hasText(itemName) || maxPrice != null) {
			sql += " where";
		}
		boolean andFlag = false;
		if (StringUtils.hasText(itemName)) {
			sql += " item_name like concat('%',:itemName,'%')";
			andFlag = true;
		}
		if (maxPrice != null) {
			if (andFlag) {
				sql += " and";
			}
			sql += " price <= :maxPrice";
		}
		log.info("sql={}", sql);
		return template.query(sql, param, itemRowMapper());
	}

	private RowMapper<Item> itemRowMapper() {
		return BeanPropertyRowMapper.newInstance(Item.class); //camel 변환 지원
	}
}
