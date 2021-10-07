package com.ssafy.special.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QExceptionKeyword is a Querydsl query type for ExceptionKeyword
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QExceptionKeyword extends EntityPathBase<ExceptionKeyword> {

    private static final long serialVersionUID = 842480232L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QExceptionKeyword exceptionKeyword = new QExceptionKeyword("exceptionKeyword");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath keyword = createString("keyword");

    public final StringPath market = createString("market");

    public final QProduct productId;

    public QExceptionKeyword(String variable) {
        this(ExceptionKeyword.class, forVariable(variable), INITS);
    }

    public QExceptionKeyword(Path<? extends ExceptionKeyword> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QExceptionKeyword(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QExceptionKeyword(PathMetadata metadata, PathInits inits) {
        this(ExceptionKeyword.class, metadata, inits);
    }

    public QExceptionKeyword(Class<? extends ExceptionKeyword> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.productId = inits.isInitialized("productId") ? new QProduct(forProperty("productId"), inits.get("productId")) : null;
    }

}

