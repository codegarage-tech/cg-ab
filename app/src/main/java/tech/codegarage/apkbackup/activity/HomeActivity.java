package tech.codegarage.apkbackup.activity;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;

import com.yalantis.colormatchtabs.colormatchtabs.adapter.ColorTabAdapter;
import com.yalantis.colormatchtabs.colormatchtabs.colortabs.ColorMatchTabLayout;
import com.yalantis.colormatchtabs.colormatchtabs.listeners.ColorTabLayoutOnPageChangeListener;
import com.yalantis.colormatchtabs.colormatchtabs.listeners.OnColorTabSelectedListener;
import com.yalantis.colormatchtabs.colormatchtabs.model.ColorTab;
import com.yalantis.jellytoolbar.listener.JellyListener;
import com.yalantis.jellytoolbar.widget.JellyToolbar;

import tech.codegarage.apkbackup.R;
import tech.codegarage.apkbackup.adapter.ColorTabsAdapter;
import tech.codegarage.apkbackup.base.BaseActivity;

import static tech.codegarage.apkbackup.util.AppUtil.getStatusBarHeight;

/**
 * @author Md. Rashadul Alam
 * Email: rashed.droid@gmail.com
 */
public class HomeActivity extends BaseActivity {

    //Jelly toolbar
    private JellyToolbar toolbar;
    private AppCompatEditText editText;
    private JellyListener jellyListener;
    private static final String TEXT_KEY = "text";

    //Color match tab layout
    private String[] colorsArray, textsArray;
    private TypedArray iconsArray;
    private ColorMatchTabLayout colorMatchTabLayoutBackup;

    //ViewPager adapter
    private ViewPager viewPagerBackup;
    private ColorTabsAdapter colorTabsAdapter;

//    @Override
//    public String initActivityTag() {
//        return HomeActivity.class.getSimpleName();
//    }

    @Override
    public int initActivityLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initIntentData(Bundle savedInstanceState, Intent intent) {
    }

    @Override
    public void initActivityViews() {
        toolbar = (JellyToolbar) findViewById(R.id.toolbar);
        colorMatchTabLayoutBackup = (ColorMatchTabLayout) findViewById(R.id.color_match_tab_layout_backup);
        viewPagerBackup = (ViewPager) findViewById(R.id.view_pager_backup);
    }

    @Override
    public void initActivityViewsData(Bundle savedInstanceState) {
        setActionBar();
        initColorMatchTabLayout();
        initViewPager();
    }

    @Override
    public void initActivityActions(Bundle savedInstanceState) {
    }

    @Override
    public void initActivityOnResult(int requestCode, int resultCode, Intent data) {

    }

    @Override
    public void initActivityBackPress() {
        if (toolbar.isExpanded()) {
            toolbar.collapse();
        } else {
            finish();
        }
    }

    @Override
    public void initActivityPermissionResult(int requestCode, String[] permissions, int[] grantResults) {

    }

    /*************************
     * Jelly toolbar methods *
     *************************/
    private void setActionBar() {
        jellyListener = new JellyListener() {
            @Override
            public void onCancelIconClicked() {
                if (TextUtils.isEmpty(editText.getText())) {
                    toolbar.collapse();
                } else {
                    editText.getText().clear();
                }
            }
        };
        toolbar.getToolbar().setNavigationIcon(R.drawable.ic_menu);
        toolbar.setJellyListener(jellyListener);
        toolbar.getToolbar().setPadding(0, getStatusBarHeight(getActivity()), 0, 0);

        editText = (AppCompatEditText) LayoutInflater.from(this).inflate(R.layout.toolbar_edittext, null);
        editText.setBackgroundResource(R.color.colorTransparent);
        toolbar.setContentView(editText);

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        toolbar.getToolbar().setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

//        setSupportActionBar(toolbar.getToolbar());
//        getSupportActionBar().setHomeButtonEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(TEXT_KEY, editText.getText().toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        editText.setText(savedInstanceState.getString(TEXT_KEY));
        editText.setSelection(editText.getText().length());
    }

    /**********************************
     * Color match tab layout methods *
     **********************************/
    private void initColorMatchTabLayout() {
        colorsArray = getResources().getStringArray(R.array.colors);
        textsArray = getResources().getStringArray(R.array.texts);
        iconsArray = getResources().obtainTypedArray(R.array.icons);

        for (int i = 0; i < colorsArray.length; i++) {
            String tabName = textsArray[i];
            int selectedColor = Color.parseColor(colorsArray[i]);
            Drawable icon = iconsArray.getDrawable(i);
            colorMatchTabLayoutBackup.addTab(ColorTabAdapter.createColorTab(colorMatchTabLayoutBackup, tabName, selectedColor, icon));
        }
    }

    private void initViewPager() {
        colorTabsAdapter = new ColorTabsAdapter(getSupportFragmentManager(), colorMatchTabLayoutBackup.count());
        viewPagerBackup.setAdapter(colorTabsAdapter);
        viewPagerBackup.addOnPageChangeListener(new ColorTabLayoutOnPageChangeListener(colorMatchTabLayoutBackup));
        viewPagerBackup.setBackgroundColor(ContextCompat.getColor(HomeActivity.this, R.color.colorGreen));
        viewPagerBackup.getBackground().setAlpha(128);

        colorMatchTabLayoutBackup.addOnColorTabSelectedListener(new OnColorTabSelectedListener() {
            @Override
            public void onSelectedTab(ColorTab tab) {
                viewPagerBackup.setCurrentItem((tab !=null)?tab.getPosition():0);
                viewPagerBackup.setBackgroundColor((tab !=null)?tab.getSelectedColor():ContextCompat.getColor(HomeActivity.this, R.color.colorPrimary));
                viewPagerBackup.getBackground().setAlpha(128);

                //Change toolbar color
//                toolbar.toolbarTitle.setTextColor(tab?.selectedColor ?: ContextCompat.getColor(this@MainActivity, R.color.colorPrimary))
            }

            @Override
            public void onUnselectedTab(ColorTab tab) {

            }
        });
    }
}
