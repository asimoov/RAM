package br.ufba.hupes.hospitaladmissionforram.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import br.ufba.hupes.hospitaladmissionforram.fragment.DadosGeraisFragment;
import br.ufba.hupes.hospitaladmissionforram.fragment.DadosGeraisFragment_;

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
        Fragment fragment = DadosGeraisFragment_.builder().build();
        switch (position) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
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