package com.mwi7.saver.adapter

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import java.util.*

class ViewPagerWAAdapter(val mFragmentManager: FragmentManager,val behavior : Int) : FragmentPagerAdapter(mFragmentManager,behavior) {

    private var arrayListText = ArrayList<String>()
    private var fragmentArrayList = ArrayList<Fragment>()


    override fun getCount(): Int {
        return fragmentArrayList.size
    }

    override fun getItem(position: Int): Fragment {
        return fragmentArrayList[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return arrayListText[position]
    }

    fun addTabs(text: String, fragment: Fragment) {
        arrayListText.add(text)
        fragmentArrayList.add(fragment)
    }


}