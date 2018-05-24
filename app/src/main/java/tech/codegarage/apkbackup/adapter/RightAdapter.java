package tech.codegarage.apkbackup.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yalantis.multiselection.lib.adapter.BaseRightAdapter;

import tech.codegarage.apkbackup.R;
import tech.codegarage.apkbackup.interfaces.MultiSelectionCallback;
import tech.codegarage.apkbackup.model.ApplicationInfo;
import tech.codegarage.apkbackup.viewholder.ViewHolder;

/**
 * @author Md. Rashadul Alam
 * Email: rashed.droid@gmail.com
 */
public class RightAdapter extends BaseRightAdapter<ApplicationInfo, ViewHolder> {

    private final MultiSelectionCallback callback;

    public RightAdapter(MultiSelectionCallback callback) {
        this.callback = callback;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.multi_selection_item_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);

        ViewHolder.bind(holder, getItemAt(position));

//        holder.itemView.setOnClickListener(view -> {
//            view.setPressed(true);
//            view.postDelayed(() -> {
//                view.setPressed(false);
//                callback.onClick(holder.getAdapterPosition());
//            }, 200);
//        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                view.setPressed(true);

                view.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        view.setPressed(false);
                        callback.onClick(holder.getAdapterPosition());
                    }
                }, 200);
            }
        });
    }
}
