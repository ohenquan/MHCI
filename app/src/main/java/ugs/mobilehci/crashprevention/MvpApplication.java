package ugs.mobilehci.crashprevention;

import android.app.Application;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.interceptors.HttpLoggingInterceptor;

import ugs.mobilehci.crashprevention.di.component.ApplicationComponent;
import ugs.mobilehci.crashprevention.di.component.DaggerApplicationComponent;
import ugs.mobilehci.crashprevention.di.module.ApplicationModule;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class MvpApplication extends Application {


    private ApplicationComponent mApplicationComponent;


    @Override
    public void onCreate() {
        super.onCreate();

        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();

        mApplicationComponent.inject(this);

    }


    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }

    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }
}
