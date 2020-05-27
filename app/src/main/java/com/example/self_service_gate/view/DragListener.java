package com.example.self_service_gate.view;

import android.view.DragEvent;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.example.self_service_gate.R;
import com.example.self_service_gate.adapter.CandidateAdapter;
import com.example.self_service_gate.adapter.SelectedStaffAdapter;
import com.example.self_service_gate.model.StaffMember;

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
                        boolean type = judgmentType(source.getAdapter());
                        if (type) {
                            CandidateAdapter adapterSource = (CandidateAdapter) source.getAdapter();
                            int positionSource = (int) viewSource.getTag();
                            StaffMember.MemberHelperListBean list = adapterSource.getData().get(positionSource);
                            StaffMember.PostHelperListBean bean = castBottomToTop(list);
                            boolean type1 = judgmentType(target.getAdapter());
                            if (!type1) {
                                adapterSource.remove(positionSource);
                                SelectedStaffAdapter adapterTarget = (SelectedStaffAdapter) target.getAdapter();
                                if (adapterTarget != null) {
                                    if (positionTarget >= 0) {
                                        adapterTarget.setData(positionTarget, bean);
                                    } else {
                                        adapterTarget.addData(bean);
                                    }
                                }
                            }
                        } else {
                            SelectedStaffAdapter adapterSource = ((SelectedStaffAdapter) source.getAdapter());
                            int positionSource = (int) viewSource.getTag();
                            StaffMember.PostHelperListBean list = adapterSource.getData().get(positionSource);
                            boolean type1 = judgmentType(target.getAdapter());
                            if (type1) {
                                CandidateAdapter adapterTarget = (CandidateAdapter) target.getAdapter();
                                if (adapterTarget != null) {
                                    StaffMember.MemberHelperListBean bean = castTopToBottom(list);
                                    if (positionTarget >= 0) {
                                        adapterTarget.addData(positionTarget, bean);
                                    } else {
                                        adapterTarget.addData(bean);
                                    }
                                }
                            } else {
                                SelectedStaffAdapter adapterTarget = (SelectedStaffAdapter) target.getAdapter();
                                if (adapterTarget != null) {
                                    if (positionTarget >= 0) {
                                        StaffMember.PostHelperListBean item = adapterTarget.getItem(positionTarget);
                                        adapterTarget.setData(positionSource, item);
                                        adapterTarget.setData(positionTarget, list);
                                    }
                                }
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

    private boolean judgmentType(RecyclerView.Adapter adapter) {
        return adapter instanceof CandidateAdapter;
    }

    private StaffMember.PostHelperListBean castBottomToTop(StaffMember.MemberHelperListBean list) {
        StaffMember.PostHelperListBean postHelperListBean = new StaffMember.PostHelperListBean();
        postHelperListBean.setPostName(list.getUserName());
        postHelperListBean.setUserTitle(list.getUserTitle());
        return postHelperListBean;
    }

    private StaffMember.MemberHelperListBean castTopToBottom(StaffMember.PostHelperListBean list) {
        StaffMember.MemberHelperListBean postHelperListBean = new StaffMember.MemberHelperListBean();
        postHelperListBean.setUserName(list.getUserName());
        postHelperListBean.setUserTitle(list.getUserTitle());
        return postHelperListBean;
    }
}
