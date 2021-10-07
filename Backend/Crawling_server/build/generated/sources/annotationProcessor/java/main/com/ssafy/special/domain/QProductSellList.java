package com.ssafy.special.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProductSellList is a Querydsl query type for ProductSellList
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QProductSellList extends EntityPathBase<ProductSellList> {

    private static final long serialVersionUID = 718380945L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProductSellList productSellList = new QProductSellList("productSellList");

    public final NumberPath<Long> aid = createNumber("aid", Long.class);

    public final StringPath content = createString("content");

    public final DateTimePath<java.time.LocalDateTime> createDate = createDateTime("createDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> cycle = createNumber("cycle", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath img = createString("img");

    public final StringPath link = createString("link");

    public final StringPath location = createString("location");

    public final StringPath market = createString("market");

    public final NumberPath<Long> price = createNumber("price", Long.class);

    public final QProduct productId;

    public final ListPath<ProductSellArticleSimiler, QProductSellArticleSimiler> productSellArticleSimilerA = this.<ProductSellArticleSimiler, QProductSellArticleSimiler>createList("productSellArticleSimilerA", ProductSellArticleSimiler.class, QProductSellArticleSimiler.class, PathInits.DIRECT2);

    public final ListPath<ProductSellArticleSimiler, QProductSellArticleSimiler> productSellArticleSimilerB = this.<ProductSellArticleSimiler, QProductSellArticleSimiler>createList("productSellArticleSimilerB", ProductSellArticleSimiler.class, QProductSellArticleSimiler.class, PathInits.DIRECT2);

    public final StringPath title = createString("title");

    public final NumberPath<Double> x = createNumber("x", Double.class);

    public final NumberPath<Double> y = createNumber("y", Double.class);

    public QProductSellList(String variable) {
        this(ProductSellList.class, forVariable(variable), INITS);
    }

    public QProductSellList(Path<? extends ProductSellList> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProductSellList(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProductSellList(PathMetadata metadata, PathInits inits) {
        this(ProductSellList.class, metadata, inits);
    }

    public QProductSellList(Class<? extends ProductSellList> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.productId = inits.isInitialized("productId") ? new QProduct(forProperty("productId"), inits.get("productId")) : null;
    }

}

