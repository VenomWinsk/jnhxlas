package com.hxht.logprocess.core.util;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

public class BeanUtils {
    public static final String PROPERTY_NAME = "com.csii.pe.context.key.lowercase";
    private static final boolean LOWERCASE = isLowercase();
    private static ConcurrentHashMap<Class<?>, PropertyEditor> customEditors = new ConcurrentHashMap();
    static ThreadLocal<Set> recurseBeanSet;

    private static boolean isLowercase() {
        String lowercase = System.getProperty("com.csii.pe.context.key.lowercase", "false");
        return Boolean.valueOf(lowercase).booleanValue();
    }

    private BeanUtils() {
    }

    public static void registerCustomEditor(Class<?> clazz, PropertyEditor editor) {
        customEditors.put(clazz, editor);
    }

    public static String getConvertedName(String name) {
        return name != null && name.length() != 0 && !LOWERCASE ? Character.toUpperCase(name.charAt(0)) + name.substring(1) : name;
    }

    public static <T> T map2Bean(Map map, T obj) {
        BeanWrapper bw = new BeanWrapperImpl(obj);
        PropertyDescriptor[] props = bw.getPropertyDescriptors();
        PropertyDescriptor[] var4 = props;
        int var5 = props.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            PropertyDescriptor pd = var4[var6];
            String name = pd.getName();
            if (bw.isWritableProperty(name) && bw.isReadableProperty(name)) {
                Class class0 = pd.getPropertyType();
                String convertedName;
                Object value;
                if (Enum.class.isAssignableFrom(class0)) {
                    convertedName = getConvertedName(name);
                    value = map.get(convertedName);
                    if (value != null) {
                        if (value.getClass() == class0) {
                            bw.setPropertyValue(name, value);
                        } else {
                            String enumValue = String.valueOf(value);
                            if (enumValue.length() > 0) {
                                Enum v = Enum.valueOf(class0, enumValue);
                                bw.setPropertyValue(name, v);
                            }
                        }
                    }
                } else {
                    convertedName = getConvertedName(name);
                    value = map.get(convertedName);
                    if (value != null) {
                        bw.setPropertyValue(name, value);
                    }
                }
            }
        }

