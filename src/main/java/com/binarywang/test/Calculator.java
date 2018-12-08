package com.binarywang.test;

/**
 * <pre>
 *
 * Created by Binary Wang on 2018/12/8.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public interface Calculator {
  /**
   * calculate sum of an integer array
   * @param numbers
   * @return
   */
  public long sum(int[] numbers);

  /**
   * shutdown pool or reclaim any related resources
   */
  public void shutdown();
}
