package ugs.mobilehci.crashprevention.ui.main;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ugs.mobilehci.crashprevention.R;
import ugs.mobilehci.crashprevention.ui.base.BaseActivity;
import ugs.mobilehci.crashprevention.ui.custom.RoundedImageView;

public class MainActivity extends BaseActivity implements MainMvpView{

    @Inject
    MainMvpPresenter<MainMvpView> mPresenter;

    @BindView(R.id.drawer_view)
    DrawerLayout mDrawer;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.navigation_view)
    NavigationView mNavigationView;



    private RoundedImageView mProfileImageView;


    private TextView mNameTextView;

    private TextView mEmailTextView;

    private ActionBarDrawerToggle mDrawerToggle;

    private Button CameraButton;


    Button btnFlashLight, btnBlinkFlashLight;
    private static final int CAMERA_REQUEST = 123;
    boolean hasCameraFlash = false;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        setUp();

        CameraButton = findViewById(R.id.camera);

        onCameraButtonClick();

        //flashlight
        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.CAMERA}, CAMERA_REQUEST);

        hasCameraFlash = getPackageManager().
                hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);

//        btnFlashLight = findViewById(R.id.btnFlashLightToggle);
//        btnBlinkFlashLight = findViewById(R.id.btnBlinkFlashLight);

//        btnFlashLight.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (hasCameraFlash) {
//                    if (btnFlashLight.getText().toString().contains("ON")) {
//                        btnFlashLight.setText("FLASHLIGHT OFF");
//                        btnBlinkFlashLight.setText("BLINK FLASHLIGHT OFF");
//                        flashLightOff();
//                    } else {
//                        btnBlinkFlashLight.setText("BLINK FLASHLIGHT ON");
//                        btnFlashLight.setText("FLASHLIGHT ON");
//                        flashLightOn();
//                    }
//                } else {
//                    Toast.makeText(MainActivity.this, "No flash available on your device",
//                            Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

//        btnBlinkFlashLight.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                blinkFlash();
//                if(btnFlashLight.getText().toString().contains("ON"))
//                {
//                    blinkFlash();
//                }
//                else{
//                    Toast.makeText(MainActivity.this, "Press the above button first.",
//                            Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        });

    }

    private void flashLightOn() {
        CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);

        try {
            String cameraId = cameraManager.getCameraIdList()[0];
            cameraManager.setTorchMode(cameraId, true);
        } catch (CameraAccessException e) {
        }
    }

    private void flashLightOff() {
        CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            String cameraId = cameraManager.getCameraIdList()[0];
            cameraManager.setTorchMode(cameraId, false);
        } catch (CameraAccessException e) {
        }
    }

    private void blinkFlash()
    {
        CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        String myString = "0101010101";
        long blinkDelay = 50; //Delay in ms
        for (int i = 0; i < myString.length(); i++) {
            if (myString.charAt(i) == '0') {
                try {
                    String cameraId = cameraManager.getCameraIdList()[0];
                    cameraManager.setTorchMode(cameraId, true);
                } catch (CameraAccessException e) {
                }
            } else {
                try {
                    String cameraId = cameraManager.getCameraIdList()[0];
                    cameraManager.setTorchMode(cameraId, false);
                } catch (CameraAccessException e) {
                }
            }
            try {
                Thread.sleep(blinkDelay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case CAMERA_REQUEST:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    hasCameraFlash = getPackageManager().
                            hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
                } else {
                    btnFlashLight.setEnabled(false);
                    btnBlinkFlashLight.setEnabled(false);
                    Toast.makeText(MainActivity.this, "Permission Denied for the Camera", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }




    @Override
    protected void onDestroy() {
//        mPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    protected void setUp() {
        setSupportActionBar(mToolbar);

        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawer,
                mToolbar,
                R.string.open_drawer,
                R.string.close_drawer) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                hideKeyboard();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        mDrawer.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        setupNavMenu();
        mPresenter.onNavMenuCreated();
//        setupCardContainerView();
        mPresenter.onViewInitialized();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Drawable drawable = item.getIcon();
        if (drawable instanceof Animatable) {
            ((Animatable) drawable).start();
        }
        switch (item.getItemId()) {
//            case R.id.action_cut:
//                return true;
//            case R.id.action_copy:
//                return true;
//            case R.id.action_share:
//                return true;
//            case R.id.action_delete:
//                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    void setupNavMenu() {
        View headerLayout = mNavigationView.getHeaderView(0);
        mProfileImageView = headerLayout.findViewById(R.id.iv_profile_pic);
        mNameTextView = headerLayout.findViewById(R.id.tv_name);
        mEmailTextView = headerLayout.findViewById(R.id.tv_email);

        mNavigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        mDrawer.closeDrawer(GravityCompat.START);
                        switch (item.getItemId()) {
//                            case R.id.nav_item_about:
////                                mPresenter.onDrawerOptionAboutClick();
//                                return true;
//                            case R.id.nav_item_rate_us:
////                                mPresenter.onDrawerRateUsClick();
//                                return true;
//                            case R.id.nav_item_feed:
////                                mPresenter.onDrawerMyFeedClick();
//                                return true;
//                            case R.id.nav_item_logout:
////                                mPresenter.onDrawerOptionLogoutClick();
//                                return true;
                            default:
                                return false;
                        }
                    }
                });
    }

    @Override
    public void lockDrawer() {
        if (mDrawer != null)
            mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    @Override
    public void unlockDrawer() {
        if (mDrawer != null)
            mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
    }

    @Override
    public void closeNavigationDrawer() {
        if (mDrawer != null) {
            mDrawer.closeDrawer(Gravity.START);
        }
    }


//
//    //Test open camera

    private void onCameraButtonClick(){


        final DetectorActivity DA = new DetectorActivity();

        CameraButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                System.out.println("HEY ==================");
                Intent i = new Intent(getApplicationContext(),DetectorActivity.class);
                startActivity(i);
            }
        });


    }
}


