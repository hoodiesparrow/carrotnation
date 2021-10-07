package com.ssafy.special.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSaleCount is a Querydsl query type for SaleCount
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSaleCount extends EntityPathBase<SaleCount> {

    private static final long serialVersionUID = -1857698566L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSaleCount saleCount = new QSaleCount("saleCount");

    public final NumberPath<Long> count = createNumber("count", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> price = createNumber("price", Long.class);

    public final QProduct productId;

    public QSaleCount(String variable) {
        this(SaleCount.class, forVariable(variable), INITS);
    }

    public QSaleCount(Path<? extends SaleCount> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSaleCount(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSaleCount(PathMetadata metadata, PathInits inits) {
        this(SaleCount.class, metadata, inits);
    }

    public QSaleCount(Class<? extends SaleCount> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.productId = inits.isInitialized("productId") ? new QProduct(forProperty("productId"), inits.get("productId")) : null;
    }

}

