package com.sundoctor.quartz.dao;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sundoctor.example.Constant;

@Repository("quartzDao")
public class QuartzDao {

	private DataSource dataSource;

	@Autowired
	public void setDataSource(@Qualifier("dataSource") DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public static void main(String[] args) {
		long next_val=1483596021395L;//1431508020000L;
		long prev_val=1483595721395L;//1431421620000L;
		long start_val=1428624000000L;//1429175220000L;
		long end_val=1433063220000L;
		String next_fire_time = DateFormatUtils.format(next_val, "yyyy-MM-dd HH:mm:ss");
		String prev_fire_time = DateFormatUtils.format(prev_val, "yyyy-MM-dd HH:mm:ss");
		String start_time = DateFormatUtils.format(start_val, "yyyy-MM-dd HH:mm:ss");
		String end_time = DateFormatUtils.format(end_val, "yyyy-MM-dd HH:mm:ss");
		System.out.println("下次执行时间："+next_fire_time);
		System.out.println("上次执行时间："+prev_fire_time);
		System.out.println("开始时间："+start_time);
		System.out.println("结束时间："+end_time);
	}
	public List<Map<String, Object>> getQrtzTriggers() {
		List<Map<String, Object>> results = getJdbcTemplate().queryForList(
				"select * from QRTZ_TRIGGERS order by start_time");
		long val = 0;
		String temp = null;
		for (Map<String, Object> map : results) {
			temp = MapUtils.getString(map, "trigger_name");
			if (StringUtils.indexOf(temp, "&") != -1) {
				map.put("display_name", StringUtils.substringBefore(temp, "&"));
			} else {
				map.put("display_name", temp);
			}

			val = MapUtils.getLongValue(map, "next_fire_time");
			if (val > 0) {
				map.put("next_fire_time", DateFormatUtils.format(val, "yyyy-MM-dd HH:mm:ss"));
			}

			val = MapUtils.getLongValue(map, "prev_fire_time");
			if (val > 0) {
				map.put("prev_fire_time", DateFormatUtils.format(val, "yyyy-MM-dd HH:mm:ss"));
			}

			val = MapUtils.getLongValue(map, "start_time");
			if (val > 0) {
				map.put("start_time", DateFormatUtils.format(val, "yyyy-MM-dd HH:mm:ss"));
			}

			val = MapUtils.getLongValue(map, "end_time");
			if (val > 0) {
				map.put("end_time", DateFormatUtils.format(val, "yyyy-MM-dd HH:mm:ss"));
			}

			map.put("statu", Constant.status.get(MapUtils.getString(map, "trigger_state")));
		}

		return results;
	}

	private JdbcTemplate getJdbcTemplate() {
		return new JdbcTemplate(this.dataSource);
	}
}
