package com.lxinet.jeesns.core.utils;

import com.lxinet.jeesns.core.entity.Picture;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

/**
 * Created by zchuanzhao on 2017/3/7.
 */
public class PictureUtil {
    public static void delete(HttpServletRequest request, List<Picture> pictures){
        if (pictures != null){
            for (Picture picture : pictures){
                delete(request, picture);
            }
        }

    }

    public static void delete(HttpServletRequest request, Picture picture){
        String path = picture.getPath();
        String thumbnailPath = picture.getThumbnailPath();
        if(StringUtils.isNotEmpty(path)){
            File file = new File(request.getServletContext().getRealPath(path));
            if ((file.exists())){
                file.delete();
            }
        }
        if(StringUtils.isNotEmpty(thumbnailPath)){
            File file = new File(request.getServletContext().getRealPath(thumbnailPath));
            if ((file.exists())){
                file.delete();
            }
        }
    }
}
