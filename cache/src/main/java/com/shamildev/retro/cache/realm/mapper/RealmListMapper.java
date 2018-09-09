package com.shamildev.retro.cache.realm.mapper;






import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.Reusable;
import io.reactivex.Observable;
import io.realm.RealmList;


/**
 * Maps lists of entities.
 */

@Reusable
final class RealmListMapper {

    @Inject
    RealmListMapper() {
    }


    public static RealmList listToRealmList(List<String> list) {
        return Observable.fromArray(new RealmList<>())
                .flatMap(item -> Observable.fromArray(list))
                .collect(RealmList::new, RealmList::addAll).blockingGet();

    }


    public static <V extends Number> List<V> mapToList(RealmList<V> list) {
        
        List<V> vList = new ArrayList<>();
        for (V id : list) {
            vList.add(id);
        }
        return vList;
        
    }

    public static <V extends String> List<V> mapToStringList(RealmList<V> list) {
        
        List<V> vList = new ArrayList<>();
        for (V id : list) {
            vList.add(id);
        }
        return vList;
   }


}
