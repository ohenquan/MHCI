package ugs.mobilehci.crashprevention.ui.settings;

import android.os.Bundle;

import butterknife.ButterKnife;
import ugs.mobilehci.crashprevention.R;
import ugs.mobilehci.crashprevention.ui.base.BaseActivity;
import ugs.mobilehci.crashprevention.ui.main.MainMvpView;

public class SettingsActivity extends BaseActivity implements SettingsMvpView {



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));


    }



    @Override
    protected void setUp(){

    }


}
