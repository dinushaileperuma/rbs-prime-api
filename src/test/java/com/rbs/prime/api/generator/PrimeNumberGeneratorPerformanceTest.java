package com.rbs.prime.api.generator;

import org.junit.Test;
import org.springframework.util.StopWatch;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.lessThan;


public class PrimeNumberGeneratorPerformanceTest {

    private PrimeNumberGenerator generator = new PrimeNumberGenerator(new SequentialFunction());

    @Test(timeout = 60000)
    public void testWhenNumberIs10ThenAllPrimeNumberBelowAreGenerated() {
        int number = 200_000;
        StopWatch stopWatch = new StopWatch();


        stopWatch.start("sequential");
        generator.generate(number, new SequentialFunction());
        stopWatch.stop();

        stopWatch.start("parallel");
        generator.generate(number, new ParallelFunction());
        stopWatch.stop();

        stopWatch.start("futures");
        generator.generate(number, new FuturesFunction());
        stopWatch.stop();

        StopWatch.TaskInfo[] taskInfos = stopWatch.getTaskInfo();
        long sequentialTime = taskInfos[0].getTimeMillis();
        long parallelTime = taskInfos[1].getTimeMillis();
        long futuresTime = taskInfos[2].getTimeMillis();

        assertThat(parallelTime, is(lessThan(sequentialTime)));
        assertThat(futuresTime, is(lessThan(sequentialTime)));

        long diff = sequentialTime - futuresTime;
        long diffInSecs = TimeUnit.SECONDS.convert(diff, TimeUnit.MILLISECONDS);
        assertThat(TimeUnit.SECONDS.toSeconds(5), is(lessThan(diffInSecs)));

    }
}
