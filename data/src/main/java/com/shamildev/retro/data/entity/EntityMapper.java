package com.shamildev.retro.data.entity;


import com.shamildev.retro.data.entity.mapper.error.MappingError;
import com.shamildev.retro.domain.core.DomainObject;



/**
 * Maps entity K to V and vice versa.
 *
 * @param <K> the type of the {@link Entity}
 * @param <V> the type of the {@link DomainObject}
 */
public interface EntityMapper<K extends Entity, V extends DomainObject> {
    V map(K k) throws MappingError;
    K map(V v);
}
