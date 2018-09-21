package com.shamildev.retro.ui.search.fragment.model;

import com.shamildev.retro.ui.common.model.BaseModel;
import com.shamildev.retro.ui.register.fragment.presenter.RegisterPresenter;
import com.shamildev.retro.ui.search.fragment.presenter.SearchPresenter;


/**
 * Created by Shamil Lazar.
 */

public abstract class SearchModel extends BaseModel<SearchPresenter> {

    public abstract void search();

}
