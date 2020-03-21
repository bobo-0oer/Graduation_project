package com.example.graduatioproject_android.tools;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.graduatioproject_android.FragmentControl.FragmentControl;
import com.example.graduatioproject_android.FragmentHome.FragmentHome;
import com.example.graduatioproject_android.FragmentMy.FragmentMy;
import com.example.graduatioproject_android.FragmentOutdoor.FragmentOutdoor;
import com.example.graduatioproject_android.R;

import static com.example.graduatioproject_android.tools.GlobalVariable.FRAGMENTSELECT;
import static com.example.graduatioproject_android.tools.GlobalVariable.ISVISIBLE;
import static com.example.graduatioproject_android.tools.GlobalVariable.THEMEPICTURE;

import java.util.ArrayList;
import java.util.List;

public class FragmentManager extends FragmentActivity {

    private TextView homeTv=null;
    private TextView controlTv=null;
    private TextView outdoorTv=null;
    private TextView myTv=null;
    private ViewPager myViewpager=null;
    private MyListener myListener=null;
    private LinearLayout LayoutAll =null;
    private long mPressedTime = 0;

    private FragmentTransaction fragmentTransaction;
    private List<Fragment> fragmentList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_manager);
        getWindow().setStatusBarColor(0xff4f4f4f);

        homeTv=(TextView) findViewById(R.id.homeTV);
        controlTv=(TextView) findViewById(R.id.controlTV);
        outdoorTv=(TextView) findViewById(R.id.outdoorTV);
        myTv=(TextView) findViewById(R.id.myTV);
        myViewpager=(ViewPager)findViewById(R.id.myViewpager);
        LayoutAll=(LinearLayout)findViewById(R.id.LayoutAll);

        ISVISIBLE=true;
        myListener=new MyListener();
        homeTv.setOnClickListener(myListener);
        controlTv.setOnClickListener(myListener);
        outdoorTv.setOnClickListener(myListener);
        myTv.setOnClickListener(myListener);
        ThemeSet(THEMEPICTURE);//主题设置


        // 获取片段管理器
        androidx.fragment.app.FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        fragmentList = new ArrayList<Fragment>();
        Fragment fragment1 = new FragmentHome();
        Fragment fragment2 = new FragmentControl();
        Fragment fragment3 = new FragmentOutdoor();
        Fragment fragment4 = new FragmentMy();
        fragmentList.add(fragment1);
        fragmentList.add(fragment2);
        fragmentList.add(fragment3);
        fragmentList.add(fragment4);

        myViewpager.setAdapter(new MyFragmentPagerAdapter(fragmentManager,fragmentList));//设置适配器

        /**
         * 返回时设置fragment的不同选中状态
         * */
        if(FRAGMENTSELECT==0){
            homeTv.setSelected(true);
        }if(FRAGMENTSELECT==1){
            controlTv.setSelected(true);
        }if(FRAGMENTSELECT==2){
            outdoorTv.setSelected(true);
        }if(FRAGMENTSELECT==3){
            myTv.setSelected(true);
        }

        myViewpager.setCurrentItem(FRAGMENTSELECT, true);

        /**
         * Viewpager的事件监听
         * 滑动Viewpager时改变底部按钮
         * */
        myViewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }
            @Override
            public void onPageSelected(int i) {
                switch (i){
                    case 0:
                        homeTv.setSelected(true);
                        controlTv.setSelected(false);
                        outdoorTv.setSelected(false);
                        myTv.setSelected(false);
                        FRAGMENTSELECT=0;
                        break;
                    case 1:
                        homeTv.setSelected(false);
                        controlTv.setSelected(true);
                        outdoorTv.setSelected(false);
                        myTv.setSelected(false);
                        FRAGMENTSELECT=1;
                        break;
                    case 2:
                        homeTv.setSelected(false);
                        controlTv.setSelected(false);
                        outdoorTv.setSelected(true);
                        myTv.setSelected(false);
                        FRAGMENTSELECT=2;
                        break;
                    case 3:
                        homeTv.setSelected(false);
                        controlTv.setSelected(false);
                        outdoorTv.setSelected(false);
                        myTv.setSelected(true);
                        FRAGMENTSELECT=3;
                        break;
                    default:
                        break;
                }
            }
            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });
    }

    /**
     * 按钮事件监听
     * */
    private class MyListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case (R.id.homeTV):
                    setSelected();
                    FRAGMENTSELECT=0;
                    homeTv.setSelected(true);
                    myViewpager.setCurrentItem(0, true);
                    break;
                case(R.id.controlTV):
                    setSelected();
                    FRAGMENTSELECT=1;
                    controlTv.setSelected(true);
                    myViewpager.setCurrentItem(1, true);
                    break;
                case (R.id.outdoorTV):
                    setSelected();
                    FRAGMENTSELECT=2;
                    outdoorTv.setSelected(true);
                    myViewpager.setCurrentItem(2, true);
                    break;
                case(R.id.myTV):
                    setSelected();
                    FRAGMENTSELECT=3;
                    myTv.setSelected(true);
                    myViewpager.setCurrentItem(3, true);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 设置适配器
     * */
    private class MyFragmentPagerAdapter extends FragmentPagerAdapter {
        List<Fragment> list;
        public MyFragmentPagerAdapter(androidx.fragment.app.FragmentManager fragmentManager, List<Fragment> fragmentList) {
            super(fragmentManager);
            this.list = fragmentList;
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }
        @Override
        public int getCount() {
            return list.size();
        }
    }

    /**
     * 重置所有TextView未选中状态
     * */
    private void setSelected(){
        homeTv.setSelected(false);
        controlTv.setSelected(false);
        outdoorTv.setSelected(false);
        myTv.setSelected(false);
    }

    /**
     * 双击退出
     * */
    @Override
    public void onBackPressed() {
        long mNowTime = System.currentTimeMillis();
        if((mNowTime - mPressedTime) > 2000){
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            mPressedTime = mNowTime;
        }
        else{
            this.finish();
            System.exit(0);
        }
    }

    /**
     * 主题设置函数
     * 设置主题,setBackgroundResource()参数类型为int，无法改变，所以应该写成函数在Theme中调用
     * 设置全局变量，进入时设置默认值进行加载，在更改主题的Activity中进行全局变量的更换，实现
     * 主题更换功能
     *
     * */
    private void ThemeSet(int ThemeNumber){
        if(ThemeNumber==100){
            LayoutAll.setBackgroundResource(R.drawable.theme_default);
            LayoutAll.getBackground().setAlpha(100);
        }else if(ThemeNumber==0){
            LayoutAll.setBackgroundResource(R.drawable.theme_select0);
            LayoutAll.getBackground().setAlpha(100);
        }else if(ThemeNumber==1){
            LayoutAll.setBackgroundResource(R.drawable.theme_select1);
            LayoutAll.getBackground().setAlpha(100);
        }else if(ThemeNumber==2){
            LayoutAll.setBackgroundResource(R.drawable.theme_select2);
            LayoutAll.getBackground().setAlpha(100);
        }else if(ThemeNumber==3){
            LayoutAll.setBackgroundResource(R.drawable.theme_select3);
            LayoutAll.getBackground().setAlpha(100);
        }else if(ThemeNumber==4){
            LayoutAll.setBackgroundResource(R.drawable.theme_select4);
            LayoutAll.getBackground().setAlpha(100);
        }else if(ThemeNumber==5){
            LayoutAll.setBackgroundResource(R.drawable.theme_select5);
            LayoutAll.getBackground().setAlpha(100);
        }else if(ThemeNumber==6){
            LayoutAll.setBackgroundResource(R.drawable.theme_select6);
            LayoutAll.getBackground().setAlpha(100);
        }else if(ThemeNumber==7){
            LayoutAll.setBackgroundResource(R.drawable.theme_select7);
            LayoutAll.getBackground().setAlpha(100);
        }else if(ThemeNumber==8){
            LayoutAll.setBackgroundResource(R.drawable.theme_select8);
            LayoutAll.getBackground().setAlpha(100);
        }else if(ThemeNumber==9){
            LayoutAll.setBackgroundResource(R.drawable.theme_select9);
            LayoutAll.getBackground().setAlpha(100);
        }else if(ThemeNumber==10){
            LayoutAll.setBackgroundResource(R.drawable.theme_select10);
            LayoutAll.getBackground().setAlpha(100);
        }else if(ThemeNumber==11){
            LayoutAll.setBackgroundResource(R.drawable.theme_select11);
            LayoutAll.getBackground().setAlpha(100);
        }
    }
}
