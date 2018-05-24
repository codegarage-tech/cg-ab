package tech.codegarage.apkbackup.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import tech.codegarage.apkbackup.R;
import tech.codegarage.apkbackup.model.ApplicationInfo;

/**
 * @author Md. Rashadul Alam
 * Email: rashed.droid@gmail.com
 */
class ViewHolder extends RecyclerView.ViewHolder {
    TextView track;
    TextView artist;
    ImageView avatar;

    public ViewHolder(View view) {
        super(view);
        track = (TextView) view.findViewById(R.id.track);
        artist = (TextView) view.findViewById(R.id.artist);
        avatar = (ImageView) view.findViewById(R.id.yal_ms_avatar);
    }

    public static void bind(ViewHolder viewHolder, ApplicationInfo applicationInfo) {
        viewHolder.track.setText(applicationInfo.getAppName());
        viewHolder.artist.setText(applicationInfo.getAppPackage());
        viewHolder.avatar.setImageResource(track.getAlbum());
    }
}
