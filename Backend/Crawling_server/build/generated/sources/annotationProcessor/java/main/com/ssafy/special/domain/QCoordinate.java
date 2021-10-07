package com.ssafy.special.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QCoordinate is a Querydsl query type for Coordinate
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCoordinate extends EntityPathBase<Coordinate> {

    private static final long serialVersionUID = -1914281018L;

    public static final QCoordinate coordinate = new QCoordinate("coordinate");

    public final StringPath address = createString("address");

    public final NumberPath<Long> code = createNumber("code", Long.class);

    public final NumberPath<Integer> count = createNumber("count", Integer.class);

    public final StringPath dong = createString("dong");

    public final StringPath gugun = createString("gugun");

    public final StringPath lat = createString("lat");

    public final StringPath lon = createString("lon");

    public final StringPath sido = createString("sido");

    public QCoordinate(String variable) {
        super(Coordinate.class, forVariable(variable));
    }

    public QCoordinate(Path<? extends Coordinate> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCoordinate(PathMetadata metadata) {
        super(Coordinate.class, metadata);
    }

}

