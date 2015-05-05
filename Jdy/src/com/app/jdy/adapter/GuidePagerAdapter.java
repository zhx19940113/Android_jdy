package com.app.jdy.adapter;

import com.app.jdy.ui.GuideFragmentOne;
import com.app.jdy.ui.GuideFragmentTwo;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
/**
 * 
 * description :FragmentPagerAdapter : 当我们的viewpager和fragment嵌套使用时实用的
 *
 * @version 1.0
 * @author zhonghuixiong
 * @createtime : 2015-1-11 下午3:07:51
 * 
 * 修改历史:
 * 修改人                                          修改时间                                                  修改内容
 * --------------- ------------------- -----------------------------------
 * zhonghuixiong        2015-1-11 下午3:07:51
 *
 */
public class GuidePagerAdapter extends FragmentPagerAdapter{

	private Context ctx;
	private Boolean isOK;

	//FragmentManager fragment管理器 ,上下文
	public GuidePagerAdapter(FragmentManager fm,Context ctx,Boolean isOK) {
		super(fm);
		this.ctx = ctx;
		this.isOK = isOK;
	}
	//返回一个fragment
	//arg0 滑动到第几页
	@Override
	public Fragment getItem(int arg0) {
		Fragment mFragment = null;
		if(arg0 == 0){
			mFragment = new GuideFragmentOne(ctx);
		}else if(arg0 == 1){
			mFragment = new GuideFragmentTwo(ctx,isOK);
		}
		return mFragment;
	}

	//返回适配数量
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 2;
	}

}
