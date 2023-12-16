using System;
using System.Data;

namespace appraisal_system.utility {
    public static class ToModel {
        /**
         * 将 DataRow 形式的数据转成指定类型格式的数据
         */
        public static T DataRowToModel<T>(this DataRow dataRow) {
            //反射获取 T 的类型
            Type type = typeof(T);
            //获取 T 实例
            T instance = (T) Activator.CreateInstance(type);
            //枚举 T 的属性
            foreach (var property in type.GetProperties()) {
                //设置属性值
                property.SetValue(instance, dataRow[property.Name]);
            }

            return instance;
        }
    }
}
