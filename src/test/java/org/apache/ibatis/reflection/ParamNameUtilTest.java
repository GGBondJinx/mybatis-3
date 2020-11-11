package org.apache.ibatis.reflection;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @author GG Bond
 * @date 2020/11/9 20:15
 * @description
 */

public class ParamNameUtilTest {

  @Test
  void getParamNamesTest() {
    Method[] declaredMethods = ParamNameUtil.class.getDeclaredMethods();
    for (Method declaredMethod : declaredMethods) {
      List<String> paramNames = ParamNameUtil.getParamNames(declaredMethod);
      System.out.println(paramNames);
    }
  }
}
