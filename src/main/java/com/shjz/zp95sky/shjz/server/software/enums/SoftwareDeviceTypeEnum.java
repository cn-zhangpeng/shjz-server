package com.shjz.zp95sky.shjz.server.software.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.util.ObjectUtils;

/**
 * 设备类型
 * @author 山海紫穹
 * @date 2021年06月24日 14:00
 */
@Getter
@AllArgsConstructor
public enum SoftwareDeviceTypeEnum {

    /** 电脑端 */
    PC("pc"),
    /** 移动端 */
    MOBILE("mobile");

    private final String key;

    public static SoftwareDeviceTypeEnum getByKey(String key) {
        if (ObjectUtils.isEmpty(key)) { return null; }

        for (SoftwareDeviceTypeEnum typeEnum : SoftwareDeviceTypeEnum.values()) {
            if (typeEnum.getKey().equals(key)) {
                return typeEnum;
            }
        }
        return null;
    }

}
