using System.Reflection;

namespace appraisal_system.common {
    /**
     * 此类是使用工厂模式创建所有窗体的公共方法类
     */
    public class FormFactory {
        private static readonly List<Form> Forms = new();

        private static List<Type> _types;
        static FormFactory() {
            var assembly = Assembly.LoadFrom("appraisal_system");
            //接收所有子类
            _types = assembly.GetTypes().ToList();
        }

        /**
         * 工厂模式创建所有窗体
         */
        public static Form? CreateForm(string? formName) {
            //隐藏所有窗体
            FormHideAll();

            formName ??= "None";
            var form = Forms.Find(form => form.Name == formName);
            //判断 form 是否已经在列表中存在
            if (form == null) {
                //查找子类里面命名等于 formName 的类型
                var type = _types.Find(type => type.Name == formName);
                //实例化
                form = (Form)Activator.CreateInstance(type);
                //将 form 加入列表
                Forms.Add(form);
            }
            return form;
        }

        /**
         * 隐藏所有窗体
         */
        private static void FormHideAll() {
            Forms.ForEach(form => form.Hide());
        }
    }
}
