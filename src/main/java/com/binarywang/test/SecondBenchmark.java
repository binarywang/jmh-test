package com.binarywang.test;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * <pre>
 *
 * Created by Binary Wang on 2018/12/8.
 * </pre>
 * http://blog.dyngr.com/blog/2016/10/29/introduction-of-jmh/
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
public class SecondBenchmark {
  @Param({"10000", "100000", "1000000"})
  private int length;

  private int[] numbers;
  private Calculator singleThreadCalc;
  private Calculator multiThreadCalc;

  public static void main(String[] args) throws RunnerException {
    Options opt = new OptionsBuilder()
        .include(SecondBenchmark.class.getSimpleName())
        .forks(2)
        .warmupIterations(5)
        .measurementIterations(5)
        .build();

    new Runner(opt).run();
  }

  @Benchmark
  public long singleThreadBench() {
    return singleThreadCalc.sum(numbers);
  }

  @Benchmark
  public long multiThreadBench() {
    return multiThreadCalc.sum(numbers);
  }

  @Setup
  public void prepare() {
    numbers = IntStream.rangeClosed(1, length).toArray();
    singleThreadCalc = new SingleThreadCalculator();
    multiThreadCalc = new MultiThreadCalculator(Runtime.getRuntime().availableProcessors());
  }

  @TearDown
  public void shutdown() {
    singleThreadCalc.shutdown();
    multiThreadCalc.shutdown();
  }
}
