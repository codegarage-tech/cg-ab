package tech.codegarage.apkbackup.fragment;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;

import com.yalantis.multiselection.lib.MultiSelect;
import com.yalantis.multiselection.lib.MultiSelectBuilder;

import tech.codegarage.apkbackup.R;
import tech.codegarage.apkbackup.base.BaseFragment;
import tech.codegarage.apkbackup.decorator.ApplicationInfoItemDecorator;
import tech.codegarage.apkbackup.model.ApplicationInfo;

/**
 * @author Md. Rashadul Alam
 * Email: rashed.droid@gmail.com
 */
public class HomeFragment extends BaseFragment {

    private MultiSelect<ApplicationInfo> mMultiSelect;

    @Override
    public int initFragmentLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void initFragmentViews(View parentView) {

    }

    @Override
    public void initFragmentViewsData() {
        MultiSelectBuilder<ApplicationInfo> builder = new MultiSelectBuilder<>(ApplicationInfo.class)
                .withContext(getActivity())
                .mountOn((ViewGroup) findViewById(R.id.mount_point))
                .withSidebarWidth(46 + 8 * 2); // ImageView width with paddings

        setUpAdapters(builder);
        mMultiSelect = builder.build();

        setUpDecoration();
    }

    @Override
    public void initFragmentActions() {

    }

    @Override
    public void initFragmentBackPress() {

    }

    @Override
    public void initFragmentOnResult(int requestCode, int resultCode, Intent data) {

    }

    private void setUpDecoration() {
        ApplicationInfoItemDecorator itemDecorator = new ApplicationInfoItemDecorator(
                getResources().getDimensionPixelSize(R.dimen.decoration_size));
        mMultiSelect.getRecyclerLeft().addItemDecoration(itemDecorator);
        mMultiSelect.getRecyclerRight().addItemDecoration(itemDecorator);
    }

    private void setUpAdapters(MultiSelectBuilder<ApplicationInfo> builder) {
        LeftAdapter leftAdapter = new LeftAdapter(position -> mMultiSelect.select(position));
        RightAdapter rightAdapter = new RightAdapter(position -> mMultiSelect.deselect(position));

        leftAdapter.addAll(TrackList.TRACKS);

        builder.withLeftAdapter(leftAdapter)
                .withRightAdapter(rightAdapter);
    }
}