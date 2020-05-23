package com.example.self_service_gate.adapter;

import android.content.ClipData;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.self_service_gate.R;
import com.example.self_service_gate.model.StaffMember;
import com.example.self_service_gate.view.DragListener;
import com.example.self_service_gate.view.Listener;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SelectedStaffAdapter extends BaseQuickAdapter<StaffMember.PostHelperListBean, BaseViewHolder> implements View.OnTouchListener {

    private final Context mContext;
    private Listener listener;


    public SelectedStaffAdapter(Context context, int layoutResId) {
        super(layoutResId);
        mContext = context;
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, StaffMember.PostHelperListBean bean) {
        if (bean != null) {
            String userImg = bean.getUserImg();
            String userTitle = bean.getUserTitle();
            String userName = bean.getUserName();
            String postName = bean.getPostName();
            baseViewHolder.setText(R.id.tv_title_1, postName)
                    .setText(R.id.tv_title_2, userName)
                    .setText(R.id.tv_title_3, userTitle);
            Glide.with(mContext).load(userImg).placeholder(R.drawable.personnel_select_defaut).into(((ImageView) baseViewHolder.getView(R.id.iv_avatar)));
            baseViewHolder.getView(R.id.constraint).setTag(baseViewHolder.getAdapterPosition());
            baseViewHolder.getView(R.id.constraint).setOnTouchListener(this);
            baseViewHolder.getView(R.id.constraint).setOnDragListener(new DragListener(null));

        }
    }

    public List<StaffMember.PostHelperListBean> getList() {
        return getData();
    }

    public void updateList(List<StaffMember.PostHelperListBean> customListTarget) {
        setList(customListTarget);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            ClipData data = ClipData.newPlainText("", "");
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                v.startDragAndDrop(data, shadowBuilder, v, 0);
            } else {
                v.startDrag(data, shadowBuilder, v, 0);
            }
            return true;
        }
        return false;
    }

    public View.OnDragListener getDragInstance() {
//        if (listener != null) {
            return new DragListener(listener);
//        } else {
//            Log.e("ListAdapter", "Listener wasn't initialized!");
//            return null;
//        }
    }
}
