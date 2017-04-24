package uca.apps.isi.UbiUCA;


import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;

public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();

        initRealm();
    }
    private void initRealm() {
        System.out.println("INIT REALM");
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("UBIUCA.realm")
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
    }
}
