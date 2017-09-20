package bean;

/**
 * Created by 祝文 on 2017/9/14.
 */

public class News {
    private String name;
    private boolean zhuangtai;

    @Override
    public String toString() {
        return "News{" +
                "name='" + name + '\'' +
                ", zhuangtai=" + zhuangtai +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setZhuangtai(boolean zhuangtai) {
        this.zhuangtai = zhuangtai;
    }

    public String getName() {
        return name;
    }

    public boolean isZhuangtai() {
        return zhuangtai;
    }

    public News() {
    }

    public News(String name, boolean zhuangtai) {
        this.name = name;
        this.zhuangtai = zhuangtai;
    }
}
