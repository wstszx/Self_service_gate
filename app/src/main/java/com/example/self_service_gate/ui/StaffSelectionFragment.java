package com.example.self_service_gate.ui;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ConvertUtils;
import com.bumptech.glide.Glide;
import com.example.self_service_gate.R;
import com.example.self_service_gate.adapter.CandidateAdapter;
import com.example.self_service_gate.adapter.SelectedStaffAdapter;
import com.example.self_service_gate.databinding.FragmentStaffSelectionBinding;
import com.example.self_service_gate.model.BaseResponse;
import com.example.self_service_gate.model.StaffMember;
import com.example.self_service_gate.utils.GridSpacingItemDecoration;
import com.example.self_service_gate.utils.InjectorUtils;
import com.example.self_service_gate.utils.MaxCountLayoutManager;
import com.example.self_service_gate.utils.SpacesItemDecoration;
import com.example.self_service_gate.view.Listener;
import com.example.self_service_gate.viewmodels.StaffSelectionViewModel;
import com.example.self_service_gate.viewmodels.StaffSelectionViewModelFactory;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StaffSelectionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StaffSelectionFragment extends Fragment implements View.OnClickListener, Listener {

    private static final int LEFT = 1;
    private static final int RIGHT = 2;
    private static StaffSelectionFragment sFragment;
    private FragmentStaffSelectionBinding mBinding;
    private TextView mTvAisle;
    private TextView mTvGate;
    private ImageView mIvAvatar;
    private ImageView mIvLevel;
    private TextView mTvTitle2;
    private TextView mTvTitle3;
    private TextView mTvTeamMember;
    private TextView mTvPost;
    private TextView mTvCandidate;
    private TextView mTvLinedup;
    private ImageView mIvLeft;
    private ImageView mIvRight;
    private TextView mTvAdjustmentStaff;
    private TextView mTvConfirmToWork;
    private TextView mTvConfirmLaidOff;
    private SelectedStaffAdapter mAdapter;
    private CandidateAdapter mAdapter1;
    private RecyclerView mRecyclerview;
    private RecyclerView mRecyclerview1;
    private StaffSelectionViewModel mViewModel;
    private int mLastItemPosition = 5;
    private int mFirstItemPosition;
    private View mScrollbar;

    private float barTouchedLastY;

    public StaffSelectionFragment() {
        // Required empty public constructor
    }

    public static StaffSelectionFragment newInstance() {
        if (sFragment == null) {
            sFragment = new StaffSelectionFragment();
        }
        return sFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentStaffSelectionBinding.inflate(inflater, container, false);
        initView();
        initData();
        return mBinding.getRoot();
    }

    private void initData() {
        StaffSelectionViewModelFactory factory = InjectorUtils.provideStaffSelectionViewModelFactory(requireContext());
        mViewModel = new ViewModelProvider(this, factory).get(StaffSelectionViewModel.class);
        mViewModel.getStaffMemberMutableLiveData().observe(getViewLifecycleOwner(), new Observer<StaffMember>() {
            @Override
            public void onChanged(StaffMember staffMember) {
                setData(staffMember);
            }
        });
        mViewModel.getStaffMember("");
        mViewModel.getConfirmWorkLiveData().observe(getViewLifecycleOwner(), new Observer<BaseResponse>() {
            @Override
            public void onChanged(BaseResponse baseResponse) {

            }
        });
        mViewModel.getConfirmLaidOffLiveData().observe(getViewLifecycleOwner(), new Observer<BaseResponse>() {
            @Override
            public void onChanged(BaseResponse baseResponse) {

            }
        });
    }

    private void setData(StaffMember staffMember) {
        List<StaffMember.PostHelperListBean> postHelperList = staffMember.getPostHelperList();
        List<StaffMember.MemberHelperListBean> memberHelperList = staffMember.getMemberHelperList();
        mAdapter.setNewInstance(postHelperList);
        mAdapter1.setNewInstance(memberHelperList);

        mTvGate.setText(staffMember.getGateNumber());
        mTvAisle.setText(staffMember.getPassageWay());
        Glide.with(getContext()).load(staffMember.getLeaderImg()).into(mIvAvatar);
        mTvTitle2.setText(staffMember.getLeaderName());
        mTvTitle3.setText(staffMember.getLeaderTitle());
        mTvTeamMember.setText(staffMember.getTeamMeberNumber() + "");
        mTvPost.setText(staffMember.getPostNumber() + "");
        mTvCandidate.setText(staffMember.getToChooseMemberNumber() + "");
        mTvLinedup.setText(staffMember.getAssignedPostNumber() + "");
    }

    private void initView() {
        mTvAisle = mBinding.tvAisle;
        mTvGate = mBinding.tvGate;
        mIvAvatar = mBinding.ivAvatar;
        mIvLevel = mBinding.ivLevel;
        mTvTitle2 = mBinding.tvTitle2;
        mTvTitle3 = mBinding.tvTitle3;
        mTvTeamMember = mBinding.tvTeamMember;
        mTvPost = mBinding.tvPost;
        mTvCandidate = mBinding.tvCandidate;
        mTvLinedup = mBinding.tvLinedup;
        mRecyclerview = mBinding.recyclerview;
        mRecyclerview1 = mBinding.recyclerview1;
        mIvLeft = mBinding.ivLeft;
        mIvRight = mBinding.ivRight;
        mTvAdjustmentStaff = mBinding.tvAdjustmentStaff;
        mTvConfirmToWork = mBinding.tvConfirmToWork;
        mTvConfirmLaidOff = mBinding.tvConfirmLaidOff;
        mScrollbar = mBinding.scrollbar;

        mIvLeft.setOnClickListener(this);
        mIvRight.setOnClickListener(this);
        mTvAdjustmentStaff.setOnClickListener(this);
        mTvConfirmToWork.setOnClickListener(this);
        mTvConfirmLaidOff.setOnClickListener(this);


        mAdapter = new SelectedStaffAdapter(getContext(), R.layout.item_jobs);
        mAdapter1 = new CandidateAdapter(getContext(), R.layout.item_jobs);
        mRecyclerview.setAdapter(mAdapter);
        mRecyclerview1.setAdapter(mAdapter1);
        MaxCountLayoutManager manager1 = new MaxCountLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        manager1.setMaxCount(6);
        mRecyclerview1.setLayoutManager(manager1);
        //设置添加,移除动画
        mRecyclerview.setItemAnimator(new DefaultItemAnimator());
        mRecyclerview1.setItemAnimator(new DefaultItemAnimator());

//        DragedItemTouchHandler touchHandler1 = new DragedItemTouchHandler(mAdapter1);
//        ItemTouchHelper itemTouchHelper1 = new ItemTouchHelper(touchHandler1);
//        itemTouchHelper1.attachToRecyclerView(mRecyclerview1);
        mRecyclerview.addItemDecoration(new GridSpacingItemDecoration(5, 20, true));
        mRecyclerview1.addItemDecoration(new SpacesItemDecoration(30));
        mRecyclerview1.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                //判断是当前layoutManager是否为LinearLayoutManager
                // 只有LinearLayoutManager才有查找第一个和最后一个可见view位置的方法
                if (layoutManager instanceof LinearLayoutManager) {
                    LinearLayoutManager linearManager = (LinearLayoutManager) layoutManager;
                    //获取最后一个可见view的位置
                    mLastItemPosition = linearManager.findLastVisibleItemPosition();
                    //获取第一个可见view的位置
                    mFirstItemPosition = linearManager.findFirstVisibleItemPosition();
                }
            }
        });

        mRecyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                changeBarPosition();
            }
        });
        mScrollbar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
