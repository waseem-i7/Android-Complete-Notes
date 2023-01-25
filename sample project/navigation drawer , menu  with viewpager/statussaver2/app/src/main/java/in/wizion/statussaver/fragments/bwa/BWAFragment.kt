package `in`.wizion.statussaver.fragments.bwa

import `in`.wizion.statussaver.R
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.mwi7.saver.adapter.ViewPagerWAAdapter


class BWAFragment : Fragment() {

    lateinit var viewPager : ViewPager
    lateinit var tabLayout : TabLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        val v : View = inflater.inflate(R.layout.fragment_b_w_a, container, false)
        viewPager = v.findViewById(R.id.viewPager_bwa)
        tabLayout = v.findViewById(R.id.tab_layout_bwa)
        viewPager.offscreenPageLimit=2
        val adapter : ViewPagerWAAdapter = ViewPagerWAAdapter(childFragmentManager,
            FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
        adapter.addTabs("Images",BWAImageFragment())
        adapter.addTabs("Videos",BWAVideoFragment())
        viewPager.adapter=adapter
        tabLayout.setupWithViewPager(viewPager)
        return v
    }
}