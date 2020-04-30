package com.miu30.common.ui.entity;

import java.util.List;

public class ZfrySignParam {

    /**
     * wsid : 文书ID
     * wsName : 文书名称
     * coordinateList : [{"msspid":"签章人员1编码","top":0,"left":0,"bottom":255,"right":255},{"msspid":"签章人员2编码","top":0,"left":0,"bottom":255,"right":255}]
     */

    private String wsid;
    private String wsName;
    private List<CoordinateListBean> coordinateList;

    public String getWsid() {
        return wsid;
    }

    public void setWsid(String wsid) {
        this.wsid = wsid;
    }

    public String getWsName() {
        return wsName;
    }

    public void setWsName(String wsName) {
        this.wsName = wsName;
    }

    public List<CoordinateListBean> getCoordinateList() {
        return coordinateList;
    }

    public void setCoordinateList(List<CoordinateListBean> coordinateList) {
        this.coordinateList = coordinateList;
    }

    public static class CoordinateListBean {
        /**
         * msspid : 签章人员1编码
         * top : 0
         * left : 0
         * bottom : 255
         * right : 255
         */

        private String msspid;
        private int top;
        private int left;
        private int bottom;
        private int right;

        public String getMsspid() {
            return msspid;
        }

        public void setMsspid(String msspid) {
            this.msspid = msspid;
        }

        public int getTop() {
            return top;
        }

        public void setTop(int top) {
            this.top = top;
        }

        public int getLeft() {
            return left;
        }

        public void setLeft(int left) {
            this.left = left;
        }

        public int getBottom() {
            return bottom;
        }

        public void setBottom(int bottom) {
            this.bottom = bottom;
        }

        public int getRight() {
            return right;
        }

        public void setRight(int right) {
            this.right = right;
        }

        public void setLocation(int left,int bottom,int right,int top) {
            this.left = left;
            this.bottom = bottom;
            this.right = right;
            this.top = top;
        }
    }
}