//                        barTouchedLastY是触摸点相对于父容器的垂直方向距离
                        barTouchedLastY = event.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:
//                        dy是滑动的距离
                        float dY = event.getRawY() - barTouchedLastY;
                        moveListView(dY);
                        barTouchedLastY = event.getRawY();

                        //上下边界值
                        if (mScrollbar.getY() + dY + mScrollbar.getMeasuredHeight() >= mBinding.constraint2.getHeight()) {
                            //到底了
                            mScrollbar.setY(mBinding.constraint2.getHeight() - mScrollbar.getMeasuredHeight());
                        } else if (mScrollbar.getY() + dY <= 0) {
                            //到顶了
                            mScrollbar.setY(0);
                        } else {
                            mScrollbar.setY(mScrollbar.getY() + dY);
                        }
                        break;
                    case MotionEvent.ACTION_CANCEL:
                    case MotionEvent.ACTION_UP:
                        break;
                }

                return true;
            }
        });
    }

    private void moveListView(float dY) {

        //列表总长度
        int verticalScrollRange = mRecyclerview.computeVerticalScrollRange();
        //可见区域长度
        int verticalScrollExtent = mRecyclerview.computeVerticalScrollExtent();
        //向上滑动的大概距离
        int verticalScrollOffset = mRecyclerview.computeVerticalScrollOffset();

        int listMovableHeight = verticalScrollRange - verticalScrollExtent;
        int barMovableHeight = verticalScrollExtent - mScrollbar.getHeight();

        mRecyclerview.scrollBy(0, (int) (1.0f * listMovableHeight / barMovableHeight * dY));
    }


    private void changeBarPosition() {
        //列表总长度
        int verticalScrollRange = mRecyclerview.computeVerticalScrollRange();
        //可见区域长度
        int verticalScrollExtent = mRecyclerview.computeVerticalScrollExtent();
        //向上滑动的大概距离
        int verticalScrollOffset = mRecyclerview.computeVerticalScrollOffset();

        int listMovableHeight = verticalScrollRange - verticalScrollExtent;
        int barMovableHeight = verticalScrollExtent - mScrollbar.getHeight() - ConvertUtils.dp2px(42);
        mScrollbar.setY(1.0f * barMovableHeight / listMovableHeight * verticalScrollOffset);

    }

    @Override
    public void onClick(View v) {
        if (v == mIvLeft) {
            scrollToPosition(LEFT);
        } else if (v == mIvRight) {
            scrollToPosition(RIGHT);
        } else if (v == mTvAdjustmentStaff) {
            mTvConfirmToWork.setEnabled(false);
            mTvConfirmLaidOff.setEnabled(false);
        } else if (v == mTvConfirmToWork) {
            confirmToWork();
        } else if (v == mTvConfirmLaidOff) {
            confirmLaidOff();
        }
    }

    /**
     * 确认下岗
     */
    private void confirmLaidOff() {
        final DialogPlus dialogPlus = DialogPlus.newDialog(getContext())
                .setContentHolder(new ViewHolder(R.layout.dialog_working_time_confirmation))
                .setGravity(Gravity.CENTER)
                .setContentWidth(ViewGroup.LayoutParams.WRAP_CONTENT)
                .create();
        View holderView = dialogPlus.getHolderView();
        TextView tv_confirm = holderView.findViewById(R.id.tv_confirm);
        tv_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogPlus.dismiss();
                mViewModel.confirmLaidOff("");
            }
        });
        dialogPlus.show();
    }

    /**
     * 确认上岗
     */
    private void confirmToWork() {
        JSONObject jsonObject = new JSONObject();

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonObject.toString());

        mViewModel.confirmToWork(requestBody);
    }

    private void scrollToPosition(int direction) {
        switch (direction) {
            case LEFT:
                mRecyclerview1.smoothScrollToPosition(Math.max(mFirstItemPosition - 6, 0));
                break;
            case RIGHT:
                mRecyclerview1.smoothScrollToPosition(mLastItemPosition + 6);
                break;
        }
    }

    @Override
    public void setEmptyListTop(boolean visibility) {

    }

    @Override
    public void setEmptyListBottom(boolean visibility) {

    }
}
