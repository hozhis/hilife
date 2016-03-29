package cn.dolphinsoft.hilife.authority.dto.upstream;

import cn.dolphinsoft.hilife.authority.dto.PlatformRoleInfoDto;
import cn.dolphinsoft.hilife.common.dto.PagingDto;

/**
 * 
 * Class Name: PlatformRoleInfoSearchDto
 * 
 * Description: 平台 权限管理 搜索DTO
 * 
 * @author hozhis
 *
 */
public class PlatformRoleInfoSearchDto extends PagingDto<PlatformRoleInfoDto> {

    private static final long serialVersionUID = -6289362437102895530L;

    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
