package com.example.fx50j.zhdailynews.Beans;

import java.util.List;

/**
 * Created by FX50J on 2016/11/18.
 */

public class Latest_Bean {

    /**
     * date : 20161118
     * stories : [{"images":["http://pic2.zhimg.com/34e837d5beff24f251bfdd3858e2b82d.jpg"],"type":0,"id":8989393,"ga_prefix":"111815","title":"到了 2020 年，中国会是唯一一个拥有太空站的国家吗？"},{"images":["http://pic2.zhimg.com/2b4c546e653b35161e5536692bd6fcfd.jpg"],"type":0,"id":8989282,"ga_prefix":"111814","title":"伊隆 · 马斯克：我有好几家公司，自己收购自己倒腾钱"},{"images":["http://pic4.zhimg.com/5fa88ce1a7d7e50b2afb2b6638234513.jpg"],"type":0,"id":8989303,"ga_prefix":"111813","title":"吃了好多我才注意到，这红红的是血吗？"},{"images":["http://pic3.zhimg.com/c1b8b4b64e9811222ceca580c1eb2caa.jpg"],"type":0,"id":8982498,"ga_prefix":"111812","title":"大误 · 原来我是非洲人"},{"title":"相机越来越智能，想要拍好就得多拼「套路」","ga_prefix":"111811","images":["http://pic2.zhimg.com/abe9d24c83205804dcbbcff1c1961775.jpg"],"multipic":true,"type":0,"id":8988075},{"images":["http://pic3.zhimg.com/9bfe8ebb02cbc880246c8e53fc6d15da.jpg"],"type":0,"id":8983320,"ga_prefix":"111810","title":"他们编出「时间无价」这种谎言，都是为了天真的我们"},{"title":"电影里的花式拉牌很酷炫对不对？我来教你","ga_prefix":"111809","images":["http://pic4.zhimg.com/235b915ebef6a3679396f5c08aba6a73.jpg"],"multipic":true,"type":0,"id":8918569},{"images":["http://pic1.zhimg.com/fe47a8b572518e34678cb27400f7257c.jpg"],"type":0,"id":8988073,"ga_prefix":"111809","title":"别只盯着「谢谢惠顾」，瓶盖里还有别的学问"},{"images":["http://pic4.zhimg.com/7c1d0f7caf11793395335e37e94834e3.jpg"],"type":0,"id":8988072,"ga_prefix":"111807","title":"总是预测错也不丢人，人类的思维机制确实有弱点"},{"images":["http://pic1.zhimg.com/8bb6d46045832cb14bbd7847036ab404.jpg"],"type":0,"id":8987940,"ga_prefix":"111807","title":"开灯睡觉影响记忆力"},{"images":["http://pic1.zhimg.com/cf6bc0b378483be2cb8c82f1ca99b27c.jpg"],"type":0,"id":8987977,"ga_prefix":"111807","title":"很多大公司开始操心你的信用情况，这事才刚刚开始"},{"images":["http://pic4.zhimg.com/de98e58e1c10c5086a8e6bf2ad06a97f.jpg"],"type":0,"id":8987960,"ga_prefix":"111807","title":"读读日报 24 小时热门 TOP 5 · 满天都是小星星"},{"images":["http://pic3.zhimg.com/fd310078c71a687e4485407fc42952ea.jpg"],"type":0,"id":8987983,"ga_prefix":"111806","title":"瞎扯 · 如何正确地吐槽"}]
     * top_stories : [{"image":"http://pic4.zhimg.com/b363d5804b497b2b4f55cdae03ade92b.jpg","type":0,"id":8989282,"ga_prefix":"111814","title":"伊隆 · 马斯克：我有好几家公司，自己收购自己倒腾钱"},{"image":"http://pic4.zhimg.com/6ae9c71bcd6e804a2a17a899dfc6561f.jpg","type":0,"id":8989393,"ga_prefix":"111815","title":"到了 2020 年，中国会是唯一一个拥有太空站的国家吗？"},{"image":"http://pic1.zhimg.com/196046379672284ee17ed9c6cb622ec8.jpg","type":0,"id":8918569,"ga_prefix":"111809","title":"电影里的花式拉牌很酷炫对不对？我来教你"},{"image":"http://pic2.zhimg.com/f84edda9a5de6eb99abff8b26958712d.jpg","type":0,"id":8987977,"ga_prefix":"111807","title":"很多大公司开始操心你的信用情况，这事才刚刚开始"},{"image":"http://pic2.zhimg.com/9b9b2c48ef2bc1a6ad8d3ffcea641139.jpg","type":0,"id":8987960,"ga_prefix":"111807","title":"读读日报 24 小时热门 TOP 5 · 满天都是小星星"}]
     */

    private String date;
    private List<StoriesBean> stories;
    private List<TopStoriesBean> top_stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    public List<TopStoriesBean> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStoriesBean> top_stories) {
        this.top_stories = top_stories;
    }

    public static class StoriesBean {
        /**
         * images : ["http://pic2.zhimg.com/34e837d5beff24f251bfdd3858e2b82d.jpg"]
         * type : 0
         * id : 8989393
         * ga_prefix : 111815
         * title : 到了 2020 年，中国会是唯一一个拥有太空站的国家吗？
         * multipic : true
         */

        private int type;
        private int id;
        private String ga_prefix;
        private String title;
        private boolean multipic;
        private List<String> images;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public boolean isMultipic() {
            return multipic;
        }

        public void setMultipic(boolean multipic) {
            this.multipic = multipic;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }

    public static class TopStoriesBean {
        /**
         * image : http://pic4.zhimg.com/b363d5804b497b2b4f55cdae03ade92b.jpg
         * type : 0
         * id : 8989282
         * ga_prefix : 111814
         * title : 伊隆 · 马斯克：我有好几家公司，自己收购自己倒腾钱
         */

        private String image;
        private int type;
        private int id;
        private String ga_prefix;
        private String title;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
