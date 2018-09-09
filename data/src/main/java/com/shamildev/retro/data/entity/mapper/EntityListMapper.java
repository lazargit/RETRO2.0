

package com.shamildev.retro.data.entity.mapper;




import com.shamildev.retro.data.entity.Entity;
import com.shamildev.retro.data.entity.EntityMapper;
import com.shamildev.retro.data.entity.mapper.error.MappingError;
import com.shamildev.retro.domain.core.DomainObject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.Reusable;

/**
 * Maps lists of entities.
 */
@Reusable
public final class EntityListMapper {

    @Inject
    EntityListMapper() {
    }

    <K extends Entity, V extends DomainObject> List<V> mapToV(EntityMapper<K, V> entityMapper,
                                                              List<K> kList) throws MappingError {
        List<V> vList = new ArrayList<>(kList.size());
        for (K k : kList) {
            vList.add(entityMapper.map(k));
        }
        return vList;
    }

    <K extends Entity, V extends DomainObject> List<K> mapToK(EntityMapper<K, V> entityMapper,
                                                              List<V> vList) {
        List<K> kList = new ArrayList<>(vList.size());
        for (V v : vList) {
            kList.add(entityMapper.map(v));
        }
        return kList;
    }
}
