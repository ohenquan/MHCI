package ugs.mobilehci.crashprevention.di.component;


import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import ugs.mobilehci.crashprevention.MvpApplication;
import ugs.mobilehci.crashprevention.di.ApplicationContext;
import ugs.mobilehci.crashprevention.di.module.ApplicationModule;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {


    void inject(MvpApplication app);

    @ApplicationContext
    Context context();


    Application application();

}