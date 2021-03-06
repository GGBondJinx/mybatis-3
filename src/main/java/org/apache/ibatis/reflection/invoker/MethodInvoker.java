/**
 *    Copyright 2009-2018 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.apache.ibatis.reflection.invoker;

import org.apache.ibatis.reflection.Reflector;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Clinton Begin
 *
 * 指定方法的调用器
 */
public class MethodInvoker implements Invoker {

  /**
   * 类型
   */
  private final Class<?> type;
  /**
   * 指定方法
   */
  private final Method method;

  public MethodInvoker(Method method) {
    this.method = method;

    if (method.getParameterTypes().length == 1) {
      // 参数个数为 1 时，一般是 setter 方法，设置 type 为方法参数 [0]
      type = method.getParameterTypes()[0];
    } else {
      // 否则，一般是 get 方法，设置 type 为返回值类型
      type = method.getReturnType();
    }
  }

  /**
   * 执行指定的方法
   * @param target 目标
   * @param args 参数列表
   * @return 返回值
   */
  @Override
  public Object invoke(Object target, Object[] args) throws IllegalAccessException, InvocationTargetException {
    try {
      return method.invoke(target, args);
    } catch (IllegalAccessException e) {
      if (Reflector.canControlMemberAccessible()) {
        method.setAccessible(true);
        return method.invoke(target, args);
      } else {
        throw e;
      }
    }
  }

  @Override
  public Class<?> getType() {
    return type;
  }
}
