package controller


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import view.AboutFragment
import view.Contact
import view.Features
import view.Highlights


class PortfolioAdapter(fragmentManager :FragmentManager):FragmentPagerAdapter(fragmentManager)  {
    override fun getCount(): Int {
        return 4

    }

     override fun getItem(position: Int): Fragment {
        when(position){
            0->return AboutFragment()
            1->return Features()
            2->return Highlights()
            3->return Contact()
        }
         throw IllegalStateException("position $position is invalid for this viewpager")
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position){
            0->return "About"
            1->return "features"
            2->return "highlight"
            3->return "contacts"
        }
        return null
    }
}