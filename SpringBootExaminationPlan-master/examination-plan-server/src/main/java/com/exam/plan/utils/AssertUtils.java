package com.exam.plan.utils;

import com.exam.plan.entity.ResultCode;
import com.exam.plan.exception.ServiceException;

public class AssertUtils {
  public static void throwIf(
          final boolean statement, final ResultCode resultCode, final String message) {
    if (statement) {
      throw toThrow(resultCode, message);
    }
  }

  public static void throwIf(
      final boolean statement, final ResultCode resultCode, final Object... messages) {
    throwIf(statement, resultCode, resultCode.format(messages));
  }

  public static RuntimeException toThrow(final ResultCode resultCode, final Object... messages) {
    return new ServiceException(resultCode, resultCode.format(messages));
  }

  public static void asserts(
      final boolean statement, final ResultCode resultCode, final Object... messages) {
    throwIf(!statement, resultCode, messages);
  }
}
