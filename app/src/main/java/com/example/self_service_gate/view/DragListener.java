package com.example.self_service_gate.view;

import android.view.DragEvent;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.example.self_service_gate.R;
import com.example.self_service_gate.adapter.CandidateAdapter;
import com.example.self_service_gate.adapter.SelectedStaffAdapter;
import com.example.self_service_gate.model.StaffMember;

import java.util.List;

public class DragListener implements View.OnDragListener {
    private boolean isDropped = false;
    private Listener listener;

    public DragListener(Listener listener) {
        this.listener = listener;
    }

    @Override
    public boolean onDrag(View v, DragEvent event) {
        if (event.getAction() == DragEvent.ACTION_DROP) {
            isDropped = true;
            int positionTarget = -1;

            View viewSource = (View) event.getLocalState();
            int viewId = v.getId();
            final int container = R.id.constraint;
            final int rvTop = R.id.recyclerview;
            final int rvBottom = R.id.recyclerview1;

            switch (viewId) {
                case container:
                case rvTop:
                case rvBottom:

                    RecyclerView target;
                    switch (viewId) {
                        case rvTop:
                            target = v.getRootView().findViewById(rvTop);
                            break;
                        case rvBottom:
                            target = v.getRootView().findViewById(rvBottom);
                            break;
                        default:
                            target = (RecyclerView) v.getParent();
                            positionTarget = (int) v.getTag();
                    }

                    if (viewSource != null) {
                        RecyclerView source = (RecyclerView) viewSource.getParent();

                        CandidateAdapter adapterSource = (CandidateAdapter) source.getAdapter();
                        int positionSource = (int) viewSource.getTag();

                        StaffMember.MemberHelperListBean list = adapterSource.getData().get(positionSource);

                        adapterSource.remove(positionSource);
                        StaffMember.PostHelperListBean bean = castBottomToTop(list);

                        SelectedStaffAdapter adapterTarget = (SelectedStaffAdapter) target.getAdapter();

                        if (adapterTarget != null) {
                            if (positionTarget >= 0) {
                                adapterTarget.setData(positionTarget, bean);
                            } else {
                                adapterTarget.addData(bean);
                            }
                        }

                    }
                    break;
            }
        }

        if (!isDropped && event.getLocalState() != null) {
            ((View) event.getLocalState()).setVisibility(View.VISIBLE);
        }
        return true;
    }

    private StaffMember.PostHelperListBean castBottomToTop(StaffMember.MemberHelperListBean list) {
        StaffMember.PostHelperListBean postHelperListBean = new StaffMember.PostHelperListBean();
        postHelperListBean.setPostName(list.getUserName());
        postHelperListBean.setUserTitle(list.getUserTitle());
        return postHelperListBean;
    }
}
