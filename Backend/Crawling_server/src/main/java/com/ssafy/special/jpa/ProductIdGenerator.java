package com.ssafy.special.jpa;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentityGenerator;

import com.ssafy.special.domain.ProductSellList;

public class ProductIdGenerator extends IdentityGenerator {
	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
//		if (object instanceof ProductSellList) {
//			ProductSellList p = (ProductSellList) object;
//			return p.getId() == 0 ? super.generate(session, object) : p.getId();
//		} else {
//			throw new RuntimeException("Product entity가 아니에요.");
//		}

		
		// 이것도 됨
		Serializable id = session.getEntityPersister(null, object).getClassMetadata().getIdentifier(object, session);
		return id != null ? id : super.generate(session, object);
		 
	}
}