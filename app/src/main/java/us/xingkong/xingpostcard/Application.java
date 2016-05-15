package us.xingkong.xingpostcard;

import ly.img.android.ImgLySdk;

/**
 * Created by hugeterry(http://hugeterry.cn)
 * Date: 16/5/15 21:23
 */
public class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();

        ImgLySdk.init(this);
    }
}
