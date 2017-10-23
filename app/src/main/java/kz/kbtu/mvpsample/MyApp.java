package kz.kbtu.mvpsample;

import android.app.Application;

import kz.kbtu.mvpsample.dagger.AppModule;
import kz.kbtu.mvpsample.dagger.DaggerNetComponent;
import kz.kbtu.mvpsample.dagger.NetComponent;
import kz.kbtu.mvpsample.dagger.NetModule;

/**
 * Created by aibekkuralbaev on 23.10.17.
 */

public class MyApp extends Application {

    private NetComponent mNetComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        // Dagger%COMPONENT_NAME%
        mNetComponent = DaggerNetComponent.builder()
                // list of modules that are part of this component need to be created here too
                .appModule(new AppModule(this)) // This also corresponds to the name of your module: %component_name%Module
                .netModule(new NetModule("https://api.github.com"))
                .build();

        // If a Dagger 2 component does not have any constructor arguments for any of its modules,
        // then we can use .create() as a shortcut instead:
        //  mNetComponent = com.codepath.dagger.components.DaggerNetComponent.create();
    }

    public NetComponent getNetComponent() {
        return mNetComponent;
    }
}
