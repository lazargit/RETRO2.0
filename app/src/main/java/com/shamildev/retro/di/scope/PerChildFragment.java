package com.shamildev.retro.di.scope;

/**
 * Created by Schamil Lazar.
 */

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;


@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerChildFragment {
}
