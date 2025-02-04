package com.shamildev.retro.ui.search.fragment.model;

import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.domain.core.AppConfig;
import com.shamildev.retro.domain.core.usecase.UseCaseHandler;
import com.shamildev.retro.domain.interactor.usecases.base.USECASE_authUser;
import com.shamildev.retro.domain.models.AppUser;

import javax.inject.Inject;

/**
 * Created by Shamil Lazar.
 */
@PerFragment
public class SearchModelImpl extends SearchModel {


    @Inject
    protected AppConfig appConfig;
    @Inject
    protected AppUser appUser;

    private final USECASE_authUser usecase_authUser;
    private final UseCaseHandler useCaseHandler;


    @Inject
    public SearchModelImpl(
            UseCaseHandler useCaseHandler,
            USECASE_authUser usecase_authUser) {
        this.useCaseHandler = useCaseHandler;
        this.usecase_authUser = usecase_authUser;

    }

    @Override
    public void search() {

    }
}
