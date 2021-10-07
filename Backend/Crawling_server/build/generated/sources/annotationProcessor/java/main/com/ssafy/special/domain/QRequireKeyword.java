package com.ssafy.special.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRequireKeyword is a Querydsl query type for RequireKeyword
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QRequireKeyword extends EntityPathBase<RequireKeyword> {

    private static final long serialVersionUID = 313614354L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRequireKeyword requireKeyword = new QRequireKeyword("requireKeyword");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath keyword = createString("keyword");

    public final StringPath market = createString("market");

    public final QProduct productId;

    public QRequireKeyword(String variable) {
        this(RequireKeyword.class, forVariable(variable), INITS);
    }

    public QRequireKeyword(Path<? extends RequireKeyword> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRequireKeyword(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRequireKeyword(PathMetadata metadata, PathInits inits) {
        this(RequireKeyword.class, metadata, inits);
    }

    public QRequireKeyword(Class<? extends RequireKeyword> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.productId = inits.isInitialized("productId") ? new QProduct(forProperty("productId"), inits.get("productId")) : null;
    }

}

