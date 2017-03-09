package com.doomedforfailure.myapplication;

import org.junit.Test;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.functions.Function;
import io.reactivex.subscribers.TestSubscriber;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void RxTest() throws Exception {
        TestSubscriber<String> subscriber = TestSubscriber.create();
        Flowable.just(1, 2, 3).map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Exception {
                return String.valueOf(integer);
            }
        })
                .subscribe(subscriber);

        subscriber.assertValues("1","2","3");

    }

    @Test
    public void DanLew() throws Exception {
        Flowable<String> myFlowable = Flowable.create(
                new FlowableOnSubscribe<String>() {
                    @Override
                    public void subscribe(FlowableEmitter<String> e) throws Exception {
                        e.onNext("Hello, world!");
                        e.onComplete();
                    }
                }, BackpressureStrategy.LATEST
        );

        TestSubscriber<String> testSubscriber = TestSubscriber.create();
        myFlowable.subscribe(testSubscriber);
        testSubscriber.assertValues("Hello, world!");
        testSubscriber.assertComplete();
    }
}