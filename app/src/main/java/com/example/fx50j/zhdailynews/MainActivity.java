package com.example.fx50j.zhdailynews;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.fx50j.zhdailynews.Adapter.ViewPagerAdapter;
import com.example.fx50j.zhdailynews.Fragment.Fragment_Hot;
import com.example.fx50j.zhdailynews.Fragment.Fragment_Latest;
import com.example.fx50j.zhdailynews.Fragment.Fragment_Section;
import com.example.fx50j.zhdailynews.Fragment.Fragment_Theme;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Fragment> fragments = new ArrayList<Fragment>();
    private String[] mtittles = {
            "最新","热门","主题","栏目"
    };
    private ViewPager mviewpager;
    private TabLayout mtabs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        fragments.add(new Fragment_Latest());
        fragments.add(new Fragment_Hot());
        fragments.add(new Fragment_Theme());
        fragments.add(new Fragment_Section());



        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),fragments,mtittles);
        mviewpager = (ViewPager) findViewById(R.id.viewpager);
        mviewpager.setAdapter(viewPagerAdapter);

        mtabs = (TabLayout) findViewById(R.id.tabs);

        //设置tab显示模式  MODE_FUXED全部显示（少）  MODE_SCROLLABLE可滑动显示（多）
        mtabs.setTabMode(TabLayout.MODE_FIXED);
        mtabs.setupWithViewPager(mviewpager);
    }
}
