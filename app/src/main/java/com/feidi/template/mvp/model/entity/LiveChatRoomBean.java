package com.feidi.template.mvp.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class LiveChatRoomBean implements Parcelable {


    /**
     * negotiate : [{"id":"21efc3cd809b42549f8fb79118ed08f8","title":"你有能力，我有空间","banner":"[{\"uid\":\"rc-upload-1588093145051-5\",\"status\":\"done\",\"name\":\"timg.jpg\",\"url\":\"https://47.57.31.10:8443/odm/file/2020/04/29/3fa3ed89e49248e2bc212c8656beba4e.jpg\"}]","state":"1","anchorUserId":"b463092ec1b3435394068d6439d115d4","roomNumber":"2020042391","createTime":1588093431351,"anchorUserName":"张三","parentId":"483ca2c3c8934a0f8d0de0a584af1240","exhibitionUserDtoList":null,"desc":"洽谈室","exhibitionLiveUrl":null,"anchorLiveUrl":"http://play.smallaide.com/live/1400356456_20200423917_admin_main.flv,http://play.smallaide.com/live/1400356456_20200423917_share_admin_main.flv,http://play.smallaide.com/live/1400356456_2020042391_admin_main.flv,http://play.smallaide.com/live/1400356456_2020042391_share_admin_main.flv","type":"negotiate"}]
     * roomNumber :
     * anchorLiveUrl : http://play.smallaide.com/live/1400356456_20200423965_admin_main,http://play.smallaide.com/live/1400356456_20200423965_share_admin_main,http://play.smallaide.com/live/1400356456_20200423965_admin_main,http://play.smallaide.com/live/1400356456_20200423965_share_admin_main,http://play.smallaide.com/live/1400356456_20200423965_admin_main,http://play.smallaide.com/live/1400356456_20200423965_share_admin_main,http://play.smallaide.com/live/1400356456_20200423965_admin_main,http://play.smallaide.com/live/1400356456_20200423965_share_admin_main,http://play.smallaide.com/live/1400356456_20200423965_admin_main,http://play.smallaide.com/live/1400356456_20200423965_share_admin_main,http://play.smallaide.com/live/1400356456_20200423965_zhangsan_main,http://play.smallaide.com/live/1400356456_20200423965_share_zhangsan_main,http://play.smallaide.com/live/1400356456_20200423965_admin_main.flv,http://play.smallaide.com/live/1400356456_20200423965_share_admin_main.flv,http://play.smallaide.com/live/1400356456_20200423965_admin_main.flv,http://play.smallaide.com/live/1400356456_20200423965_share_admin_main.flv,http://play.smallaide.com/live/1400356456_20200423965_admin_main.flv,http://play.smallaide.com/live/1400356456_20200423965_share_admin_main.flv
     * banner : [{"uid":"rc-upload-1588006041317-6","status":"done","name":"timg.jpg","url":"https://47.57.31.10:8443/odm/file/2020/04/28/998b0a4a1ffe4cfabec324c113dff515.jpg"}]
     * title : 众志成城，共同战“疫”
     * type : exhibition
     * parentId :
     * exhibitionUserDtoList : [{"id":"20200427956","exhibitionId":"483ca2c3c8934a0f8d0de0a584af1240","userId":"0a6e761f51f84dfc8b7802e9078d4868","createTime":1587971101714,"userName":"李四","invitationCode":"ifs3ruk"},{"id":"20200428926","exhibitionId":"483ca2c3c8934a0f8d0de0a584af1240","userId":"d130a890baa5430392a2d468ec777e8a","createTime":1588064748335,"userName":"admin","invitationCode":"ifs34k6"},{"id":"20200428954","exhibitionId":"483ca2c3c8934a0f8d0de0a584af1240","userId":"f8fae76d7ec148c2b006840b592b254a","createTime":1588052928094,"userName":"赵六","invitationCode":"ifs343v"},{"id":"20200428968","exhibitionId":"483ca2c3c8934a0f8d0de0a584af1240","userId":"b463092ec1b3435394068d6439d115d4","createTime":1588052915443,"userName":"张三","invitationCode":"ifs34ms"},{"id":"20200428976","exhibitionId":"483ca2c3c8934a0f8d0de0a584af1240","userId":"c59c8f0fe6ae4427bfd30787f4c75b05","createTime":1588052921988,"userName":"王五","invitationCode":"ifs34mp"}]
     * anchorUserName : admin
     * exhibitionLiveUrl :
     http://live.codmr.com/live/93972_477b02d99dd6c00c5ba852bb9a9e1f6c.flv
     * createTime : 1587622492504
     * anchorUserId : d130a890baa5430392a2d468ec777e8a
     * id : 483ca2c3c8934a0f8d0de0a584af1240
     * state : 1
     * live : [{"id":"ae071999528e40de84dcec8fdb0aa644","title":"四川会场","banner":"[{\"uid\":\"rc-upload-1588006041317-8\",\"status\":\"done\",\"name\":\"timg.jpg\",\"url\":\"https://47.57.31.10:8443/odm/file/2020/04/28/d7653bd6a0d24c289bdcb95d474e0ba7.jpg\"}]","state":"1","anchorUserId":"0a6e761f51f84dfc8b7802e9078d4868","roomNumber":"2020042391","createTime":15876225648,"anchorUserName":"李四","parentId":"483ca2c3c8934a0f8d0de0a584af1240","exhibitionUserDtoList":null,"desc":null,"exhibitionLiveUrl":"http://live.codmr.com/live/93972_477b02d99dd6c00c5ba852bb9a9e1f6c.flv","anchorLiveUrl":"http://play.smallaide.com/live/1400356456_20200423918_admin_main.flv,http://play.smallaide.com/live/1400356456_20200423918_share_admin_main.flv","type":"live"},{"id":"f2c9980ce1a44b6c894632a05843b344","title":"北京会场","banner":"[{\"uid\":\"rc-upload-1588007347956-8\",\"status\":\"done\",\"name\":\"timg.jpg\",\"url\":\"https://47.57.31.10:8443/odm/file/2020/04/28/22e58c07de3c422a97750e2bd2234998.jpg\"}]","state":"1","anchorUserId":"b463092ec1b3435394068d6439d115d4","roomNumber":"2020042392","createTime":1587622577714,"anchorUserName":"张三","parentId":"483ca2c3c8934a0f8d0de0a584af1240","exhibitionUserDtoList":null,"desc":null,"exhibitionLiveUrl":"http://live.codmr.com/live/93972_477b02d99dd6c00c5ba852bb9a9e1f6c.flv","anchorLiveUrl":null,"type":"live"},{"id":"f734641e5c754318ac0500841f1939ed","title":"深圳会场","banner":"[{\"uid\":\"rc-upload-1588006041317-12\",\"status\":\"done\",\"name\":\"timg.jpg\",\"url\":\"https://47.57.31.10:8443/odm/file/2020/04/28/e651243784324d91809331b607950a1e.jpg\"}]","state":"1","anchorUserId":"0a6e761f51f84dfc8b7802e9078d4868","roomNumber":"2020042396","createTime":1587622929426,"anchorUserName":"李四","parentId":"483ca2c3c8934a0f8d0de0a584af1240","exhibitionUserDtoList":null,"desc":null,"exhibitionLiveUrl":"http://live.codmr.com/live/93972_477b02d99dd6c00c5ba852bb9a9e1f6c.flv","anchorLiveUrl":null,"type":"live"}]
     * desc : null
     */

    private String roomNumber;
    private String anchorLiveUrl;
    private String banner;
    private String title;
    private String type;
    private String parentId;
    private String anchorUserName;
    private String exhibitionLiveUrl;
    private long createTime;
    private String anchorUserId;
    private String id;
    private String state;
    private String desc;
    private List<NegotiateBean> negotiate;
    private List<ExhibitionUserDtoListBean> exhibitionUserDtoList;
    private List<LiveBean> live;

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getAnchorLiveUrl() {
        return anchorLiveUrl;
    }

    public void setAnchorLiveUrl(String anchorLiveUrl) {
        this.anchorLiveUrl = anchorLiveUrl;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getAnchorUserName() {
        return anchorUserName;
    }

    public void setAnchorUserName(String anchorUserName) {
        this.anchorUserName = anchorUserName;
    }

    public String getExhibitionLiveUrl() {
        return exhibitionLiveUrl;
    }

    public void setExhibitionLiveUrl(String exhibitionLiveUrl) {
        this.exhibitionLiveUrl = exhibitionLiveUrl;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getAnchorUserId() {
        return anchorUserId;
    }

    public void setAnchorUserId(String anchorUserId) {
        this.anchorUserId = anchorUserId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<NegotiateBean> getNegotiate() {
        return negotiate;
    }

    public void setNegotiate(List<NegotiateBean> negotiate) {
        this.negotiate = negotiate;
    }

    public List<ExhibitionUserDtoListBean> getExhibitionUserDtoList() {
        return exhibitionUserDtoList;
    }

    public void setExhibitionUserDtoList(List<ExhibitionUserDtoListBean> exhibitionUserDtoList) {
        this.exhibitionUserDtoList = exhibitionUserDtoList;
    }

    public List<LiveBean> getLive() {
        return live;
    }

    public void setLive(List<LiveBean> live) {
        this.live = live;
    }

    public static class NegotiateBean implements Parcelable {
        /**
         * id : 21efc3cd809b42549f8fb79118ed08f8
         * title : 你有能力，我有空间
         * banner : [{"uid":"rc-upload-1588093145051-5","status":"done","name":"timg.jpg","url":"https://47.57.31.10:8443/odm/file/2020/04/29/3fa3ed89e49248e2bc212c8656beba4e.jpg"}]
         * state : 1
         * anchorUserId : b463092ec1b3435394068d6439d115d4
         * roomNumber : 2020042391
         * createTime : 1588093431351
         * anchorUserName : 张三
         * parentId : 483ca2c3c8934a0f8d0de0a584af1240
         * exhibitionUserDtoList : null
         * desc : 洽谈室
         * exhibitionLiveUrl : null
         * anchorLiveUrl : http://play.smallaide.com/live/1400356456_20200423917_admin_main.flv,http://play.smallaide.com/live/1400356456_20200423917_share_admin_main.flv,http://play.smallaide.com/live/1400356456_2020042391_admin_main.flv,http://play.smallaide.com/live/1400356456_2020042391_share_admin_main.flv
         * type : negotiate
         */

        private String id;
        private String title;
        private String banner;
        private String state;
        private String anchorUserId;
        private String roomNumber;
        private long createTime;
        private String anchorUserName;
        private String parentId;
        private List<ExhibitionUserDtoListBean> exhibitionUserDtoList;
        private String desc;
        private String exhibitionLiveUrl;
        private String anchorLiveUrl;
        private String type;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getBanner() {
            return banner;
        }

        public void setBanner(String banner) {
            this.banner = banner;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getAnchorUserId() {
            return anchorUserId;
        }

        public void setAnchorUserId(String anchorUserId) {
            this.anchorUserId = anchorUserId;
        }

        public String getRoomNumber() {
            return roomNumber;
        }

        public void setRoomNumber(String roomNumber) {
            this.roomNumber = roomNumber;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getAnchorUserName() {
            return anchorUserName;
        }

        public void setAnchorUserName(String anchorUserName) {
            this.anchorUserName = anchorUserName;
        }

        public String getParentId() {
            return parentId;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }

        public List<ExhibitionUserDtoListBean> getExhibitionUserDtoList() {
            return exhibitionUserDtoList;
        }

        public void setExhibitionUserDtoList(List<ExhibitionUserDtoListBean> exhibitionUserDtoList) {
            this.exhibitionUserDtoList = exhibitionUserDtoList;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getExhibitionLiveUrl() {
            return exhibitionLiveUrl;
        }

        public void setExhibitionLiveUrl(String exhibitionLiveUrl) {
            this.exhibitionLiveUrl = exhibitionLiveUrl;
        }

        public String getAnchorLiveUrl() {
            return anchorLiveUrl;
        }

        public void setAnchorLiveUrl(String anchorLiveUrl) {
            this.anchorLiveUrl = anchorLiveUrl;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.id);
            dest.writeString(this.title);
            dest.writeString(this.banner);
            dest.writeString(this.state);
            dest.writeString(this.anchorUserId);
            dest.writeString(this.roomNumber);
            dest.writeLong(this.createTime);
            dest.writeString(this.anchorUserName);
            dest.writeString(this.parentId);
            dest.writeTypedList(this.exhibitionUserDtoList);
            dest.writeString(this.desc);
            dest.writeString(this.exhibitionLiveUrl);
            dest.writeString(this.anchorLiveUrl);
            dest.writeString(this.type);
        }

        public NegotiateBean() {
        }

        protected NegotiateBean(Parcel in) {
            this.id = in.readString();
            this.title = in.readString();
            this.banner = in.readString();
            this.state = in.readString();
            this.anchorUserId = in.readString();
            this.roomNumber = in.readString();
            this.createTime = in.readLong();
            this.anchorUserName = in.readString();
            this.parentId = in.readString();
            this.exhibitionUserDtoList = in.createTypedArrayList(ExhibitionUserDtoListBean.CREATOR);
            this.desc = in.readString();
            this.exhibitionLiveUrl = in.readString();
            this.anchorLiveUrl = in.readString();
            this.type = in.readString();
        }

        public static final Creator<NegotiateBean> CREATOR = new Creator<NegotiateBean>() {
            @Override
            public NegotiateBean createFromParcel(Parcel source) {
                return new NegotiateBean(source);
            }

            @Override
            public NegotiateBean[] newArray(int size) {
                return new NegotiateBean[size];
            }
        };
    }

    public static class ExhibitionUserDtoListBean implements Parcelable {
        /**
         * id : 20200427956
         * exhibitionId : 483ca2c3c8934a0f8d0de0a584af1240
         * userId : 0a6e761f51f84dfc8b7802e9078d4868
         * createTime : 1587971101714
         * userName : 李四
         * invitationCode : ifs3ruk
         */

        private String id;
        private String exhibitionId;
        private String userId;
        private long createTime;
        private String userName;
        private String invitationCode;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getExhibitionId() {
            return exhibitionId;
        }

        public void setExhibitionId(String exhibitionId) {
            this.exhibitionId = exhibitionId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getInvitationCode() {
            return invitationCode;
        }

        public void setInvitationCode(String invitationCode) {
            this.invitationCode = invitationCode;
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.id);
            dest.writeString(this.exhibitionId);
            dest.writeString(this.userId);
            dest.writeLong(this.createTime);
            dest.writeString(this.userName);
            dest.writeString(this.invitationCode);
        }

        public ExhibitionUserDtoListBean() {
        }

        protected ExhibitionUserDtoListBean(Parcel in) {
            this.id = in.readString();
            this.exhibitionId = in.readString();
            this.userId = in.readString();
            this.createTime = in.readLong();
            this.userName = in.readString();
            this.invitationCode = in.readString();
        }

        public static final Creator<ExhibitionUserDtoListBean> CREATOR = new Creator<ExhibitionUserDtoListBean>() {
            @Override
            public ExhibitionUserDtoListBean createFromParcel(Parcel source) {
                return new ExhibitionUserDtoListBean(source);
            }

            @Override
            public ExhibitionUserDtoListBean[] newArray(int size) {
                return new ExhibitionUserDtoListBean[size];
            }
        };
    }

    public static class LiveBean implements Parcelable {
        /**
         * id : ae071999528e40de84dcec8fdb0aa644
         * title : 四川会场
         * banner : [{"uid":"rc-upload-1588006041317-8","status":"done","name":"timg.jpg","url":"https://47.57.31.10:8443/odm/file/2020/04/28/d7653bd6a0d24c289bdcb95d474e0ba7.jpg"}]
         * state : 1
         * anchorUserId : 0a6e761f51f84dfc8b7802e9078d4868
         * roomNumber : 2020042391
         * createTime : 15876225648
         * anchorUserName : 李四
         * parentId : 483ca2c3c8934a0f8d0de0a584af1240
         * exhibitionUserDtoList : null
         * desc : null
         * exhibitionLiveUrl : http://live.codmr.com/live/93972_477b02d99dd6c00c5ba852bb9a9e1f6c.flv
         * anchorLiveUrl : http://play.smallaide.com/live/1400356456_20200423918_admin_main.flv,http://play.smallaide.com/live/1400356456_20200423918_share_admin_main.flv
         * type : live
         */

        private String id;
        private String title;
        private String banner;
        private String state;
        private String anchorUserId;
        private String roomNumber;
        private long createTime;
        private String anchorUserName;
        private String parentId;
        private List<ExhibitionUserDtoListBean> exhibitionUserDtoList;
        private String desc;
        private String exhibitionLiveUrl;
        private String anchorLiveUrl;
        private String type;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getBanner() {
            return banner;
        }

        public void setBanner(String banner) {
            this.banner = banner;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getAnchorUserId() {
            return anchorUserId;
        }

        public void setAnchorUserId(String anchorUserId) {
            this.anchorUserId = anchorUserId;
        }

        public String getRoomNumber() {
            return roomNumber;
        }

        public void setRoomNumber(String roomNumber) {
            this.roomNumber = roomNumber;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getAnchorUserName() {
            return anchorUserName;
        }

        public void setAnchorUserName(String anchorUserName) {
            this.anchorUserName = anchorUserName;
        }

        public String getParentId() {
            return parentId;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }

        public List<ExhibitionUserDtoListBean> getExhibitionUserDtoList() {
            return exhibitionUserDtoList;
        }

        public void setExhibitionUserDtoList(List<ExhibitionUserDtoListBean> exhibitionUserDtoList) {
            this.exhibitionUserDtoList = exhibitionUserDtoList;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getExhibitionLiveUrl() {
            return exhibitionLiveUrl;
        }

        public void setExhibitionLiveUrl(String exhibitionLiveUrl) {
            this.exhibitionLiveUrl = exhibitionLiveUrl;
        }

        public String getAnchorLiveUrl() {
            return anchorLiveUrl;
        }

        public void setAnchorLiveUrl(String anchorLiveUrl) {
            this.anchorLiveUrl = anchorLiveUrl;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.id);
            dest.writeString(this.title);
            dest.writeString(this.banner);
            dest.writeString(this.state);
            dest.writeString(this.anchorUserId);
            dest.writeString(this.roomNumber);
            dest.writeLong(this.createTime);
            dest.writeString(this.anchorUserName);
            dest.writeString(this.parentId);
            dest.writeTypedList(this.exhibitionUserDtoList);
            dest.writeString(this.desc);
            dest.writeString(this.exhibitionLiveUrl);
            dest.writeString(this.anchorLiveUrl);
            dest.writeString(this.type);
        }

        public LiveBean() {
        }

        protected LiveBean(Parcel in) {
            this.id = in.readString();
            this.title = in.readString();
            this.banner = in.readString();
            this.state = in.readString();
            this.anchorUserId = in.readString();
            this.roomNumber = in.readString();
            this.createTime = in.readLong();
            this.anchorUserName = in.readString();
            this.parentId = in.readString();
            this.exhibitionUserDtoList = in.createTypedArrayList(ExhibitionUserDtoListBean.CREATOR);
            this.desc = in.readString();
            this.exhibitionLiveUrl = in.readString();
            this.anchorLiveUrl = in.readString();
            this.type = in.readString();
        }

        public static final Creator<LiveBean> CREATOR = new Creator<LiveBean>() {
            @Override
            public LiveBean createFromParcel(Parcel source) {
                return new LiveBean(source);
            }

            @Override
            public LiveBean[] newArray(int size) {
                return new LiveBean[size];
            }
        };
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.roomNumber);
        dest.writeString(this.anchorLiveUrl);
        dest.writeString(this.banner);
        dest.writeString(this.title);
        dest.writeString(this.type);
        dest.writeString(this.parentId);
        dest.writeString(this.anchorUserName);
        dest.writeString(this.exhibitionLiveUrl);
        dest.writeLong(this.createTime);
        dest.writeString(this.anchorUserId);
        dest.writeString(this.id);
        dest.writeString(this.state);
        dest.writeString(this.desc);
        dest.writeList(this.negotiate);
        dest.writeList(this.exhibitionUserDtoList);
        dest.writeList(this.live);
    }

    public LiveChatRoomBean() {
    }

    protected LiveChatRoomBean(Parcel in) {
        this.roomNumber = in.readString();
        this.anchorLiveUrl = in.readString();
        this.banner = in.readString();
        this.title = in.readString();
        this.type = in.readString();
        this.parentId = in.readString();
        this.anchorUserName = in.readString();
        this.exhibitionLiveUrl = in.readString();
        this.createTime = in.readLong();
        this.anchorUserId = in.readString();
        this.id = in.readString();
        this.state = in.readString();
        this.desc = in.readString();
        this.negotiate = new ArrayList<NegotiateBean>();
        in.readList(this.negotiate, NegotiateBean.class.getClassLoader());
        this.exhibitionUserDtoList = new ArrayList<ExhibitionUserDtoListBean>();
        in.readList(this.exhibitionUserDtoList, ExhibitionUserDtoListBean.class.getClassLoader());
        this.live = new ArrayList<LiveBean>();
        in.readList(this.live, LiveBean.class.getClassLoader());
    }

    public static final Creator<LiveChatRoomBean> CREATOR = new Creator<LiveChatRoomBean>() {
        @Override
        public LiveChatRoomBean createFromParcel(Parcel source) {
            return new LiveChatRoomBean(source);
        }

        @Override
        public LiveChatRoomBean[] newArray(int size) {
            return new LiveChatRoomBean[size];
        }
    };
}
