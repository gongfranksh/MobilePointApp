package personal.wl.mobilepointapp.entity.menufunction;

public class MPAFunction {
      private String FunctionId;
      private String FunctionName;
      private String IconName;
      private String MethodName;

    public String getIconName() {
        return IconName;
    }

    public void setIconName(String iconName) {
        IconName = iconName;
    }

    public String getMethodName() {
        return MethodName;
    }

    public void setMethodName(String methodName) {
        MethodName = methodName;
    }

    public String getFunctionId() {
        return FunctionId;
    }

    public void setFunctionId(String functionId) {
        FunctionId = functionId;
    }

    public String getFunctionName() {
        return FunctionName;
    }

    public void setFunctionName(String functionName) {
        FunctionName = functionName;
    }
}
