package tech.codegarage.apkbackup.decorator;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author Md. Rashadul Alam
 * Email: rashed.droid@gmail.com
 */
public class ApplicationInfoItemDecorator extends RecyclerView.ItemDecoration {

    private int size;

    public ApplicationInfoItemDecorator(int size) {
        this.size = size;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.bottom = size;
        outRect.top = 0;
        outRect.left = 0;
        outRect.right = 0;
    }
}
