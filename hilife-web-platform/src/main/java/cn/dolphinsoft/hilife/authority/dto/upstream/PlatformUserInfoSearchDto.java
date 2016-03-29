package cn.dolphinsoft.hilife.authority.dto.upstream;

import cn.dolphinsoft.hilife.authority.dto.PlatformUserInfoDto;
import cn.dolphinsoft.hilife.common.dto.PagingDto;

/**
 * Class Name: PlatformUserInfoSearchDto
 * 
 * Description: 平台用户管理 搜索DTO
 * 
 * @author hozhis
 *
 */
public class PlatformUserInfoSearchDto extends PagingDto<PlatformUserInfoDto> {

    private static final long serialVersionUID = 7702830114348183829L;

    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