        return (T) bw.getWrappedInstance();
    }

    public static <T> T map2Bean(Map map, Class<T> clazz) {
        BeanWrapper bw = new BeanWrapperImpl(clazz);
        Iterator var3 = customEditors.entrySet().iterator();

        while(var3.hasNext()) {
            Entry<Class<?>, PropertyEditor> en = (Entry)var3.next();
            bw.registerCustomEditor((Class)en.getKey(), (PropertyEditor)en.getValue());
        }

        PropertyDescriptor[] props = bw.getPropertyDescriptors();
        PropertyDescriptor[] var15 = props;
        int var5 = props.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            PropertyDescriptor pd = var15[var6];
            String name = pd.getName();
            if (bw.isWritableProperty(name) && bw.isReadableProperty(name)) {
                Class class0 = pd.getPropertyType();
                String convertedName;
                Object value;
                if (Enum.class.isAssignableFrom(class0)) {
                    convertedName = getConvertedName(name);
                    value = map.get(convertedName);
                    if (value != null) {
                        if (value.getClass() == class0) {
                            bw.setPropertyValue(name, value);
                        } else {
                            String enumValue = String.valueOf(value);
                            if (enumValue.length() > 0) {
                                Enum v = Enum.valueOf(class0, String.valueOf(value));
                                bw.setPropertyValue(name, v);
                            }
                        }
                    }
                } else {
                    convertedName = getConvertedName(name);
                    value = map.get(convertedName);
                    if (value != null) {
                        bw.setPropertyValue(name, value);
                    }
                }
            }
        }

        return (T) bw.getWrappedInstance();
    }





    public static <T> List<T> listMap2ListBean(List list, Class<T> class0) {
        List<T> result = new ArrayList();
        Iterator it = list.iterator();

        while(it.hasNext()) {
            T t = map2Bean((Map)it.next(), class0);
            result.add(t);
        }

        return result;
    }



    public static void list2Bean(List<?> srcBeanObject, Object destBeanObject, String listPropName) {
        BeanWrapperImpl destBean = new BeanWrapperImpl(destBeanObject);
        destBean.setPropertyValue(listPropName, srcBeanObject);
    }





    public static <T> T map2BeanStrict(Map map, T obj) {
        BeanWrapper bw = new BeanWrapperImpl(obj);
        PropertyDescriptor[] props = bw.getPropertyDescriptors();
        PropertyDescriptor[] var4 = props;
        int var5 = props.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            PropertyDescriptor pd = var4[var6];
            String name = pd.getName();
            if (bw.isWritableProperty(name) && bw.isReadableProperty(name)) {
                Class class0 = pd.getPropertyType();
                Object value;
                if (Enum.class.isAssignableFrom(class0)) {
                    value = map.get(name);
                    if (value != null) {
                        if (value.getClass() == class0) {
                            bw.setPropertyValue(name, value);
                        } else {
                            Enum v = Enum.valueOf(class0, String.valueOf(value));
                            bw.setPropertyValue(name, v);
                        }
                    }
                } else {
                    value = map.get(name);
                    if (value != null) {
                        bw.setPropertyValue(name, value);
                    }
                }
            }
        }

        return (T) bw.getWrappedInstance();
    }

    public static <T> T map2BeanStrict(Map map, Class<T> clazz) {
        BeanWrapper bw = new BeanWrapperImpl(clazz);
        PropertyDescriptor[] props = bw.getPropertyDescriptors();
        PropertyDescriptor[] var4 = props;
        int var5 = props.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            PropertyDescriptor pd = var4[var6];
            String name = pd.getName();
            if (bw.isWritableProperty(name) && bw.isReadableProperty(name)) {
                Class class0 = pd.getPropertyType();
                Object value;
                if (Enum.class.isAssignableFrom(class0)) {
                    value = map.get(name);
                    if (value != null) {
                        if (value.getClass() == class0) {
                            bw.setPropertyValue(name, value);
                        } else {
                            Enum v = Enum.valueOf(class0, String.valueOf(value));
                            bw.setPropertyValue(name, v);
                        }
                    }
                } else {
                    value = map.get(name);
                    if (value != null) {
                        bw.setPropertyValue(name, value);
                    }
                }
            }
        }

        return (T) bw.getWrappedInstance();
    }



    private static class CustomPropertyEditor extends PropertyEditorSupport {
        protected Object value;

        private CustomPropertyEditor() {
        }

        public void setAsText(String text) throws IllegalArgumentException {
            this.value = text;
        }

        public void setValue(Object value) {
            this.value = value;
        }
    }


    public static <T> T map2Object(Map<String, Object> map, Class<T> clazz) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        if (map == null) {
            return null;
        }
        T obj = null;
        try {
            // 使用newInstance来创建对象
            obj = clazz.newInstance();
            // 获取类中的所有字段
            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field field : fields) {
                int mod = field.getModifiers();
                // 判断是拥有某个修饰符
                if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                    continue;
                }
                // 当字段使用private修饰时，需要加上
                field.setAccessible(true);
                // 获取参数类型名字
                String filedTypeName = field.getType().getName();
                // 判断是否为时间类型，使用equalsIgnoreCase比较字符串，不区分大小写
                // 给obj的属性赋值
                if (filedTypeName.equalsIgnoreCase("java.util.date")) {
                    String datetimestamp = (String) map.get(field.getName());
                    if (datetimestamp.equalsIgnoreCase("null")) {
                        field.set(obj, null);
                    } else {
                        field.set(obj, sdf.parse(datetimestamp));
                    }
                } else {
                    field.set(obj, map.get(field.getName()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
}


