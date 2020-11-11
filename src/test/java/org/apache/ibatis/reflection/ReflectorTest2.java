package org.apache.ibatis.reflection;

import org.junit.jupiter.api.Test;

/**
 * @author GG Bond
 * @date 2020/11/5 16:28
 * @description
 */

public class ReflectorTest2 {

  @Test
  void fun1() {
    Reflector reflector = new Reflector(Section.class);
    Class<?> id = reflector.getGetterType("id");
    System.out.println(id);
  }

  interface Entity<T> {
    T getId();

    void setId(T id);
  }

  static abstract class AbstractEntity implements ReflectorTest.Entity<Long> {

    private Long id;

    @Override
    public Long getId() {
      return id;
    }

    @Override
    public void setId(Long id) {
      this.id = id;
    }
  }

  static class Section extends ReflectorTest.AbstractEntity implements ReflectorTest.Entity<Long> {
  }
}
