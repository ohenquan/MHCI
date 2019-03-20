package ugs.mobilehci.crashprevention.di.module;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import ugs.mobilehci.crashprevention.di.ActivityContext;
import ugs.mobilehci.crashprevention.di.PerActivity;
import ugs.mobilehci.crashprevention.ui.main.MainMvpPresenter;
import ugs.mobilehci.crashprevention.ui.main.MainMvpView;
import ugs.mobilehci.crashprevention.ui.main.MainPresenter;
import ugs.mobilehci.crashprevention.ui.settings.SettingsMvpPresenter;
import ugs.mobilehci.crashprevention.ui.settings.SettingsMvpView;
import ugs.mobilehci.crashprevention.ui.settings.SettingsPresenter;

@Module
public class ActivityModule {

    private AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return mActivity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    @PerActivity
    MainMvpPresenter<MainMvpView> provideMainPresenter(
            MainPresenter<MainMvpView> presenter) {
        return presenter;
    }


    @Provides
    LinearLayoutManager provideLinearLayoutManager(AppCompatActivity activity) {
        return new LinearLayoutManager(activity);
    }


    @Provides
    @PerActivity
    SettingsMvpPresenter<SettingsMvpView> provideSettingsPresenter(
        SettingsPresenter<SettingsMvpView> presenter){
        return presenter;
    }


}