package com.ssafy.special.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDatePrice is a Querydsl query type for DatePrice
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDatePrice extends EntityPathBase<DatePrice> {

    private static final long serialVersionUID = -1993507283L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDatePrice datePrice = new QDatePrice("datePrice");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DatePath<java.time.LocalDate> pdate = createDate("pdate", java.time.LocalDate.class);

    public final NumberPath<Long> price = createNumber("price", Long.class);

    public final QProduct productId;

    public QDatePrice(String variable) {
        this(DatePrice.class, forVariable(variable), INITS);
    }

    public QDatePrice(Path<? extends DatePrice> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDatePrice(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDatePrice(PathMetadata metadata, PathInits inits) {
        this(DatePrice.class, metadata, inits);
    }

    public QDatePrice(Class<? extends DatePrice> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.productId = inits.isInitialized("productId") ? new QProduct(forProperty("productId"), inits.get("productId")) : null;
    }

}

