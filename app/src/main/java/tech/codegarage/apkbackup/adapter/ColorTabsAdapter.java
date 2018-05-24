package tech.codegarage.apkbackup.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import tech.codegarage.apkbackup.base.BaseFragment;
import tech.codegarage.apkbackup.fragment.AboutFragment;
import tech.codegarage.apkbackup.fragment.BackupFragment;
import tech.codegarage.apkbackup.fragment.HomeFragment;
import tech.codegarage.apkbackup.fragment.SettingsFragment;

/**
 * @author Md. Rashadul Alam
 * Email: rashed.droid@gmail.com
 */
public class ColorTabsAdapter extends FragmentStatePagerAdapter {

    private int amountTabs;
    private BaseFragment contentFragment;

    public ColorTabsAdapter(FragmentManager fragmentManager, int amountTabs) {
        super(fragmentManager);

        this.amountTabs = amountTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                contentFragment = new HomeFragment();
                break;
            case 1:
                contentFragment = new BackupFragment();
                break;
            case 2:
                contentFragment = new AboutFragment();
                break;
            case 3:
                contentFragment = new SettingsFragment();
                break;
            default:
                contentFragment = new HomeFragment();
                break;
        }
        return contentFragment;
    }

    @Override
    public int getCount() {
        return amountTabs;
    }
}