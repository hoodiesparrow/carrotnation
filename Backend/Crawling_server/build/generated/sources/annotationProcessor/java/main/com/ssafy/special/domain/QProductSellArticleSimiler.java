package com.ssafy.special.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProductSellArticleSimiler is a Querydsl query type for ProductSellArticleSimiler
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QProductSellArticleSimiler extends EntityPathBase<ProductSellArticleSimiler> {

    private static final long serialVersionUID = 724655044L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProductSellArticleSimiler productSellArticleSimiler = new QProductSellArticleSimiler("productSellArticleSimiler");

    public final QProductSellList articleA;

    public final QProductSellList articleB;

    public final NumberPath<Long> cycle = createNumber("cycle", Long.class);

    public final NumberPath<Double> similarity = createNumber("similarity", Double.class);

    public QProductSellArticleSimiler(String variable) {
        this(ProductSellArticleSimiler.class, forVariable(variable), INITS);
    }

    public QProductSellArticleSimiler(Path<? extends ProductSellArticleSimiler> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProductSellArticleSimiler(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProductSellArticleSimiler(PathMetadata metadata, PathInits inits) {
        this(ProductSellArticleSimiler.class, metadata, inits);
    }

    public QProductSellArticleSimiler(Class<? extends ProductSellArticleSimiler> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.articleA = inits.isInitialized("articleA") ? new QProductSellList(forProperty("articleA"), inits.get("articleA")) : null;
        this.articleB = inits.isInitialized("articleB") ? new QProductSellList(forProperty("articleB"), inits.get("articleB")) : null;
    }

}

