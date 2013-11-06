package br.ufba.hupes.hospitaladmissionforram.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import br.ufba.hupes.hospitaladmissionforram.fragment.AlgoritmoFragment_;
import br.ufba.hupes.hospitaladmissionforram.fragment.DadosGeraisFragment;
import br.ufba.hupes.hospitaladmissionforram.fragment.DadosGeraisFragment_;
import br.ufba.hupes.hospitaladmissionforram.fragment.InfoAdicionaisFragment_;
import br.ufba.hupes.hospitaladmissionforram.fragment.OutrasCausasFragment_;
import br.ufba.hupes.hospitaladmissionforram.fragment.RAMFragment_;

public class PagerAdapter extends FragmentPagerAdapter {

    Fragment fragments[];

    public PagerAdapter(FragmentManager fm) {
        super(fm);
        fragments = new Fragment[getCount()];
    }

    @Override
    public Fragment getItem(int position) {
        if (fragments[position] == null) {
            fragments[position] = getNewFragment(position);
        }
        return fragments[position];
    }

    private Fragment getNewFragment(int position) {
        Fragment fragment = null;
		switch (position) {
            case 0:
            	fragment = DadosGeraisFragment_.builder().build();
                break;
            case 1:
            	fragment = RAMFragment_.builder().build();
                break;
            case 2:
            	fragment = OutrasCausasFragment_.builder().build();
                break;
            case 3:
            	fragment = InfoAdicionaisFragment_.builder().build();
                break;
            case 4:
            	fragment = AlgoritmoFragment_.builder().build();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 5;
    }

    public Fragment[] getFragments() {
        return fragments;
    }
}