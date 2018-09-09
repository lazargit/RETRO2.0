package com.shamildev.retro.cache.realm.mapper;
import com.shamildev.retro.domain.core.DomainObject;

import io.realm.RealmObject;


public interface RealmMapper<K extends DomainObject, V extends RealmObject> {

    V map(K k);

    K map(V v);
}
