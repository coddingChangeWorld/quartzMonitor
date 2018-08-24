package com.service.sport;

import com.common.base.dao.BaseDaoImpl;
import com.common.dto.counterUserOrderDto;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Observer;

@Service("bbuserOrderService")
public class BasketballUserOrderService implements OrderHandler {
	@Autowired
	private BaseDaoImpl baseDaoImpl;

	public void handler(String id) {
		Map<String, Object> param = new HashMap();
		param.put("matchId", id);
		List<counterUserOrderDto> list = this.baseDaoImpl.findByNameSql("queryCountBetOrder", param, counterUserOrderDto.class);
		for (int i = 0; i < list.size(); i++) {
			counterUserOrderDto orderDto = (counterUserOrderDto) list.get(i);

			orderDto.counter();
			Map<String, Object> upParam = new HashMap();
			if (orderDto.getAmount().doubleValue() < 0.0D) {
				upParam.put("userId", orderDto.getPublishUserId());
				upParam.put("amount", orderDto.getNum());
				this.baseDaoImpl.executeByNameSql("updateUserCurrencyById", upParam);
			} else {
				upParam.put("userId", orderDto.getPublishUserId());
				double tempAmount = orderDto.getAmount().doubleValue() - orderDto.getNum().doubleValue();
				if (tempAmount > 0.0D) {
					tempAmount = -tempAmount;
				}
				upParam.put("amount", Double.valueOf(tempAmount));
				this.baseDaoImpl.executeByNameSql("updateUserCurrencyById", upParam);

				upParam.put("userId", orderDto.getUserId());
				upParam.put("amount", orderDto.getAmount());
				this.baseDaoImpl.executeByNameSql("updateUserCurrencyById", upParam);
			}
		}
	}
}
