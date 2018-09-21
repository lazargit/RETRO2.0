package com.shamildev.retro.di.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;
import javax.inject.Singleton;

/**
 * Created by Schamil Lazar
 */

@Scope
@Singleton
@Retention(RetentionPolicy.CLASS)
public @interface ApplicationScope {
}
