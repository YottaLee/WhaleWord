package com.silence.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import android.os.Environment;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.silence.fragment.CalendarFragment;
import com.silence.fragment.DicFragment;
import com.silence.fragment.MainFragment;
import com.silence.fragment.OriginalFragment;
import com.silence.fragment.TabContentFragment;
import com.silence.studyplan.PickerView;
import com.silence.utils.Const;
import com.silence.word.R;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private long exitTime = 0;
    private TabLayout mTabTl;
    private ViewPager mContentVp;

    private List<String> tabIndicators;

    private List<Fragment> tabFragments;
    private ContentPagerAdapter contentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_navigation);

        mTabTl = (TabLayout) findViewById(R.id.tl_tab);
        mContentVp = (ViewPager) findViewById(R.id.vp_content);

        initContent();
        initTab();

    }

    @Override
    public void onClick(View v) {
//        Intent intent = new Intent();
//        switch (v.getId()) {
//            case R.id.iv_nmet:
//                intent.setClass(this, UnitListActivity.class);
//                intent.putExtra(Const.META_KEY, Const.WORDS_NMET);
//                break;
//            case R.id.iv_cet4:
//                intent.setClass(this, UnitListActivity.class);
//                intent.putExtra(Const.META_KEY, Const.WORDS_CET4);
//                break;
//            case R.id.iv_cet6:
//                intent.setClass(this, UnitListActivity.class);
//                intent.putExtra(Const.META_KEY, Const.WORDS_CET6);
//                break;
//            case R.id.iv_ietsl:
//                intent.setClass(this, UnitListActivity.class);
//                intent.putExtra(Const.META_KEY, Const.WORDS_IETSL);
//                break;
//            case R.id.iv_gre:
//                intent.setClass(this, UnitListActivity.class);
//                intent.putExtra(Const.META_KEY, Const.WORDS_GRE);
//                break;
//        }
//        startActivity(intent);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(this, R.string.exit_hint, Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void initTab() {
        mTabTl.setTabMode(TabLayout.MODE_FIXED);
        mTabTl.setSelectedTabIndicatorHeight(0);
        ViewCompat.setElevation(mTabTl, 10);
        mTabTl.setupWithViewPager(mContentVp);
        int[] mitmap = {R.drawable.selector_menu_home,R.drawable.selector_menu_dic,R.drawable.selector_menu_calendar};
        for (int i = 0; i < tabIndicators.size(); i++) {
            TabLayout.Tab itemTab = mTabTl.getTabAt(i);
            if (itemTab != null) {
                itemTab.setCustomView(R.layout.item_tab_layout_custom);
                TextView itemTv = (TextView) itemTab.getCustomView().findViewById(R.id.tv_menu_item);
                itemTv.setText(tabIndicators.get(i));
                ImageView image = (ImageView) itemTab.getCustomView().findViewById(R.id.tv_menu_icon);
                image.setImageDrawable(getResources().getDrawable(mitmap[i]));
            }
        }
        mTabTl.getTabAt(0).getCustomView().setSelected(true);
    }

    private void initContent() {
        tabIndicators = new ArrayList<>();
        tabIndicators.add("主页");
        tabIndicators.add("词库");
        tabIndicators.add("日历");
        tabFragments = new ArrayList<>();
        tabFragments.add(MainFragment.newInstance());
        tabFragments.add(DicFragment.newInstance());
        tabFragments.add(CalendarFragment.newInstance());
        contentAdapter = new ContentPagerAdapter(getSupportFragmentManager());
        mContentVp.setAdapter(contentAdapter);
    }

    class ContentPagerAdapter extends FragmentPagerAdapter {

        public ContentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return tabFragments.get(position);
        }

        @Override
        public int getCount() {
            return tabIndicators.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabIndicators.get(position);
        }
    }

}