package com.zbq.beads;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.readystatesoftware.systembartint.SystemBarTintManager;


public class MainActivity extends ActionBarActivity implements NavigationDrawerCallbacks , IViewFactory {

    private Toolbar mToolbar;
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private ViewContainer viewContainer;
    /**
     * 首次创建页面的临时view
     */
    public BaseView newView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        mToolbar.setTitle(R.string.app_name);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(R.color.myPrimaryColor);//通知栏所需颜色
        }
        viewContainer = (ViewContainer)findViewById(R.id.container);
        viewContainer.setViewFactory(this);
        mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager().findFragmentById(R.id.fragment_drawer);
        mNavigationDrawerFragment.setup(R.id.fragment_drawer, (DrawerLayout) findViewById(R.id.drawer), mToolbar);
        onNavigationDrawerItemSelected(0);
    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, Util.getStatusBarHeight(this), 0, 0);
        mToolbar.setLayoutParams(lp);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        if(viewContainer!=null){
            viewContainer.flipToView(position);
            NavigationItem item = mNavigationDrawerFragment.getNavigationItem(position);
            mToolbar.setTitle(item.getText());
        }
    }


    @Override
    public void onBackPressed() {
        if (mNavigationDrawerFragment.isDrawerOpen())
            mNavigationDrawerFragment.closeDrawer();
        else
            super.onBackPressed();
    }

    @Override
    public View createView(int index) {

        switch (index){
            case 1:
                newView = new Pair(this);
                break;
            case 2:
                newView = new Reminder(this);
                break;
            case 3:
                newView = new Language(this);
                break;
            case 4:
                newView = new TimeSet(this);
                break;
            case 5:
                newView = new Status(this);
                break;
            default:
                newView = new Main(this);
                break;
        }
        return newView.getView();
    }
}
