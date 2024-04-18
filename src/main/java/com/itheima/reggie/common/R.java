package com.itheima.reggie.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * @author GRS
 * @since 2024/4/18 下午2:38
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public class R<T>
{
    private Integer code;

    private String msg;

    private T data;

    private Map<String, Object> map = new HashMap<>();

    public static <T> R<T> success(T object)
    {
        R<T> r = new R<>();
        r.data = object;
        r.code = 1;
        return r;
    }

    public static <T> R<T> error(String msg)
    {
        R<T> r = new R<>();
        r.msg = msg;
        r.code = 0;
        return r;
    }

    public R<T> add(String key, Object value)
    {
        this.map.put(key, value);
        return this;
    }
}