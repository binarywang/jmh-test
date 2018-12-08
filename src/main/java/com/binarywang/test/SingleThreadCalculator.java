package com.binarywang.test;

/**
 * <pre>
 *
 * Created by Binary Wang on 2018/12/8.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public class SingleThreadCalculator implements Calculator {
  @Override
  public long sum(int[] numbers) {
    long total = 0L;
    for (int i : numbers) {
      total += i;
    }
    return total;
  }

  @Override
  public void shutdown() {
    // nothing to do
  }
}