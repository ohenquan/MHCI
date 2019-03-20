package ugs.mobilehci.crashprevention.di.component;


import dagger.Component;
import ugs.mobilehci.crashprevention.di.PerActivity;
import ugs.mobilehci.crashprevention.di.module.ActivityModule;
import ugs.mobilehci.crashprevention.ui.main.CameraActivity;
import ugs.mobilehci.crashprevention.ui.main.MainActivity;
import ugs.mobilehci.crashprevention.ui.settings.SettingsActivity;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity activity);

    void inject (CameraActivity activity);

    void inject(SettingsActivity activity);

}