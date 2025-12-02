package com.infonal.productservice.core.cqrs;

public interface QueryHandler <Q extends Query<R>, R>{
    R handle(Q query);
}
