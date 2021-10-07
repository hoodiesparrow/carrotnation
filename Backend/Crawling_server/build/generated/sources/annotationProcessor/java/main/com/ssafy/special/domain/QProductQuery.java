package com.ssafy.special.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QProductQuery is a Querydsl query type for ProductQuery
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QProductQuery extends EntityPathBase<ProductQuery> {

    private static final long serialVersionUID = 1723640807L;

    public static final QProductQuery productQuery = new QProductQuery("productQuery");

    public final StringPath query = createString("query");

    public QProductQuery(String variable) {
        super(ProductQuery.class, forVariable(variable));
    }

    public QProductQuery(Path<? extends ProductQuery> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProductQuery(PathMetadata metadata) {
        super(ProductQuery.class, metadata);
    }

}

