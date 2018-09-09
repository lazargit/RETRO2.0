package com.shamildev.retro.data.entity.mapper;

import com.shamildev.retro.data.entity.EntityMapper;
import com.shamildev.retro.data.entity.mapper.error.MappingError;
import com.shamildev.retro.data.entity.tmdb.ProductionCompanyEntity;
import com.shamildev.retro.domain.models.ProductionCompany;

import javax.inject.Inject;

import dagger.Reusable;

/**
 * Maps {@link ProductionCompanyEntity} to {@link ProductionCompany} .
 */
@Reusable
public final class ProductionCompanyEntityMapper implements EntityMapper<ProductionCompanyEntity, ProductionCompany> {


    @Inject
    ProductionCompanyEntityMapper() {

    }


    @Override
    public ProductionCompany map(ProductionCompanyEntity entity) throws MappingError {


        try {
            return ProductionCompany.builder()
                    .id(entity.getId())
                    .name(entity.getName())
                    .build();
        } catch (NullPointerException e) {
            throw MappingError.getError(e.getMessage(), this.getClass());

        }
    }

    @Override
    public ProductionCompanyEntity map(ProductionCompany model) {
        return null;
    }
}
