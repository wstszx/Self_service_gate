package com.example.self_service_gate.model;

import java.util.ArrayList;
import java.util.List;

public class StaffSelection {

    /**
     * assignedPostNumber : 0
     * gateNumber :
     * leaderImg :
     * leaderName :
     * leaderPost :
     * leaderTitle :
     * memberHelperList : [{"userId":0,"userImg":"","userName":"","userStatus":0,"userTitle":""}]
     * passageWay :
     * postHelperList : [{"postId":"","postName":"","userId":0,"userImg":"","userName":"","userTitle":""}]
     * postNumber : 0
     * teamMeberNumber : 0
     * toChooseMemberNumber : 0
     */

    private int assignedPostNumber;
    private String gateNumber;
    private String leaderImg;
    private String leaderName;
    private String leaderPost;
    private String leaderTitle;
    private String passageWay;
    private int postNumber;
    private int teamMeberNumber;
    private int toChooseMemberNumber;
    private List<MemberHelperListBean> memberHelperList;
    private List<PostHelperListBean> postHelperList;

    public int getAssignedPostNumber() {
        return assignedPostNumber;
    }

    public void setAssignedPostNumber(int assignedPostNumber) {
        this.assignedPostNumber = assignedPostNumber;
    }

    public String getGateNumber() {
        return gateNumber == null ? "" : gateNumber;
    }

    public void setGateNumber(String gateNumber) {
        this.gateNumber = gateNumber;
    }

    public String getLeaderImg() {
        return leaderImg == null ? "" : leaderImg;
    }

    public void setLeaderImg(String leaderImg) {
        this.leaderImg = leaderImg;
    }

    public String getLeaderName() {
        return leaderName == null ? "" : leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }

    public String getLeaderPost() {
        return leaderPost == null ? "" : leaderPost;
    }

    public void setLeaderPost(String leaderPost) {
        this.leaderPost = leaderPost;
    }

    public String getLeaderTitle() {
        return leaderTitle == null ? "" : leaderTitle;
    }

    public void setLeaderTitle(String leaderTitle) {
        this.leaderTitle = leaderTitle;
    }

    public String getPassageWay() {
        return passageWay == null ? "" : passageWay;
    }

    public void setPassageWay(String passageWay) {
        this.passageWay = passageWay;
    }

    public int getPostNumber() {
        return postNumber;
    }

    public void setPostNumber(int postNumber) {
        this.postNumber = postNumber;
    }

    public int getTeamMeberNumber() {
        return teamMeberNumber;
    }

    public void setTeamMeberNumber(int teamMeberNumber) {
        this.teamMeberNumber = teamMeberNumber;
    }

    public int getToChooseMemberNumber() {
        return toChooseMemberNumber;
    }

    public void setToChooseMemberNumber(int toChooseMemberNumber) {
        this.toChooseMemberNumber = toChooseMemberNumber;
    }

    public List<MemberHelperListBean> getMemberHelperList() {
        if (memberHelperList == null) {
            return new ArrayList<>();
        }
        return memberHelperList;
    }

    public void setMemberHelperList(List<MemberHelperListBean> memberHelperList) {
        this.memberHelperList = memberHelperList;
    }

    public List<PostHelperListBean> getPostHelperList() {
        if (postHelperList == null) {
            return new ArrayList<>();
        }
        return postHelperList;
    }

    public void setPostHelperList(List<PostHelperListBean> postHelperList) {
        this.postHelperList = postHelperList;
    }

    public static class MemberHelperListBean {
        /**
         * userId : 0
         * userImg :
         * userName :
         * userStatus : 0
         * userTitle :
         */

        private int userId;
        private String userImg;
        private String userName;
        private int userStatus;
        private String userTitle;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUserImg() {
            return userImg == null ? "" : userImg;
        }

        public void setUserImg(String userImg) {
            this.userImg = userImg;
        }

        public String getUserName() {
            return userName == null ? "" : userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public int getUserStatus() {
            return userStatus;
        }

        public void setUserStatus(int userStatus) {
            this.userStatus = userStatus;
        }

        public String getUserTitle() {
            return userTitle == null ? "" : userTitle;
        }

        public void setUserTitle(String userTitle) {
            this.userTitle = userTitle;
        }
    }

    public static class PostHelperListBean {
        /**
         * postId :
         * postName :
         * userId : 0
         * userImg :
         * userName :
         * userTitle :
         */

        private String postId;
        private String postName;
        private int userId;
        private String userImg;
        private String userName;
        private String userTitle;

        public String getPostId() {
            return postId == null ? "" : postId;
        }

        public void setPostId(String postId) {
            this.postId = postId;
        }

        public String getPostName() {
            return postName == null ? "" : postName;
        }

        public void setPostName(String postName) {
            this.postName = postName;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUserImg() {
            return userImg == null ? "" : userImg;
        }

        public void setUserImg(String userImg) {
            this.userImg = userImg;
        }

        public String getUserName() {
            return userName == null ? "" : userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserTitle() {
            return userTitle == null ? "" : userTitle;
        }

        public void setUserTitle(String userTitle) {
            this.userTitle = userTitle;
        }
    }
}
