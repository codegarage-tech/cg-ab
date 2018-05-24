package tech.codegarage.apkbackup.fragment;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;

import com.yalantis.multiselection.lib.MultiSelect;
import com.yalantis.multiselection.lib.MultiSelectBuilder;

import java.util.ArrayList;
import java.util.List;

import tech.codegarage.apkbackup.R;
import tech.codegarage.apkbackup.adapter.LeftAdapter;
import tech.codegarage.apkbackup.adapter.RightAdapter;
import tech.codegarage.apkbackup.base.BaseFragment;
import tech.codegarage.apkbackup.decorator.ApplicationInfoItemDecorator;
import tech.codegarage.apkbackup.interfaces.MultiSelectionCallback;
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
                .mountOn((ViewGroup) rootView.findViewById(R.id.mount_point))
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
//        LeftAdapter leftAdapter = new LeftAdapter(position -> mMultiSelect.select(position));
//        RightAdapter rightAdapter = new RightAdapter(position -> mMultiSelect.deselect(position));

        LeftAdapter leftAdapter = new LeftAdapter(new MultiSelectionCallback() {
            @Override
            public void onClick(int position) {
                mMultiSelect.select(position);
            }
        });
        RightAdapter rightAdapter = new RightAdapter(new MultiSelectionCallback() {
            @Override
            public void onClick(int position) {
                mMultiSelect.deselect(position);
            }
        });

        leftAdapter.addAll(applicationInfoList);

        builder.withLeftAdapter(leftAdapter)
                .withRightAdapter(rightAdapter);
    }

    public List<ApplicationInfo> applicationInfoList = new ArrayList<ApplicationInfo>() {{
        add(new ApplicationInfo("Dead Inside", "Muse", R.drawable.img_dead));
        add(new ApplicationInfo("Sandman", "Hurts", R.drawable.img_sandman));
        add(new ApplicationInfo("Doing It to Death", "The Kills", R.drawable.img_doing_it));
        add(new ApplicationInfo("Born This Way", "Thousand Foot Krunch", R.drawable.img_born));
        add(new ApplicationInfo("Light Up the Sky", "Thousand Foot Krunch", R.drawable.img_light_up));
        add(new ApplicationInfo("Rolling Stone", "Hurts", R.drawable.img_rolling));
        add(new ApplicationInfo("Hater", "Korn", R.drawable.img_hater));
        add(new ApplicationInfo("Siberian Knights", "The Kills", R.drawable.img_siberian));
        add(new ApplicationInfo("Hard As a Rock", "AC/DC", R.drawable.img_hard));
        add(new ApplicationInfo("Hells Bells", "AC/DC", R.drawable.img_hells));
        add(new ApplicationInfo("Way Down We Go", "Kaleo", R.drawable.img_way_down));
        add(new ApplicationInfo("Psycho", "Muse", R.drawable.img_psycho));
        add(new ApplicationInfo("Obstacles", "Syd Matters", R.drawable.img_obstacles));
        add(new ApplicationInfo("Guilty All the Same", "Linking Park", R.drawable.img_guilty));
        add(new ApplicationInfo("Jesus Walks", "Kanye West", R.drawable.img_jesus));
        add(new ApplicationInfo("Bad Blood", "Black Pistol Fire", R.drawable.img_bad));
        add(new ApplicationInfo("Black Skinhead", "Kanye West", R.drawable.img_blackskinhead));
        add(new ApplicationInfo("Someone to Love Me", "Diddy", R.drawable.img_someone));
        add(new ApplicationInfo("The Fire", "The Roots", R.drawable.img_thefire));
        add(new ApplicationInfo("Shook Ones, Pt II", "Mobb Deep", R.drawable.img_shook));
        add(new ApplicationInfo("We Don't Care", "Kanye West", R.drawable.img_jesus));
    }};
}