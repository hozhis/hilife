package cn.dolphinsoft.hilife.common.lbs;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.dolphinsoft.hilife.common.constant.ApplicationConstant;
import cn.dolphinsoft.hilife.common.lbs.bean.BaiduGeocoderBean;
import cn.dolphinsoft.hilife.common.lbs.bean.BaiduResultBean;
import cn.dolphinsoft.hilife.common.lbs.bean.LocationInfoBean;
import cn.dolphinsoft.hilife.common.lbs.bean.StoreLocationDetailBean;
import cn.dolphinsoft.hilife.common.lbs.bean.StoreUserLocationDetailBean;
import cn.dolphinsoft.hilife.common.lbs.bean.UserLocationInfoBean;
import cn.dolphinsoft.hilife.common.util.AppConfigUtil;
import cn.dolphinsoft.hilife.common.util.HiLifeUtil;
import cn.dolphinsoft.hilife.common.util.HttpUtil;

/**
 * Class Name: BaiduApiUtil Description: 百度位置相关API
 * 
 * @author hozhis
 *
 */
public class BaiduApiUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaiduApiUtil.class);

    private static final String BAIDU_NEARBY_URL = "http://api.map.baidu.com/geosearch/v3/nearby?";

    private static final String BAIDU_POI_UPATE = "http://api.map.baidu.com/geodata/v3/poi/update";

    private static final String BAIDU_POI_CREATE = "http://api.map.baidu.com/geodata/v3/poi/create";

    private static final String BAIDU_POI_STORE_USER_QUERY = "http://api.map.baidu.com/geosearch/v3/detail"; // http://api.map.baidu.com/geosearch/v3/detail/{uid}
                                                                                                             // // GET请求

    private static final String BAIDU_GEOCODER_URL = "http://api.map.baidu.com/geocoder/v2/?";

    private static final String ENABLE_STORE = "enable:1";

    private static final String NORMAL_STATUS_CODE = "0";

    private static final String BAIDU_LBS_AK = "baidu.lbs.ak";

    private static final String BAIDU_LBS_STORE_GEOTABLE_ID = "baidu.lbs.store.geotable_id";

    private static final String BAIDU_LBS_STOREUSER_GEOTABLE_ID = "baidu.lbs.storeuser.geotable_id";

    /**
     * Description: 按获得附近门店按距离排序
     *
     * @param localtion
     * @param radius
     * @param pageIndex
     * @param pageSize
     * @param locationInfoBean
     * @return
     */
    public static LocationInfoBean getNearbyStoreByDistance(String localtion, Integer radius, String brand,
            Integer pageIndex, Integer pageSize) {
        return getNearbyStore(localtion, radius, brand, "distance:1", pageIndex, pageSize);
    }

    /**
     * Description: 按获得附近门店按星级排序
     *
     * @param localtion
     * @param radius
     * @param brand
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public static LocationInfoBean getNearbyStoreByRate(String localtion, Integer radius, String brand,
            Integer pageIndex, Integer pageSize) {
        return getNearbyStore(localtion, radius, brand, "rate:-1", pageIndex, pageSize);
    }

    /**
     * Description: 获得附近门店
     *
     * @param localtion
     * @param radius
     * @param brand
     * @param sortby
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public static LocationInfoBean getNearbyStore(String localtion, Integer radius, String brand, String sortby,
            Integer pageIndex, Integer pageSize) {
        StringBuilder url = new StringBuilder(BAIDU_NEARBY_URL);
        url.append("ak=").append(AppConfigUtil.getConfig(BAIDU_LBS_AK));
        url.append("&geotable_id=").append(AppConfigUtil.getConfig(BAIDU_LBS_STORE_GEOTABLE_ID));
        url.append("&filter=").append(ENABLE_STORE);
        if (!HiLifeUtil.isEmpty(brand)) {
            url.append("&tags=").append(brand);
        }
        url.append("&page_size=").append(pageSize);
        url.append("&page_index=").append(pageIndex);
        url.append("&location=").append(localtion);
        url.append("&radius=").append(radius);
        url.append("&sortby=").append(sortby);
        String content = HttpUtil.doGet(url.toString());
        LocationInfoBean locationInfoBean = HiLifeUtil.parseStringToObj(content, LocationInfoBean.class);
        if (NORMAL_STATUS_CODE.equals(locationInfoBean.getStatus())) {
            return locationInfoBean;
        } else {
            LOGGER.error("百度获取门店信息失败，错误码：", locationInfoBean.getStatus());
        }
        return null;
    }

    /**
     * Description: 获取门店技师经纬度
     *
     * @param localtion
     * @param radius
     * @param brand
     * @param sortby
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public static UserLocationInfoBean getStoreUserLocation(Integer storeUserBaiduId) {
        StringBuilder url = new StringBuilder(BAIDU_POI_STORE_USER_QUERY);
        url.append("/").append(storeUserBaiduId);
        url.append("?ak=").append(AppConfigUtil.getConfig(BAIDU_LBS_AK));
        url.append("&geotable_id=").append(AppConfigUtil.getConfig(BAIDU_LBS_STOREUSER_GEOTABLE_ID));
        String content = HttpUtil.doGet(url.toString());
        UserLocationInfoBean userLocationInfoBean = HiLifeUtil.parseStringToObj(content, UserLocationInfoBean.class);
        if (NORMAL_STATUS_CODE.equals(userLocationInfoBean.getStatus())) {
            return userLocationInfoBean;
        } else {
            LOGGER.error("百度获取门店信息失败，错误码：", userLocationInfoBean.getStatus());
        }
        return null;
    }

    /**
     * Description: 上传位置信息
     *
     * @param params
     * @return
     */
    public static BaiduResultBean createPoi(String params) {
        String content = HttpUtil.doPost(BAIDU_POI_CREATE, ApplicationConstant.FORM_CONTENT_TYPE, params);
        BaiduResultBean resultBean = HiLifeUtil.parseStringToObj(content, BaiduResultBean.class);
        if (NORMAL_STATUS_CODE.equals(resultBean.getStatus())) {
            return resultBean;
        } else {
            LOGGER.error("百度上传信息失败，错误码：", resultBean.getStatus());
        }
        return null;
    }

    /**
     * Description: 修改位置信息
     *
     * @param params
     * @return
     */
    public static BaiduResultBean updatePoi(String params) {
        String content = HttpUtil.doPost(BAIDU_POI_UPATE, ApplicationConstant.FORM_CONTENT_TYPE, params);
        BaiduResultBean resultBean = HiLifeUtil.parseStringToObj(content, BaiduResultBean.class);
        if (NORMAL_STATUS_CODE.equals(resultBean.getStatus())) {
            return resultBean;
        } else {
            LOGGER.error("百度修改信息失败，错误码：", resultBean.getStatus());
        }
        return null;
    }

    /**
     * Description: 上传门店信息
     *
     * @return
     */
    public static BaiduResultBean createStorePoi(StoreLocationDetailBean storeLocationDetailBean) {
        StringBuilder params = new StringBuilder("ak=").append(AppConfigUtil.getConfig(BAIDU_LBS_AK));
        params.append("&geotable_id=").append(AppConfigUtil.getConfig(BAIDU_LBS_STORE_GEOTABLE_ID));
        params.append("&coord_type=").append(storeLocationDetailBean.getCoord_type());
        params.append("&title=").append(storeLocationDetailBean.getTitle());
        params.append("&tags=").append(storeLocationDetailBean.getTags());
        params.append("&longitude=").append(storeLocationDetailBean.getLocation()[0]);
        params.append("&latitude=").append(storeLocationDetailBean.getLocation()[1]);
        params.append("&brand=").append(storeLocationDetailBean.getBrand());
        params.append("&store_id=").append(storeLocationDetailBean.getStore_id());
        params.append("&enable=").append(storeLocationDetailBean.getEnable());
        params.append("&rate=").append(storeLocationDetailBean.getRate());
        params.append("&imgUrl=").append(storeLocationDetailBean.getImgUrl());
        params.append("&business_hours=").append(storeLocationDetailBean.getBusiness_hours());
        return createPoi(params.toString());
    }

    /**
     * Description: 上传门店用户信息
     *
     * @return
     */
    public static BaiduResultBean createStoreUserPoi(StoreUserLocationDetailBean storeUserLocationDetailBean) {
        StringBuilder params = new StringBuilder("ak=").append(AppConfigUtil.getConfig(BAIDU_LBS_AK));
        params.append("&geotable_id=").append(AppConfigUtil.getConfig(BAIDU_LBS_STOREUSER_GEOTABLE_ID));
        params.append("&coord_type=").append(storeUserLocationDetailBean.getCoord_type());
        params.append("&title=").append(storeUserLocationDetailBean.getTitle());
        params.append("&longitude=").append(storeUserLocationDetailBean.getLongitude());
        params.append("&latitude=").append(storeUserLocationDetailBean.getLatitude());
        params.append("&user_id=").append(storeUserLocationDetailBean.getUser_id());
        return createPoi(params.toString());
    }

    /**
     * Description: 修改门店信息
     *
     * @param storeLocationDetailBean
     * @return
     */
    public static BaiduResultBean updateStorePoi(StoreLocationDetailBean storeLocationDetailBean) {
        StringBuilder params = new StringBuilder("ak=").append(AppConfigUtil.getConfig(BAIDU_LBS_AK));
        params.append("&geotable_id=").append(AppConfigUtil.getConfig(BAIDU_LBS_STORE_GEOTABLE_ID));
        params.append("&coord_type=").append(storeLocationDetailBean.getCoord_type());
        params.append("&id=").append(storeLocationDetailBean.getUid());
        if (!HiLifeUtil.isEmpty(storeLocationDetailBean.getTitle())) {
            params.append("&title=").append(storeLocationDetailBean.getTitle());
        }
        if (!HiLifeUtil.isEmpty(storeLocationDetailBean.getTags())) {
            params.append("&tags=").append(storeLocationDetailBean.getTags());
        }
        if (!HiLifeUtil.isEmpty(storeLocationDetailBean.getLocation()[0])) {
            params.append("&longitude=").append(storeLocationDetailBean.getLocation()[0]);
        }
        if (!HiLifeUtil.isEmpty(storeLocationDetailBean.getLocation()[1])) {
            params.append("&latitude=").append(storeLocationDetailBean.getLocation()[1]);
        }
        if (!HiLifeUtil.isEmpty(storeLocationDetailBean.getBrand())) {
            params.append("&brand=").append(storeLocationDetailBean.getBrand());
        }
        if (!HiLifeUtil.isEmpty(String.valueOf(storeLocationDetailBean.getStore_id()))) {
            params.append("&store_id=").append(storeLocationDetailBean.getStore_id());
        }
        if (!HiLifeUtil.isEmpty(String.valueOf(storeLocationDetailBean.getEnable()))) {
            params.append("&enable=").append(storeLocationDetailBean.getEnable());
        }
        if (!HiLifeUtil.isEmpty(String.valueOf(storeLocationDetailBean.getRate()))) {
            params.append("&rate=").append(storeLocationDetailBean.getRate());
        }
        if (!HiLifeUtil.isEmpty(String.valueOf(storeLocationDetailBean.getImgUrl()))) {
            params.append("&imgUrl=").append(storeLocationDetailBean.getImgUrl());
        }
        if (!HiLifeUtil.isEmpty(String.valueOf(storeLocationDetailBean.getBusiness_hours()))) {
            params.append("&business_hours=").append(storeLocationDetailBean.getBusiness_hours());
        }
        return updatePoi(params.toString());
    }

    /**
     * Description: 修改门店用户信息
     *
     * @param storeLocationDetailBean
     * @return
     */
    public static BaiduResultBean updateStoreUserPoi(StoreUserLocationDetailBean storeUserLocationDetailBean) {
        StringBuilder params = new StringBuilder("ak=").append(AppConfigUtil.getConfig(BAIDU_LBS_AK));
        params.append("&geotable_id=").append(AppConfigUtil.getConfig(BAIDU_LBS_STOREUSER_GEOTABLE_ID));
        params.append("&coord_type=").append(storeUserLocationDetailBean.getCoord_type());
        params.append("&id=").append(storeUserLocationDetailBean.getUid());
        if (!HiLifeUtil.isEmpty(storeUserLocationDetailBean.getLongitude())) {
            params.append("&longitude=").append(storeUserLocationDetailBean.getLongitude());
        }
        if (!HiLifeUtil.isEmpty(storeUserLocationDetailBean.getLatitude())) {
            params.append("&latitude=").append(storeUserLocationDetailBean.getLatitude());
        }
        if (!HiLifeUtil.isEmpty(String.valueOf(storeUserLocationDetailBean.getUser_id()))) {
            params.append("&user_id=").append(storeUserLocationDetailBean.getUser_id());
        }
        return updatePoi(params.toString());
    }

    /**
     * Description: 根据地址获得经纬度
     *
     * @param address
     * @return
     */
    @SuppressWarnings("unchecked")
    public static String[] getLocationByAddress(String address) {
        if (!HiLifeUtil.isEmpty(address)) {
            try {
                StringBuilder url = new StringBuilder(BAIDU_GEOCODER_URL);
                url.append("ak=").append(AppConfigUtil.getConfig(BAIDU_LBS_AK));
                url.append("&output=").append("json");
                url.append("&address=").append(URLEncoder.encode(address, ApplicationConstant.ENCODING));
                String content = HttpUtil.doGet(url.toString());
                BaiduGeocoderBean geocoderBean = HiLifeUtil.parseStringToObj(content, BaiduGeocoderBean.class);
                if (NORMAL_STATUS_CODE.equals(geocoderBean.getStatus())) {
                    Map<String, Object> result = geocoderBean.getResult();
                    Map<String, String> map = (Map<String, String>) result.get("location");
                    String[] location = new String[] { String.valueOf(map.get("lng")), String.valueOf(map.get("lat")) };
                    return location;
                } else {
                    LOGGER.error("百度获取取地址坐标失败，错误码：", geocoderBean.getStatus());
                }
            } catch (UnsupportedEncodingException e) {
                LOGGER.error("百度获取地址坐标编码转换错误，地址：", address);
            }
        }
        return null;

    }
}
