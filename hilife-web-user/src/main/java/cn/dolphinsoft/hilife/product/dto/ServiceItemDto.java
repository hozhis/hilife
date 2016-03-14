package cn.dolphinsoft.hilife.product.dto;

import cn.dolphinsoft.hilife.common.dto.RequestDto;

public class ServiceItemDto extends RequestDto {

    private static final long serialVersionUID = -8239656524619793883L;

    private String serviceId;

    private String serviceName;

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

}
