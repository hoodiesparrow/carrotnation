package com.ssafy.special.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QQueryExceptionKeyword is a Querydsl query type for QueryExceptionKeyword
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QQueryExceptionKeyword extends EntityPathBase<QueryExceptionKeyword> {

    private static final long serialVersionUID = -239189900L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QQueryExceptionKeyword queryExceptionKeyword = new QQueryExceptionKeyword("queryExceptionKeyword");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath keyword = createString("keyword");

    public final QProductQuery query;

    public QQueryExceptionKeyword(String variable) {
        this(QueryExceptionKeyword.class, forVariable(variable), INITS);
    }

    public QQueryExceptionKeyword(Path<? extends QueryExceptionKeyword> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QQueryExceptionKeyword(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QQueryExceptionKeyword(PathMetadata metadata, PathInits inits) {
        this(QueryExceptionKeyword.class, metadata, inits);
    }

    public QQueryExceptionKeyword(Class<? extends QueryExceptionKeyword> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.query = inits.isInitialized("query") ? new QProductQuery(forProperty("query")) : null;
    }

}

